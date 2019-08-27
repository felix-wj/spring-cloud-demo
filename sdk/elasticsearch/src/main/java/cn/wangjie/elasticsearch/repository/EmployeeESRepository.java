package cn.wangjie.elasticsearch.repository;

import cn.wangjie.elasticsearch.model.EmployeeModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-08-01 11:39
 **/

public interface EmployeeESRepository extends ElasticsearchCrudRepository<EmployeeModel,Integer> {

    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"must\": {\n" +
            "        \"match\": {\n" +
            "          \"last_name\": \"?0\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"filter\": {\n" +
            "        \"range\": {\n" +
            "          \"age\": {\n" +
            "            \"gte\": ?1\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" )
    List<EmployeeModel> getEmp(String lastName, Integer age);

}
