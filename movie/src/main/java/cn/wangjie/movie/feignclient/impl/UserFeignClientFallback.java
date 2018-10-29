package cn.wangjie.movie.feignclient.impl;

import cn.wangjie.movie.entity.User;
import cn.wangjie.movie.feignclient.UserFeignClient;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloudDemo
 * @description:
 * @author: WangJie
 * @create: 2018-10-29 10:19
 **/
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
