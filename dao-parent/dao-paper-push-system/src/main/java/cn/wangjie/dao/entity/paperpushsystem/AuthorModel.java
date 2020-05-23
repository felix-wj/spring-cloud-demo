package cn.wangjie.dao.entity.paperpushsystem;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Author)实体类
 *
 * @author wangjie
 * @since 2020-05-22 13:06:28
 */
@Data
@Table(name = "author")
public class AuthorModel implements Serializable {
    private static final long serialVersionUID = 575811251130811830L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer authorId;
    
    private String name;


}