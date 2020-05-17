package cn.wangjie.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;

/**
 * @program: spring-cloud-demo
 * @description:
 * @author: WangJie
 * @create: 2020-05-17 17:36
 **/
@ConditionalOnProperty(name = "spring.datasource.paperpushsystem.url",matchIfMissing = false)
@org.springframework.context.annotation.Configuration
public class PaperPushSystemDBConfig extends DataSourceConfigurer {

    @Bean(name = DB_PPS)
    @ConfigurationProperties(prefix = "spring.datasource.paperpushsystem")
    public DataSource paperPushSystemDataSource(){
        return new DruidDataSource();
    }
    @Bean
    public Configuration paperPushSystemConfiguration(){
        Configuration configuration = new Configuration();
        //开启驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }
    @Bean("paperPushSystemSqlSessionFactory")
    public SqlSessionFactory paperPushSystemSqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(paperPushSystemDataSource());
        sqlSessionFactoryBean.setConfiguration(paperPushSystemConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/action/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    @Bean("paperPushSystemTransactionManager")
    public DataSourceTransactionManager paperPushSystemTransactionManager(){
        return new DataSourceTransactionManager(paperPushSystemDataSource());
    }

    @ConditionalOnProperty(name = "spring.datasource.paperpushsystem.url", matchIfMissing = false)
    @org.springframework.context.annotation.Configuration
    @AutoConfigureAfter(DataSourceConfigurer.class)
    public static class MyBatisMapperScannerConfigurer {

        @Bean
        public MapperScannerConfigurer actionScannerConfigurer() {
            return scannerConfigurer(DB_PPS);
        }
    }
}
