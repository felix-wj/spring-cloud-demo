package cn.wangjie.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.jasypt.encryption.StringEncryptor;
import org.json.JSONException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void test1() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token={ACCESS_TOKEN}";
        String token ="25_E2Mow78OnReHny_0oWfSoxZQfxojpnnvEnpIvEIpKJVtRZt9BgOieClcddveLPsQbVQ5vgSzK1fvyM8wlVq-zSesX-htsi0xLXp7JUBblNgoKu9FmutFQZn838zkawgLU1tO5oEcNAXAPdfZWGJcAIAQAD";
        url = url.replace("{ACCESS_TOKEN}",token);
        Map<String,String> params = new HashMap<>(3);
        params.put("scene","a=1");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity entity = new HttpEntity(params,headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(url, entity, byte[].class);
        System.out.println(responseEntity.getHeaders().get("content-type").get(0));
    }

    @Data
    static class PhotoLabel{
        private Double x;
        private Double y;
        private String content;
        private String position;
    }
    @Test
    public void test5(){
        String str = "[{\"content\":\"人气第一餐厅\",\"position\":\"right\",\"x\":0.633778,\"y\":0.794317}]";
        List<PhotoLabel> labels = new ArrayList<>();
        PhotoLabel label = new PhotoLabel();
        label.setContent("123");
        label.setPosition("123");
        label.setX(1D);
        label.setY(2D);
        labels.add(label);

        label
    }
}
