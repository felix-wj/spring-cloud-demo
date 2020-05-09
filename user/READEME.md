
## 配置优先级
application-env.yml>application.yml>bootstrap.yml>配置中心的配置

## 自定义元数据
[参考链接](https://juejin.im/post/5d6a3250518825618e67a4a9)

pom
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

[配置元数据json文件](src\main\resources\META-INF\additional-spring-configuration-metadata.json)

## ConfigurationProperties
[配置类](src\main\java\cn\wangjie\user\config\DataConfig.java)
注意内部类需要static修饰，否则数据无法注入

