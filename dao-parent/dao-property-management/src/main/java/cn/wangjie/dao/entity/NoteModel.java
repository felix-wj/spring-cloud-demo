package cn.wangjie.dao.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 巡检信息(Note)实体类
 *
 * @author wangjie
 * @since 2020-05-23 10:44:25
 */
@Data
@Table(name = "note")
public class NoteModel implements Serializable {
    private static final long serialVersionUID = -30060516032381362L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    
    private Integer uid;
    /**
    * 用户名
    */
    private String name;
    
    private Integer noteTypeId;
    /**
    * 1：巡检信息 2：用户申请信息
    */
    private Integer type;
    
    private String content;
    
    private String treatment;
    /**
    * 1:待审核，2：已审核
    */
    private Integer reviewStatus;
    
    private String reviewContent;
    /**
    * 经度
    */
    private Float longitude;
    /**
    * 纬度
    */
    private Float latitude;
    
    private Long createTime;


}