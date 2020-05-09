package cn.wangjie.user.controller;

import cn.wangjie.user.aspect.annotation.LogAnnotation;
import cn.wangjie.user.config.DataConfig;
import cn.wangjie.user.entity.Movie;
import cn.wangjie.user.entity.User;
import cn.wangjie.user.feignclient.MovieFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloudDemo
 * @description: 用户controller
 * @author: WangJie
 * @create: 2018-07-08 14:08
 **/
@RestController
@RefreshScope
public class UserController {

    @Value("${server.port}")
    private Integer port;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MovieFeignClient movieFeignClient;
    @Autowired
    private DataConfig dataConfig;
    @LogAnnotation(note = "getUser")
    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setAge(port);
        return user;
    }
    @GetMapping("/show")
    public DataConfig show(){
        return dataConfig;
    }


    @LogAnnotation(note = "getMovie")
    @GetMapping("/movie/get/{id}")
    public Movie getMovie(@PathVariable("id") Integer id){

        return movieFeignClient.getMovie(id);
    }

}
