## AOP
### 一些方法注解的用处
1. @Before 进入方法之前，处理日志
2. @Around 环绕方法，可以修改入参和返回数据
3. @After  在返回数据之前，处理日志，不影响返回数据
4. @AfterReturning 可以拿到返回数据，但不能替换返回数据即修改返回数据地址，只能改变返回数据的内部结构数据。
### 执行顺序
- 同一个切面内的方法 @Around->@Before->@Around->@After->@AfterReturning
- 不同的切面，通过类上的@Order注解设置优先级，数值越大，优先级越低。默认值为Integer.MAX_VALUE。
### 切面注解
不再通过类名方法名指定切面，但是需要在具体方法上使用注解指定切面。

## jasypt对配置文件进行加解密
https://blog.csdn.net/more_try/article/details/82389532

## 配置文件动态刷新
maven引入jar包
```pom
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-bus-amqp</artifactId>
  </dependency>
```
分别在config和需要动态刷新的工程的yml文件中添加
```yml
management:
  endpoints:
    web:
      exposure:
        include: bus-refresh
spring:
  rabbitmq:
    host: 118.25.87.131
    port: 5672
    username: wangjie
    password: ENC(h0BQetxrh2zZ19UUMeUVrq3LTUS65uwn)
```
在类上加注解@RefreshScope，允许该类引入的属性动态刷新。
当配置变化后，通过ip:port/actuator/bus-refresh刷新相关项目的配置。注意ip和port为需要更新配置的项目的地址。

