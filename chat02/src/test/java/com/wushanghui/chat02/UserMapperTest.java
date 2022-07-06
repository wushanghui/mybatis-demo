package com.wushanghui.chat02;

import com.wushanghui.chat02.mapper.UserMapper;
import com.wushanghui.chat02.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 吴尚慧
 * @since 2022/7/6 14:34
 */
@Slf4j
public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //指定mybatis全局配置文件
        String resource = "mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void insertUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //创建UserModel对象
            UserModel userModel = UserModel.builder().id(System.currentTimeMillis()).name("Java").age(30).salary(50000D).sex(1).build();
            //执行插入操作
            int insert = mapper.insertUser(userModel);
            log.info("影响行数：{}", insert);
        }
        /*
        33:45.382 [main] DEBUG c.w.c.mapper.UserMapper.insertUser - ==>  Preparing: INSERT INTO t_user (id, name, age, salary, sex) VALUES (?, ?, ?, ?, ?)
        33:45.408 [main] DEBUG c.w.c.mapper.UserMapper.insertUser - ==> Parameters: 1657089225203(Long), Java(String), 30(Integer), 50000.0(Double), 1(Integer)
        33:45.415 [main] DEBUG c.w.c.mapper.UserMapper.insertUser - <==    Updates: 1
        33:45.416 [main] INFO  com.wushanghui.chat02.UserTest - 影响行数：1
         */
    }

    @Test
    public void updateUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //创建UserModel对象
            UserModel userModel = UserModel.builder().id(1657089225203L).name("Java，你好").age(18).salary(5000D).sex(0).build();
            //执行更新操作
            int result = mapper.updateUser(userModel);
            log.info("影响行数：{}", result);
        }
    }

    @Test
    public void deleteUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //定义需要删除的用户id
            Long userId = 1L;
            //执行删除操作
            int result = mapper.deleteUser(userId);
            log.info("影响行数：{}", result);
        }
    }

    @Test
    public void getUserList() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            //执行查询操作
            List<UserModel> userModelList = mapper.getUserList();
            userModelList.forEach(item -> {
                log.info("{}", item);
            });
        }
    }
}
