package cn.wangjie.dao.base;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @desc 插入优化
 * 1 插入不为null字段，并返回主键
 * 2 解决insertList字段为关键字时报错
 * @author ashui
 * @date 2019/4/29
 */
public interface GenKeyInsertMapper<T> {

    /** 插入不为null字段，并返回主键
     * @param model
     * @return
     */
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @InsertProvider(
            type = GenKeyInsertProvider.class,
            method = "dynamicSQL"
    )
    int insertSelectiveGenKey(T model);


    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @InsertProvider(
            type = GenKeyInsertProvider.class,
            method = "dynamicSQL"
    )
    /**
     * @desc  解决insertList字段为关键字时报错
     * @author ashui
     * @date 2018/11/13
     */
    int insertListWithCustom(List<T> var1);
}
