package cn.wangjie.dao.base;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author zyang
 * @date 2018/11/27
 * @desc 根据主键更新属性不为null的值
 */
public interface CustomUpdateByPrimaryKeySelectiveMapper<T> {

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    /**
     * @author zyang
     * @date 2018/11/27
     * @desc 根据主键更新属性不为null的值，解决字段为关键字时报错
     */
    @UpdateProvider(type = CustomUpdateByPrimaryKeySelectiveProvider.class, method = "dynamicSQL")
    @Options(useCache = false, useGeneratedKeys = false)
    int updateByPrimaryKeySelectiveWithCustom(T t);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    /**
     * @author zyang
     * @date 2018/11/27
     * @desc 根据主键更新属性不为null的值，解决字段为关键字时报错
     */
    @UpdateProvider(type = CustomUpdateByPrimaryKeySelectiveProvider.class, method = "dynamicSQL")
    @Options(useCache = false, useGeneratedKeys = false)
    int updateByPrimaryKeyWithCustomForTableMonth(T t);

}
