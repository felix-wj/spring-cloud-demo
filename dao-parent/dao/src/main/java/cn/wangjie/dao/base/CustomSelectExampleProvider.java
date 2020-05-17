package cn.wangjie.dao.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

import java.util.Iterator;
import java.util.Set;

public class CustomSelectExampleProvider extends BaseSelectProvider {

    public CustomSelectExampleProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }



    public String selectByExampleWithCustom(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder("SELECT ");
        if (this.isCheckExampleEntityClass()) {
            sql.append(SqlHelper.exampleCheck(entityClass));
        }

        sql.append("<if test=\"distinct\">distinct</if>");
        sql.append(exampleSelectColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.exampleWhereClause());
        sql.append(SqlHelper.exampleOrderBy(entityClass));
        sql.append(SqlHelper.exampleForUpdate());
        return sql.toString();
    }

    public static String exampleSelectColumns(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"@tk.mybatis.mapper.util.OGNL@hasSelectColumns(_parameter)\">");
        sql.append("<foreach collection=\"_parameter.selectColumns\" item=\"selectColumn\" separator=\",\">");
        sql.append("${selectColumn}");
        sql.append("</foreach>");
        sql.append("</if>");
        sql.append("<if test=\"@tk.mybatis.mapper.util.OGNL@hasNoSelectColumns(_parameter)\">");
        sql.append(getAllColumns(entityClass));
        sql.append("</if>");
        return sql.toString();
    }


    public static String getAllColumns(Class<?> entityClass) {
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        StringBuilder sql = new StringBuilder();
        Iterator var3 = columnList.iterator();

        while(var3.hasNext()) {
            EntityColumn entityColumn = (EntityColumn)var3.next();
            sql.append('`').append(entityColumn.getColumn()).append("`,");
        }

        return sql.substring(0, sql.length() - 1);
    }

}
