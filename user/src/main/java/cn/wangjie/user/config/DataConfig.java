package cn.wangjie.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: spring-cloud-demo
 * @description:
 * @author: WangJie
 * @create: 2020-05-09 23:02
 **/
@Data
@Component
@ConfigurationProperties(prefix = "data")
public class DataConfig {

    private String env;
    private UserInfo user;

    @Data
    public static class UserInfo{
        private String username;
        private String password;
    }
}
