package cn.wangjie.dao.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseSelectProvider;

import java.util.Iterator;
import java.util.Set;

public class CustomSelectProvider extends BaseSelectProvider {

    public CustomSelectProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }



    public String selectByKey(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.wherePKColumns(entityClass));
        return sql.toString();
    }

    public static String selectAllColumns(Class<?> entityClass) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append(getAllColumns(entityClass));
        sql.append(" ");
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
