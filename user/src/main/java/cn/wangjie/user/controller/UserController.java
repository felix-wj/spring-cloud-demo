package cn.wangjie.user.controller;

import cn.wangjie.user.aspect.annotation.LogAnnotation;
import cn.wangjie.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    @Autowired
    private RestTemplate restTemplate;
    @LogAnnotation(note = "getUser")
    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable("id") Integer id){
        User user = new User();
        user.setId(id);
        user.setName("张三");
        user.setAge(20);
        return user;
    }
    @GetMapping("/actuator/info")
    public String getInfo(){
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(20);
        return "tttttt";
    }
}
