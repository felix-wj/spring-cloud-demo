package cn.wangjie.movie.feignclient;

import cn.wangjie.movie.entity.User;
import cn.wangjie.movie.feignclient.impl.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "spring-cloud-user" , fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
    @RequestMapping(value = "/user/get/{id}",method = RequestMethod.GET)
    User getUser(@PathVariable("id") Integer id);
}
