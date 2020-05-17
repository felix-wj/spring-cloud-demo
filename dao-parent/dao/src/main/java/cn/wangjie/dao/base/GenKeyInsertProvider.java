package cn.wangjie.dao.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.Iterator;
import java.util.Set;

public class GenKeyInsertProvider extends BaseInsertProvider {

    public GenKeyInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }



    public String insertSelectiveGenKey(MappedStatement ms) {
        return super.insertSelective(ms);
    }


    public String insertListWithCustom(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(insertColumns(entityClass, true, false, false));
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if (!column.isId() && column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }


    public static String insertColumns(Class<?> entityClass, boolean skipId, boolean notNull, boolean notEmpty) {
        StringBuilder sql = new StringBuilder();
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var6 = columnList.iterator();

        while(true) {
            EntityColumn column;
            do {
                do {
                    if (!var6.hasNext()) {
                        sql.append("</trim>");
                        return sql.toString();
                    }

                    column = (EntityColumn)var6.next();
                } while(!column.isInsertable());
            } while(skipId && column.isId());

            if (notNull) {
                sql.append(SqlHelper.getIfNotNull(column, "`"+column.getColumn() + "`,", notEmpty));
            } else {
                sql.append("`"+column.getColumn() + "`,");
            }
        }
    }
}
