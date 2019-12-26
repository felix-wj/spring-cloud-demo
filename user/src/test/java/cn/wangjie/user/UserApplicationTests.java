package cn.wangjie.user;

import org.jasypt.encryption.StringEncryptor;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Autowired
    StringEncryptor stringEncryptor;//密码解码器自动注入
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }



}
