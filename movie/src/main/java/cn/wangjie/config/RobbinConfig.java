package cn.wangjie.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloudDemo
 * @description:
 * @author: WangJie
 * @create: 2018-10-26 20:14
 **/
@Configuration
public class RobbinConfig {

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
