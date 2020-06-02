package cn.wangjie.dao.plugin.mybatis;

import com.sun.deploy.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

@Intercepts({
        @Signature(type = Executor.class,method = "update",args = {
                MappedStatement.class,Object.class
        }),
        @Signature(type = Executor.class,method="query",args = {
                MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class
        })
})
@Slf4j
public class MybatisSqlLoggingInterceptor implements Interceptor {

    private static final Pattern WHITE_SPACE_BLOCK_PATTERN = Pattern
            .compile("([\\s]{2,}|[\\t\\r\\n])");
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        Object returnValue = null;
        long start = System.currentTimeMillis();
        // 执行目标方法
        returnValue = invocation.proceed();
        // 统计 SQL 耗时
        long end = System.currentTimeMillis();
        long time = end - start;

        // 打印日志
        logSqlInfo(configuration, boundSql, sqlId, time);

        return returnValue;
    }
    public static void logSqlInfo(Configuration configuration, BoundSql boundSql, String sqlId,
                                  long time) {

        String sqlAndParams = showSqlAndParams(configuration, boundSql);

        StringBuilder str = new StringBuilder(100);
        str.append(sqlId);
        str.append(":");
        str.append(sqlAndParams);
        str.append(":");
        str.append(time);
        str.append("ms");

        String line = str.toString();

        log.info(line);
    }
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            value = "'" + LocalDateTime.ofInstant(((Date)obj).toInstant(), ZoneId.systemDefault()).format(DateTimeFormatter.BASIC_ISO_DATE) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    public static String showSqlAndParams(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        String sql = WHITE_SPACE_BLOCK_PATTERN.matcher(boundSql.getSql()).replaceAll(" ");

        List<String> params = new ArrayList<>();

        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                params.add(getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        params.add(getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        params.add(getParameterValue(obj));
                    }
                }
            }
        }

        return sql + ":[" + StringUtils.join(params, ",") + "]";
    }
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
