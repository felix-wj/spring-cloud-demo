package cn.wangjie.dao.base;

import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;


/**
 * @author ashui
 * @desc 2018.5.4 插入后返回id insertUseGeneratedKeys
 * 2018.5.5 1 插入非空字段后返回id  insertSelectiveGenKey  2 增加 selectByKey ，解决数据库字段为关键字，在字段加 `
 * 2018.11.12 增加 CustomSelectExampleMapper selectByExample 方法时字段为关键字时报错
 * @date 2018/7/2
 */
public interface IMyMapper<T> extends
        Mapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>,
        InsertUseGeneratedKeysMapper<T>,
        CustomSelectMapper<T>,
        GenKeyInsertMapper<T>,
        CustomSelectExampleMapper<T>,
        CustomUpdateByPrimaryKeySelectiveMapper<T>{



    @InsertProvider(
            type = CustomInsertProvider.class,
            method = "dynamicSQL"
    )
    /**
     * @desc 解决insertSelective字段为关键字时报错
     * @author ashui
     * @date 2018/11/13
     */
    int insertSelectiveWithCustom(T t);

    @InsertProvider(
            type = CustomInsertProvider.class,
            method = "dynamicSQL"
    )
    /**
     * @desc 解决insertSelective字段为关键字时报错
     * @author ashui
     * @date 2018/11/13
     */
    int insertWithCustomForTableMonth(T t);



}
