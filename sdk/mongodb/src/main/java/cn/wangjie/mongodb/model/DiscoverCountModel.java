package cn.wangjie.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "app_discover_count_new")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscoverCountModel {

    private String id;

    private Long time;

    private String status;

    private Integer uid;

    private Integer discover_id;

    private Integer type;

    private String country;

    private String province;

    private String city;

    private String distriction;

    private Integer city_count;

    private Integer country_count;

    private String updated_at;

    private String created_at;
}
