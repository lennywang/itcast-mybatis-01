package junit;

import com.itcast.mapper.UserMapper;
import com.itcast.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *
 **/
public class MybatisMapperTest {


    SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void testMyBatis() throws IOException {

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(13);
        System.out.println(user);
    }

    @Test
    public void testFindByUsername() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findUserByUsername("陆");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() throws IOException {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("王路路");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("湖北省武汉市");
        int i = userMapper.insertUser(user);
        sqlSession.commit();
        System.out.println("受影响的行数："+i);
        System.out.println("新增用户Id："+user.getId());
    }

    @Test
    public void testUpdateUser()
    {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(15);

        user.setId(user.getId());
        user.setUsername(user.getUsername()+"update");
        user.setBirthday(new Date());
        user.setAddress(user.getAddress()+"update");
        int i = userMapper.updateUser(user);
        sqlSession.commit();
        System.out.println("受影响的行数："+i);
    }

    @Test
    public void testDeleteUser()
    {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int i = userMapper.deleteUserById(12);
        sqlSession.commit();
        System.out.println("受影响的行数："+i);
    }


}
