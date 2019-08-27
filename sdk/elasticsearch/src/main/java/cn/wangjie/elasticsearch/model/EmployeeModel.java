package cn.wangjie.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-08-01 11:43
 **/
@Data
@Document(indexName = "megacorp",type = "employee")
public class EmployeeModel {
    @Id
    private Integer id;
    @Field()
    private String firstName;
    private String lastName;
    private Integer age;
    private String about;
    private List<String> interests;
}
