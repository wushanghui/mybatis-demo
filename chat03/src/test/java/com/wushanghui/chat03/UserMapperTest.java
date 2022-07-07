package com.wushanghui.chat03;

import com.wushanghui.chat03.mapper.UserMapper;
import com.wushanghui.chat03.model.UserModel;
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
 * @since 2022/7/7 11:31
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
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
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
        /*
        35:05.222 [main] DEBUG c.w.c.mapper.UserMapper.getUserList - ==>  Preparing: SELECT * FROM t_user
        35:05.248 [main] DEBUG c.w.c.mapper.UserMapper.getUserList - ==> Parameters:
        35:05.269 [main] DEBUG c.w.c.mapper.UserMapper.getUserList - <==      Total: 6
        35:05.270 [main] INFO  com.wushanghui.chat03.UserMapperTest - UserModel(id=2, name=吴尚慧, age=29, salary=50000.0, sex=1)
        35:05.272 [main] INFO  com.wushanghui.chat03.UserMapperTest - UserModel(id=1657089225203, name=Java, age=30, salary=50000.0, sex=1)
        35:05.272 [main] INFO  com.wushanghui.chat03.UserMapperTest - UserModel(id=1657089225204, name=路人甲Java, age=30, salary=50000.0, sex=1)
        35:05.272 [main] INFO  com.wushanghui.chat03.UserMapperTest - UserModel(id=1657089225205, name=javacode2018, age=30, salary=50000.0, sex=1)
        35:05.272 [main] INFO  com.wushanghui.chat03.UserMapperTest - UserModel(id=1657089225206, name=张学友, age=56, salary=500000.0, sex=1)
        35:05.272 [main] INFO  com.wushanghui.chat03.UserMapperTest - UserModel(id=1657089225207, name=林志玲, age=45, salary=88888.88, sex=2)
         */
    }

}
