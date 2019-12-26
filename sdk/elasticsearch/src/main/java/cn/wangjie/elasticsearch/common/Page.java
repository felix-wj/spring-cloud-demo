package cn.wangjie.elasticsearch.common;

import lombok.Data;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-12-06 16:37
 **/
@Data
public class Page<T> {

    private List<T> result;

    private Integer pageSize;

    private Integer pages;

    private Integer total;

    public Page(List<T> result,int total,int pageSize) {
        this.result = result;
        this.total = total;
        this.pageSize = pageSize;
        pages = calculatePages(total,pageSize);
    }
    private int calculatePages(int total , int pageSize){
        return total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
    }
}
