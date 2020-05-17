package cn.wangjie.dao.base;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;
import tk.mybatis.mapper.util.StringUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

/**
 * 解决insertSelective字段为关键字时报错
 *
 * @author ashui
 * @date 2018/11/13
 */
public class CustomInsertProvider extends BaseInsertProvider {

    public CustomInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }


    public String insertSelectiveWithCustom(MappedStatement ms) {
//        Class<?> entityClass = this.getEntityClass(ms);
//        StringBuilder sql = new StringBuilder();
//        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
//        Boolean hasIdentityKey = false;
//        Iterator var6 = columnList.iterator();
//
//        EntityColumn column;
//        label95:
//        do {
//            while(true) {
//                while(true) {
//                    do {
//                        do {
//                            if (!var6.hasNext()) {
//                                sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
//                                sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
//                                var6 = columnList.iterator();
//
//                                while(true) {
//                                    while(true) {
//                                        do {
//                                            if (!var6.hasNext()) {
//                                                sql.append("</trim>");
//                                                sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
//                                                var6 = columnList.iterator();
//
//                                                while(var6.hasNext()) {
//                                                    column = (EntityColumn)var6.next();
//                                                    if (column.isInsertable()) {
//                                                        if (column.isIdentity()) {
//                                                            sql.append(SqlHelper.getIfCacheNotNull(column, column.getColumnHolder((String)null, "_cache", ",")));
//                                                        } else {
//                                                            sql.append(SqlHelper.getIfNotNull(column, column.getColumnHolder((String)null, (String)null, ","), this.isNotEmpty()));
//                                                        }
//
//                                                        if (StringUtil.isNotEmpty(column.getSequenceName())) {
//                                                            sql.append(SqlHelper.getIfIsNull(column, this.getSeqNextVal(column) + " ,", this.isNotEmpty()));
//                                                        } else if (column.isIdentity()) {
//                                                            sql.append(SqlHelper.getIfCacheIsNull(column, column.getColumnHolder() + ","));
//                                                        } else if (column.isUuid()) {
//                                                            sql.append(SqlHelper.getIfIsNull(column, column.getColumnHolder((String)null, "_bind", ","), this.isNotEmpty()));
//                                                        }
//                                                    }
//                                                }
//
//                                                sql.append("</trim>");
//                                                return sql.toString();
//                                            }
//
//                                            column = (EntityColumn)var6.next();
//                                        } while(!column.isInsertable());
//
//                                        if (!StringUtil.isNotEmpty(column.getSequenceName()) && !column.isIdentity() && !column.isUuid()) {
//                                            sql.append(SqlHelper.getIfNotNull(column, "`"+column.getColumn() + "`,", this.isNotEmpty()));
//                                        } else {
//                                            sql.append("`"+column.getColumn() + "`,");
//                                        }
//                                    }
//                                }
//                            }
//
//                            column = (EntityColumn)var6.next();
//                        } while(!column.isInsertable());
//                    } while(StringUtil.isNotEmpty(column.getSequenceName()));
//
//                    if (column.isIdentity()) {
//                        sql.append(SqlHelper.getBindCache(column));
//                        if (hasIdentityKey.booleanValue()) {
//                            continue label95;
//                        }
//
//                        this.newSelectKeyMappedStatement(ms, column);
//                        hasIdentityKey = true;
//                    } else if (column.isUuid()) {
//                        sql.append(SqlHelper.getBindValue(column, this.getUUID()));
//                    }
//                }
//            }
//        } while(column.getGenerator() != null && "JDBC".equals(column.getGenerator()));
//
//        throw new MapperException(ms.getId() + "对应的实体类" + entityClass.getCanonicalName() + "中包含多个MySql的自动增长列,最多只能有一个!");
        Class<?> entityClass = getEntityClass(ms);
        return insertByTableName(ms, tableName(entityClass));
    }

    public String insertWithCustomForTableMonth(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);

        return insertByTableName(ms, tableName(entityClass) + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM")));
    }

    private String insertByTableName(MappedStatement ms, String tableName) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Boolean hasIdentityKey = false;
        Iterator var6 = columnList.iterator();
        tableName = StringUtil.isNotEmpty(tableName) ? tableName : tableName(entityClass);
        EntityColumn column;
        label95:
        do {
            while (true) {
                while (true) {
                    do {
                        do {
                            if (!var6.hasNext()) {
                                sql.append(SqlHelper.insertIntoTable(entityClass, tableName));
                                sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
                                var6 = columnList.iterator();

                                while (true) {
                                    while (true) {
                                        do {
                                            if (!var6.hasNext()) {
                                                sql.append("</trim>");
                                                sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
                                                var6 = columnList.iterator();

                                                while (var6.hasNext()) {
                                                    column = (EntityColumn) var6.next();
                                                    if (column.isInsertable()) {
                                                        if (column.isIdentity()) {
                                                            sql.append(SqlHelper.getIfCacheNotNull(column, column.getColumnHolder((String) null, "_cache", ",")));
                                                        } else {
                                                            sql.append(SqlHelper.getIfNotNull(column, column.getColumnHolder((String) null, (String) null, ","), this.isNotEmpty()));
                                                        }

                                                        if (StringUtil.isNotEmpty(column.getSequenceName())) {
                                                            sql.append(SqlHelper.getIfIsNull(column, this.getSeqNextVal(column) + " ,", this.isNotEmpty()));
                                                        } else if (column.isIdentity()) {
                                                            sql.append(SqlHelper.getIfCacheIsNull(column, column.getColumnHolder() + ","));
                                                        } else if (column.isUuid()) {
                                                            sql.append(SqlHelper.getIfIsNull(column, column.getColumnHolder((String) null, "_bind", ","), this.isNotEmpty()));
                                                        }
                                                    }
                                                }

                                                sql.append("</trim>");
                                                return sql.toString();
                                            }

                                            column = (EntityColumn) var6.next();
                                        } while (!column.isInsertable());

                                        if (!StringUtil.isNotEmpty(column.getSequenceName()) && !column.isIdentity() && !column.isUuid()) {
                                            sql.append(SqlHelper.getIfNotNull(column, "`" + column.getColumn() + "`,", this.isNotEmpty()));
                                        } else {
                                            sql.append("`" + column.getColumn() + "`,");
                                        }
                                    }
                                }
                            }

                            column = (EntityColumn) var6.next();
                        } while (!column.isInsertable());
                    } while (StringUtil.isNotEmpty(column.getSequenceName()));

                    if (column.isIdentity()) {
                        sql.append(SqlHelper.getBindCache(column));
                        if (hasIdentityKey.booleanValue()) {
                            continue label95;
                        }

                        this.newSelectKeyMappedStatement(ms, column);
                        hasIdentityKey = true;
                    } else if (column.isUuid()) {
                        sql.append(SqlHelper.getBindValue(column, this.getUUID()));
                    }
                }
            }
        } while (column.getGenerator() != null && "JDBC".equals(column.getGenerator()));

        throw new MapperException(ms.getId() + "对应的实体类" + entityClass.getCanonicalName() + "中包含多个MySql的自动增长列,最多只能有一个!");
    }


}
