package cn.wangjie.user.controller;

import cn.wangjie.user.aspect.annotation.LogAnnotation;
import cn.wangjie.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Value("${psw}")
    private String psw;
    @Value("${name}")
    private String name;
    @Value("${server.port}")
    private Integer port;
    @Autowired
    private RestTemplate restTemplate;
    @LogAnnotation(note = "getUser")
    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(port);
        return user;
    }
}
