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
```xml
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

## ribbon 服务端负载均衡配置
在引入eureka的包时已经引入了ribbon的start包。
直接在RestTemplate上加@LoadBalanced注解
```java
public class MovieApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
   
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
```
调用服务接口
```java
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return  this.restTemplate.getForObject("http://spring-cloud-user/user/get/"+id,User.class);
    }

}
```
### 配置随机均衡策略
```java
@Configuration
public class RobbinConfig {

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
```
此处需注意RobbinConfig类不能被自动扫描到，否则会覆盖所有服务的均衡策略。
即放在启动类同目录或子包下需要指定配置类不被扫描。在启动类上加入以下注解。并在配置类上加入自定义注解。
```java
//规定带某种自定义注解的类不被扫描
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
```
在启动类上加入注解，指定均很策略适用于那个服务。
```java
@RibbonClient(name = "spring-cloud-user", configuration = RobbinConfig.class);
```

## feign 使用REST风格调用服务
首先引入jar包
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
```
之后开始创建api调用接口。
```java
@FeignClient(name = "spring-cloud-user" , fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
    @RequestMapping(value = "/user/get/{id}",method = RequestMethod.GET)
    User getUser(@PathVariable("id") Integer id);
}
```
fallback指定断路情况下返回的数据,实现相对应的接口方法，可以进行服务降级等操作。
```java
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("feign fallback");
        return user;
    }
}
```
需要在配置文件中指定开启feign的hystrix支持才能生效。
```yaml
feign:
  hystrix:
    enabled: true
```

## hystrix 断路器
引入jar包
```xml
        <!--断路器-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>

```
配置文件添加web访问点
```yaml
management:
  endpoints:
    web:
      exposure:
        include: hystrix.stream
```
访问url为ip:port/actuator/hystrix.stream

### 配置断路器UI界面
新建一个工程，导入jar包
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
        </dependency>
```
启动类加入注解@EnableHystrixDashboard，启动项目。
在界面中输入其他项目的hystrix.stream。例如http://localhost:20002/actuator/hystrix.stream，即可监控该项目。

### 部署turbine显示注册到eureka上的服务集群的接口调用情况
新建一个工程，导入jar包
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-turbine</artifactId>
        </dependency>
```
将项目注册到eureka。
配置获取hystrix.stream的应用
```yaml
turbine:
  aggregator:
    clusterConfig: default
  appConfig: movie,spring-cloud-user
  cluster-name-expression: "'default'"
```
在appConfig中加入服务名即可。

启动类加上@EnableTurbine注解，启动项目。
启动hystrix项目，输入turbine项目的id:port/turbine.stream?cluster=default即可。

## 多数据源

[多数据源配置文档](dao-parent/README.md)