package cn.wangjie.elasticsearch.core.provider;

import cn.wangjie.elasticsearch.entity.request.SearchRq;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-12-27 11:02
 **/
@Repository
public class EmployeeSearch extends BaseSearchProvider {

    public EmployeeSearch() {
        super("megacorp", "employee");
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
