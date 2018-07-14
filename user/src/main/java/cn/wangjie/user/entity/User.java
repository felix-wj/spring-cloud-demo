package cn.wangjie.user.entity;

import lombok.Data;

/**
 * @program: SpringCloudDemo
 * @description: 用户实体
 * @author: WangJie
 * @create: 2018-07-08 14:08
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
}
