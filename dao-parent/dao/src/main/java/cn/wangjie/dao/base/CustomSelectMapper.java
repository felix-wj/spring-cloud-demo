package cn.wangjie.dao.base;

import org.apache.ibatis.annotations.SelectProvider;

/**
 * @desc 解决用 selectByPrimaryKey 方法时字段为关键字时报错
 * @author ashui
 * @date 2018/5/29
 */
public interface CustomSelectMapper<T> {

    /**根据主键查询
     * 解决用 selectByPrimaryKey 方法时字段为关键字时报错
     * @author ashui
     * @date 2018/5/29
     * @return
     */
    @SelectProvider(
            type = CustomSelectProvider.class,
            method = "dynamicSQL"
    )
    T selectByKey(Object o);
}
