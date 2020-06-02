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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;

@Data
@org.springframework.context.annotation.Configuration
@ConditionalOnProperty(name = "spring.datasource.druid.propertymanagement.url", matchIfMissing = false)
public class PropertyManagementDBConfig extends DataSourceConfigurer {

    @Bean(name = DB_PM)
    @ConfigurationProperties(prefix ="spring.datasource.druid.propertymanagement")
    DataSource dataSource(){
        return new DruidDataSource();
    }

    @Bean
    public Configuration configuration() {
        Configuration configuration = new Configuration();
        //开启驼峰
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }
    @Bean(name = DB_PM+"SqlSessionFactory")
    public SqlSessionFactory sessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfiguration(configuration());
        setPlugins(sqlSessionFactoryBean);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/"+DB_PM+"/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(PM_TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @ConditionalOnProperty(name = "spring.datasource.druid.propertymanagement.url", matchIfMissing = false)
    @org.springframework.context.annotation.Configuration
    @AutoConfigureAfter(name = DB_PM+"SqlSessionFactory")
    public static class MyBatisMapperScannerConfigurer {

        @Bean
        public MapperScannerConfigurer propertymanagementScannerConfigurer() {
            return scannerConfigurer(DB_PM);
        }
    }
}
