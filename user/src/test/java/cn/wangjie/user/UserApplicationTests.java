package cn.wangjie.user;

import cn.wangjie.dao.config.DataSourceConfigurer;
import cn.wangjie.dao.entity.NoteModel;
import cn.wangjie.dao.entity.paperpushsystem.AuthorModel;
import cn.wangjie.dao.mapper.propertymanagement.NoteDao;
import cn.wangjie.dao.mapper.paperpushsystem.AuthorDao;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

    @Autowired
    StringEncryptor stringEncryptor;//密码解码器自动注入
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private NoteDao noteDao;


    @Test
    public void contextLoads() {
    }

    @Test
    public void multipleDataSource(){
        AuthorModel authorModel = authorDao.selectByPrimaryKey(9801);
        System.out.println(authorModel);
        NoteModel noteModel = noteDao.selectByPrimaryKey(1);
        System.out.println(noteModel);
    }

    @Test
    @Transactional
    public void transactionTest1(){
        AuthorModel authorModel = new AuthorModel();
        authorModel.setName("testTransaction");
        authorDao.insert(authorModel);
        throw new RuntimeException();
    }

    @Test
    @Transactional(transactionManager = DataSourceConfigurer.PM_TRANSACTION_MANAGER)
    public void transactionTest2(){
        NoteModel noteModel = noteDao.selectByPrimaryKey(1);
        noteModel.setId(null);
        noteDao.insert(noteModel);
        throw new RuntimeException();
    }



}
