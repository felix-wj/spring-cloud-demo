package cn.wangjie.elasticsearch.core.provider;

import cn.wangjie.elasticsearch.common.Page;
import cn.wangjie.elasticsearch.entity.request.SearchRq;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-12-06 16:23
 **/
public abstract class BaseSearchProvider implements ISearchProvider {

    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;
    protected String index;
    protected String type;

    public BaseSearchProvider(String index, String type) {
        this.index = index;
        this.type = type;
    }

    @Override
    public Page<Map<String, Object>> search(SearchRq searchRq) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        buildParam(sourceBuilder, searchRq);

        buildSort(sourceBuilder, searchRq);

        buildPage(sourceBuilder, searchRq);

        SearchRequest searchRequest = new SearchRequest(index).types(type).source(sourceBuilder);

        return searchES(searchRequest,searchRq.getPageSize());
    }

    protected  Page<Map<String, Object>> searchES(SearchRequest searchRequest,Integer pageSize){
        List<Map<String,Object>> result = new ArrayList<>();
        ActionFuture<SearchResponse> search = elasticsearchTemplate.getClient().search(searchRequest);
        SearchResponse searchResponse = search.actionGet();
        SearchHits hits = searchResponse.getHits();
        int total = 0;
        List<Map<String, Object>> list = null;
        if (hits != null ){
            if (hits.getHits().length>0){
                total = (int)hits.totalHits;
                SearchHit[] hitsHits = hits.getHits();
                list = new ArrayList<>(hits.getHits().length);
                for (SearchHit hitsHit : hitsHits) {
                    Map<String, Object> hitData = builderDataWithHit(hitsHit);
                    if (hitData != null) {
                        list.add(hitData);
                        continue;
                    }
                    Map<String, Object> sourceAsMap = hitsHit.getSourceAsMap();
                    if (sourceAsMap != null && sourceAsMap.containsKey("data")) {
                        list.add(builderData(sourceAsMap));
                    }
                }
            }
        }
        
        return new Page<>(list,total,pageSize);


    }

    protected  Map<String, Object> builderData(Map<String, Object> sourceAsMap){
        return (Map<String, Object>) sourceAsMap.get("data");
    }

    /**
     * @Author WangJie
     * @Description 封装返回数据
     * @param      
     * @Date  2019/12/25 17:15
     */
    protected abstract Map<String, Object> builderDataWithHit(SearchHit hitsHit);

    /**
     * @param
     * @Author WangJie
     * @Description 构建排序条件
     * @Date 2019/12/6 16:48
     */
    protected abstract void buildSort(SearchSourceBuilder sourceBuilder, SearchRq searchRq);

    protected abstract void buildParam(SearchSourceBuilder sourceBuilder, SearchRq searchRq);

    protected void buildPage(SearchSourceBuilder sourceBuilder, SearchRq searchRq) {
        Integer page = searchRq.getPage();
        Integer pageSize = searchRq.getPageSize();
        page = page == null ? 0 : page;
        pageSize = pageSize == null ? 0 : pageSize;
        sourceBuilder.from((page - 1) * pageSize);
        sourceBuilder.size(pageSize);
    }

}
