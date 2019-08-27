package cn.wangjie.mongodb;

import cn.wangjie.mongodb.model.DiscoverCountModel;
import cn.wangjie.mongodb.model.UserModel;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: WangJie
 * @create: 2019-07-29 11:29
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void connectTest() {
        mongoTemplate.getCollection("user").find();
    }

    @Test
    public void insertTest() {
        UserModel userModel = UserModel.builder().createTime(System.currentTimeMillis()).name("张三").uid(1).build();
        mongoTemplate.insert(userModel);
    }

    @Test
    public void dataInit() {
        mongoTemplate.dropCollection(UserModel.class);
        UserModel userModel1 = UserModel.builder().createTime(System.currentTimeMillis()).name("张三").uid(1).build();
        UserModel userModel2 = UserModel.builder().createTime(System.currentTimeMillis()).name("李四").uid(2).build();
        UserModel userModel3 = UserModel.builder().createTime(System.currentTimeMillis()).name("王五").uid(3).build();
        UserModel userModel4 = UserModel.builder().createTime(System.currentTimeMillis()).name("tom").uid(4).build();
        UserModel userModel5 = UserModel.builder().createTime(System.currentTimeMillis()).name("cat").uid(5).build();
        mongoTemplate.insert(userModel1);
        mongoTemplate.insert(userModel2);
        mongoTemplate.insert(userModel3);
        mongoTemplate.insert(userModel4);
        mongoTemplate.insert(userModel5);

    }

    @Test
    public void findTest() {
        Query query = Query.query(Criteria.where("uid").is(1));
        UserModel userModel = mongoTemplate.findOne(query, UserModel.class);
        System.out.println(userModel);

    }

    @Test
    public void findTest2() {
        Document document = new Document();
        document.put("uid", 1);
        FindIterable<Document> findIterable = mongoTemplate.getCollection("user").find(document);
        document = findIterable.first();
/*        UserModel model = null;
        String json = document.toJson();
        System.out.println(json);
        model = JSON.parseObject(json,UserModel.class);*/
        UserModel model = mongoTemplate.getConverter().read(UserModel.class, document);
        System.out.println(model);
    }

    @Test
    public void documentTest() {
        Document document = new Document();
        document.put("time", 1234567L);
        System.out.println(document.toJson());
    }

    @Test
    public void updateTest() {
        Query query = new Query(Criteria.where("uid").is(1));
        Update update = new Update();
        update.set("name", "张三2");
        mongoTemplate.updateMulti(query, update, UserModel.class);
    }

    @Test
    public void upsertTest() {
        Query query = new Query(Criteria.where("uid").is(2).andOperator(Criteria.where("name").is("张三2")));
        Update update = new Update();
        update.set("name", "张三6");
        mongoTemplate.updateMulti(query, update, UserModel.class);
        mongoTemplate.upsert(query, update, UserModel.class);
    }

    @Test
    public void deleteTest() {
        UserModel userModel = UserModel.builder().uid(1).name("张三6").build();
        Query query = new Query(Criteria.where("uid").is(1));
        DeleteResult result = mongoTemplate.remove(query, UserModel.class);
        result = mongoTemplate.remove(userModel);
        System.out.println(result.getDeletedCount());
    }

    @Test
    public void sortTest() {
        Query query = new Query();
        query.with(Sort.by(new Sort.Order(Sort.Direction.DESC, "name"), new Sort.Order(Sort.Direction.ASC, "uid"))).limit(4).skip(1);
        List<UserModel> userModels = mongoTemplate.find(query, UserModel.class);
        for (UserModel model : userModels) {
            System.out.println(model);
        }
    }

    @Test
    public void sort() {
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(1))
                .addCriteria(Criteria.where("status").is("normal"))
                .addCriteria(Criteria.where("_id").gt(new ObjectId("599415cbd0717ba31450d592")));
        // query.with(Sort.by(new Sort.Order(Sort.Direction.ASC,"time"))).limit(4);
        System.out.println(query.getQueryObject().toJson());
        Iterable<Document> iterable = mongoTemplate.getCollection("app_discover_count_new").find(query.getQueryObject()).limit(4);
        iterable.forEach(document -> {
            DiscoverCountModel discoverCountModel = mongoTemplate.getConverter().read(DiscoverCountModel.class, document);
            System.out.println(discoverCountModel);
        });
    }
}
