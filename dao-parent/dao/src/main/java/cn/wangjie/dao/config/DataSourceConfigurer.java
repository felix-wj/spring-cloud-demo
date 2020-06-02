package cn.wangjie.dao.config;

import cn.wangjie.dao.plugin.mybatis.MybatisSqlLoggingInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

public class DataSourceConfigurer {

    public static final String DB_PPS = "paperpushsystem";
    public static final String DB_PM = "propertymanagement";


    public static final String PPS_TRANSACTION_MANAGER = "paperpushsystemTransactionManager";
    public static final String PM_TRANSACTION_MANAGER = "propertyManagementTransactionManager";

    public static void setPlugins(SqlSessionFactoryBean sqlSessionFactoryBean) {
        //配置分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("reasonable", "false");//页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
        pageHelper.setProperties(properties);
        //添加插件
        MybatisSqlLoggingInterceptor mybatisSqlLoggingInterceptor = new MybatisSqlLoggingInterceptor();
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper,mybatisSqlLoggingInterceptor});
    }

    public static MapperScannerConfigurer scannerConfigurer(String db) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(db + "SqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("cn.wangjie.dao.mapper." + db);
        //配置通用mappers
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
