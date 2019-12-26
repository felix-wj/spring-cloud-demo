package cn.wangjie.elasticsearch.entity.request;

import lombok.Data;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-12-06 16:43
 **/
@Data
public class SearchRq  {
    /**
     * 页数
     */
    private Integer page;

    /**
     * 页大小
     */
    private Integer pageSize;
    /**
     * 搜索词
     */
    private String keyword;
}
