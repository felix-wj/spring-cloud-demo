package cn.wangjie.user.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloudDemo
 * @description: 命令行启动器 在SpringApplication启动时运行
 * @author: WangJie
 * @create: 2018-08-28 16:12
 **/
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner运行");
    }
}
