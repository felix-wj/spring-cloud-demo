package cn.wangjie.movie.controller;


import cn.wangjie.movie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/user/get")
    public User getUser(){
        return  this.restTemplate.getForObject("http://localhost:20001/user/get",User.class);
    }

}
