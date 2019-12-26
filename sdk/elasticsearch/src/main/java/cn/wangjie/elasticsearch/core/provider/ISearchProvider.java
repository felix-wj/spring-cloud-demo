package cn.wangjie.elasticsearch.core.provider;


import cn.wangjie.elasticsearch.common.Page;
import cn.wangjie.elasticsearch.entity.request.SearchRq;

import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-12-06 16:21
 **/
public interface ISearchProvider {

    Page<Map<String,Object>> search(SearchRq searchRq);
}
