package cn.wangjie.elasticsearch.repository;

import cn.wangjie.elasticsearch.core.provider.BaseSearchProvider;
import cn.wangjie.elasticsearch.entity.request.SearchRq;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-12-06 16:31
 **/
@Component
public class MeetRepository extends BaseSearchProvider {
    public MeetRepository() {
        super("meet", "meet");
    }

    @Override
    protected Map<String, Object> builderDataWithHit(SearchHit hitsHit) {
        return null;
    }

    @Override
    protected void buildSort(SearchSourceBuilder sourceBuilder, SearchRq searchRq) {

    }

    @Override
    protected void buildParam(SearchSourceBuilder sourceBuilder, SearchRq searchRq) {

    }
}
