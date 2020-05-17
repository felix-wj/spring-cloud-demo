package cn.wangjie.dao.base;


import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseUpdateProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

/**
 * @author zyang
 * @date 2018/11/27
 * @desc
 */
public class CustomUpdateByPrimaryKeySelectiveProvider extends BaseUpdateProvider {
    public CustomUpdateByPrimaryKeySelectiveProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String updateByPrimaryKeySelectiveWithCustom(MappedStatement ms) {
//        Class<?> entityClass = getEntityClass(ms);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
//        StringBuilder sql = new StringBuilder();
//        sql.append("<set>");
//        //获取全部列
//        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
//        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
//        for (EntityColumn column : columnList) {
//            if (!column.isId() && column.isUpdatable()) {
//                String colStr = "`" + column.getColumn() + "`" + " = " + column.getColumnHolder(null) + ",";
//                sql.append(SqlHelper.getIfNotNull(null, column, colStr, isNotEmpty()));
//            }
//        }
//        sql.append("</set>");
//        stringBuilder.append(sql);
//        stringBuilder.append(SqlHelper.wherePKColumns(entityClass));
//        return stringBuilder.toString();
        Class<?> entityClass = getEntityClass(ms);
        return updateByTableName(ms, tableName(entityClass));
    }

    public String updateByPrimaryKeyWithCustomForTableMonth(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        return updateByTableName(ms, tableName(entityClass) + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM")));
    }

    private String updateByTableName(MappedStatement ms, String tableName) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SqlHelper.updateTable(entityClass, tableName));
        StringBuilder sql = new StringBuilder();
        sql.append("<set>");
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnList) {
            if (!column.isId() && column.isUpdatable()) {
                String colStr = "`" + column.getColumn() + "`" + " = " + column.getColumnHolder(null) + ",";
                sql.append(SqlHelper.getIfNotNull(null, column, colStr, isNotEmpty()));
            }
        }
        sql.append("</set>");
        stringBuilder.append(sql);
        stringBuilder.append(SqlHelper.wherePKColumns(entityClass));
        return stringBuilder.toString();
    }

}
