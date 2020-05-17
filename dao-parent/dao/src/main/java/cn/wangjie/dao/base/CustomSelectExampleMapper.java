package cn.wangjie.dao.base;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @desc 解决用 selectByExample 方法时字段为关键字时报错
 * @author ashui
 * @date 2019/4/29
 */
public interface CustomSelectExampleMapper<T> {

    /**根据主键查询
     * 解决用 selectByExample 方法时字段为关键字时报错
     * @param o
     * @return
     */
    @SelectProvider(
            type = CustomSelectExampleProvider.class,
            method = "dynamicSQL"
    )
    List<T> selectByExampleWithCustom(Object o);
}
