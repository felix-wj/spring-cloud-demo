package cn.wangjie.user;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: SpringCloudDemo
 * @description:
 * @author: WangJie
 * @create: 2018-09-04 18:55
 **/
public class SimpleTest {

    @Autowired
    StringEncryptor stringEncryptor;//密码解码器自动注入

    @Test
    public void test(){
        System.out.println(stringEncryptor.encrypt("123456"));
    }
}
