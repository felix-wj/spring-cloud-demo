package cn.wangjie.mangodb.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-07-29 11:34
 **/
@Data
@Builder
@Document(collection = "user")
public class UserModel {
    private String id;
    private Integer uid;
    private String name;
    private Long createTime;

    @Tolerate
    public UserModel() {
    }
}
