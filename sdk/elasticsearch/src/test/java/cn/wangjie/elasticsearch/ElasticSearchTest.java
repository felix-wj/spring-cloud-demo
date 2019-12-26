package cn.wangjie.elasticsearch;

import cn.wangjie.elasticsearch.model.EmployeeModel;
import cn.wangjie.elasticsearch.repository.EmployeeESRepository;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-07-30 17:50
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Autowired
    private EmployeeESRepository employeeESRepository;

    @Test
    public void test() throws ExecutionException, InterruptedException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("about","rock climbing");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gt("30");
        boolQueryBuilder.must(matchQueryBuilder).filter(rangeQueryBuilder);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        SearchRequest searchRequest = new SearchRequest("megacorp").types("employee").source(searchSourceBuilder);
        ActionFuture<SearchResponse> actionFuture= esTemplate.getClient().search(searchRequest);
        SearchResponse response = actionFuture.get();
        SearchHits searchHits = response.getHits();
        if (searchHits !=null){
            for(SearchHit hit :searchHits.getHits()){
                Map<String,Object> map = hit.getSource();
                System.out.println(JSON.toJSONString(map));
            }
        }
    }

    @Test
    public void test2(){
        List<EmployeeModel> list = employeeESRepository.getEmp("smith",20);
        for (EmployeeModel employeeModel:list){
            System.out.println(employeeModel.toString());
        }
    }

    @Test
    public void testTokenizer(){
        String index = "my_index";
        String text = "莫忘记，人类情感上最大的需要是感恩。";
        AnalyzeRequestBuilder analyzeRequestBuilder = new AnalyzeRequestBuilder(esTemplate.getClient(), AnalyzeAction.INSTANCE).setTokenizer("icu_tokenizer").setText(text);

        List<AnalyzeResponse.AnalyzeToken> tokenList = analyzeRequestBuilder.execute().actionGet().getTokens();
        tokenList.forEach(ikToken -> {
            System.out.println(ikToken.getTerm());
        });
    }
}
