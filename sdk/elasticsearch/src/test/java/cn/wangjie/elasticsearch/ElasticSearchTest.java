package cn.wangjie.elasticsearch;

import cn.wangjie.elasticsearch.core.provider.EmployeeSearch;
import cn.wangjie.elasticsearch.model.EmployeeModel;
import cn.wangjie.elasticsearch.repository.EmployeeESRepository;
import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.common.xcontent.XContentType;
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

import java.util.Arrays;
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
    
    @Autowired
    private EmployeeSearch employeeSearch;

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

    String index = "megacorp";
    String type = "employee";
    @Test
    public void testInsert(){
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setAbout("I like to play games");
        employeeModel.setAge(21);
        employeeModel.setId(4);
        employeeModel.setFirstName("tom");
        employeeModel.setLastName("cat");
        employeeModel.setInterests(Arrays.asList("music","forestry","sport"));
        IndexRequest indexRequest = new IndexRequest(index,type,"4");
        indexRequest.source(JSON.toJSONString(employeeModel), XContentType.JSON);
        ActionFuture<IndexResponse> index = esTemplate.getClient().index(indexRequest);
        IndexResponse indexResponse = index.actionGet();
        System.out.println(indexResponse.toString());
    }

    @Test
    public void testDelete(){
        DeleteRequest deleteRequest = new DeleteRequest(index,type,"4");
        ActionFuture<DeleteResponse> delete = esTemplate.getClient().delete(deleteRequest);
        DeleteResponse deleteResponse = delete.actionGet();
        System.out.println(deleteResponse.toString());
    }
    @Test
    public void testGet(){
        GetRequest getRequest = new GetRequest(index,type,"4");
        ActionFuture<GetResponse> getResponseActionFuture = esTemplate.getClient().get(getRequest);
        GetResponse getFields = getResponseActionFuture.actionGet();
        EmployeeModel employeeModel = JSON.parseObject(getFields.getSourceAsString(),EmployeeModel.class);
        System.out.println(employeeModel);
    }
    @Test
    public void testUpdate(){
        UpdateRequest updateRequest = new UpdateRequest();
    }
    @Test
    public void testSearch(){
        SearchRequest searchRequest = new SearchRequest(index,type);
        ActionFuture<SearchResponse> search = esTemplate.getClient().search(searchRequest);
        SearchResponse searchResponse = search.actionGet();
        SearchHits hits = searchResponse.getHits();
        long totalHits = hits.totalHits;
        if (totalHits>0){
            for (SearchHit hit : hits.getHits()) {
                System.out.println(hit.getSource());
            }
        }
        
    }
}
