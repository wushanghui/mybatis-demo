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
 * @since 2022/7/6 13:34
 */
@Slf4j
public class UserTest {

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
    public void sqlSession() {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        log.info("sqlSession: {}", sqlSession);
        // 35:57.503 [main] INFO  com.wushanghui.chat02.UserTest - sqlSession: org.apache.ibatis.session.defaults.DefaultSqlSession@543e710e
    }

    @Test
    public void insertUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(false);) {
            //创建UserModel对象
            UserModel userModel = UserModel.builder().id(2L).name("吴尚慧").age(29).salary(50000D).sex(1).build();
            //执行插入操作
            int result = sqlSession.insert("com.wushanghui.chat02.mapper.UserMapper.insertUser", userModel);
            log.info("插入影响行数：{}", result);
            //提交事务
            sqlSession.commit();
        }
        /*
        08:19.107 [main] DEBUG c.w.c.mapper.UserMapper.insertUser - ==>  Preparing: INSERT INTO t_user (id, name, age, salary, sex) VALUES (?, ?, ?, ?, ?)
        08:19.137 [main] DEBUG c.w.c.mapper.UserMapper.insertUser - ==> Parameters: 2(Long), 吴尚慧(String), 29(Integer), 50000.0(Double), 1(Integer)
        08:19.142 [main] DEBUG c.w.c.mapper.UserMapper.insertUser - <==    Updates: 1
        08:19.142 [main] INFO  com.wushanghui.chat02.UserTest - 插入影响行数：1
         */
    }

    @Test
    public void updateUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            //创建UserModel对象
            UserModel userModel = UserModel.builder().id(2L).name("吴尚慧，你好").age(18).salary(5000D).sex(0).build();
            //执行更新操作
            int result = sqlSession.update("com.wushanghui.chat02.mapper.UserMapper.updateUser", userModel);
            log.info("影响行数：{}", result);
        }
        /*
        21:03.804 [main] DEBUG c.w.c.mapper.UserMapper.updateUser - ==>  Preparing: UPDATE t_user SET name = ?,age = ?,salary = ?,sex = ? WHERE id = ?
        21:03.834 [main] DEBUG c.w.c.mapper.UserMapper.updateUser - ==> Parameters: 吴尚慧，你好(String), 18(Integer), 5000.0(Double), 0(Integer), 2(Long)
        21:03.841 [main] DEBUG c.w.c.mapper.UserMapper.updateUser - <==    Updates: 1
        21:03.842 [main] INFO  com.wushanghui.chat02.UserTest - 影响行数：1
         */
    }

    @Test
    public void deleteUser() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            //定义需要删除的用户id
            Long userId = 2L;
            //执行删除操作
            int result = sqlSession.delete("com.wushanghui.chat02.mapper.UserMapper.deleteUser", userId);
            log.info("影响行数：{}", result);
        }
        /*
        22:28.765 [main] DEBUG c.w.c.mapper.UserMapper.deleteUser - ==>  Preparing: DELETE FROM t_user WHERE id = ?
        22:28.799 [main] DEBUG c.w.c.mapper.UserMapper.deleteUser - ==> Parameters: 2(Long)
        22:28.806 [main] DEBUG c.w.c.mapper.UserMapper.deleteUser - <==    Updates: 1
        22:28.806 [main] INFO  com.wushanghui.chat02.UserTest - 影响行数：1
         */
    }

    @Test
    public void getUserList() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true);) {
            //执行查询操作
            List<UserModel> userModelList = sqlSession.selectList("com.wushanghui.chat02.mapper.UserMapper.getUserList");
            log.info("结果：{}", userModelList);
        }
        /*
        24:25.074 [main] DEBUG c.w.c.mapper.UserMapper.getUserList - ==>  Preparing: SELECT * FROM t_user
        24:25.102 [main] DEBUG c.w.c.mapper.UserMapper.getUserList - ==> Parameters:
        24:25.133 [main] DEBUG c.w.c.mapper.UserMapper.getUserList - <==      Total: 1
        24:25.133 [main] INFO  com.wushanghui.chat02.UserTest - 结果：[UserModel(id=2, name=吴尚慧, age=29, salary=50000.0, sex=1)]
         */
    }

}
