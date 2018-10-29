package cn.wangjie.user.feignclient;


import cn.wangjie.user.entity.Movie;
import cn.wangjie.user.feignclient.impl.MovieFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "movie" , fallback = MovieFeignClientFallback.class)
public interface MovieFeignClient {
    @RequestMapping(value = "/user/get/{id}",method = RequestMethod.GET)
    Movie getMovie(@PathVariable("id") Integer id);
}
