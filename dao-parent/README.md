### 参数配置
```yaml
spring:
  datasource:
    # 使用阿里的Druid连接池
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    druid:
      paperpushsystem:
        url: 
        username: 
        password: 
      propertymanagement:
        username: 
        password: 
        url: 
      # 连接池的配置信息
      # 初始化大小，最小，最大
      initial-size: 5
      min-idle: 5
      maxActive: 20
      # 配置获取连接等待超时的时间
      maxWait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      timeBetweenEvictionRunsMillis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      minEvictableIdleTimeMillis: 300000
      validationQuery: SELECT 1
      testWhileIdle: true
      testOnBorrow: false
      testOnReturn: false
      # 打开PSCache，并且指定每个连接上PSCache的大小
      poolPreparedStatements: true
      maxPoolPreparedStatementPerConnectionSize: 20
      # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      filters: stat,wall,slf4j
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connectionProperties: druid.stat.mergeSql\=true;druid.stat.slowSqlMillis\=5000
      # 配置DruidStatFilter
      web-stat-filter:
        enabled: true
        url-pattern: "/*"
        exclusions: "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"
      # 配置DruidStatViewServlet
      stat-view-servlet:
        url-pattern: "/druid/*"
        # IP白名单(没有配置或者为空，则允许所有访问)
        allow: 127.0.0.1,192.168.163.1
        # IP黑名单 (存在共同时，deny优先于allow)
        deny: 192.168.1.73
        #  禁用HTML页面上的“Reset All”功能
        reset-enable: false
        # 登录名
        login-username: admin
        # 登录密码
        login-password: 123456
      use-global-data-source-stat: true

```

### 数据源

#### 数据库名称等基础配置
```java
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
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
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

```

#### 数据库1

```java
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
```


#### 数据库2

```java
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

```

注意事项：
1. 使用@Primary设置默认数据源和事务,这样在使用@Transactional时，如果操作的是默认数据库，可以不用指定事务管理器transactionManager，而对其他库使用事务时需要明确指定对应的transactionManager。
2. 要注意各自的SqlSessionFactory扫描各自的xml文件和dao接口，这一点可以通过包名区分开。


### 事务
启动类上使用@EnableTransactionManagement

使用@Transaction注解开启事务
```java

    @Test
    @Transactional
    public void transactionTest1(){
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName("testTransaction");
        authorDao.insert(authorModel);
        throw new RuntimeException();
    }

    @Test
    @Transactional(transactionManager = DataSourceConfigurer.PM_TRANSACTION_MANAGER)
    public void transactionTest2(){
        NoteModel noteModel = noteDao.selectByPrimaryKey(1);
        noteModel.setId(null);
        noteDao.insert(noteModel);
        throw new RuntimeException();
    }

```