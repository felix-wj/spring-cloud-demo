package cn.wangjie.user.feignclient.impl;

import cn.wangjie.user.entity.Movie;
import cn.wangjie.user.feignclient.MovieFeignClient;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloudDemo
 * @description:
 * @author: WangJie
 * @create: 2018-10-29 10:19
 **/
@Component
public class MovieFeignClientFallback implements MovieFeignClient {
    @Override
    public Movie getMovie(Integer id) {
        Movie movie = new Movie();
        movie.setId(id);
        movie.setName("feign fallback movie");
        return movie;
    }
}
