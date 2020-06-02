package cn.wangjie.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.naming.Name;
import javax.sql.DataSource;

/**
 * @program: spring-cloud-demo
 * @description:
 * @author: WangJie
 * @create: 2020-05-17 17:36
 **/
@Data
@ConditionalOnProperty(name = "spring.datasource.druid.paperpushsystem.url", matchIfMissing = false)
@org.springframework.context.annotation.Configuration
public class PaperPushSystemDBConfig extends DataSourceConfigurer {

    @Bean(name = DB_PPS)
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.paperpushsystem")
    public DataSource paperPushSystemDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public Configuration paperPushSystemConfiguration() {
        Configuration configuration = new Configuration();
        //开启驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    @Bean(DB_PPS+"SqlSessionFactory")
    @Primary
    public SqlSessionFactory paperPushSystemSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(paperPushSystemDataSource());
        sqlSessionFactoryBean.setConfiguration(paperPushSystemConfiguration());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/"+DB_PPS+"/*.xml"));
        setPlugins(sqlSessionFactoryBean);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(PPS_TRANSACTION_MANAGER)
    @Primary
    public DataSourceTransactionManager paperPushSystemTransactionManager() {
        return new DataSourceTransactionManager(paperPushSystemDataSource());
    }


    @ConditionalOnProperty(name = "spring.datasource.druid.paperpushsystem.url", matchIfMissing = false)
    @org.springframework.context.annotation.Configuration
    @AutoConfigureAfter(name=DB_PPS+"SqlSessionFactory")
    public static class MyBatisMapperScannerConfigurer {

        @Bean
        public MapperScannerConfigurer paperpushsystemScannerConfigurer() {
            return scannerConfigurer(DB_PPS);
        }
    }
}
