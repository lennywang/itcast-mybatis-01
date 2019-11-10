package junit;

import com.itcast.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisFirstTest {

    @Test
    public void testMyBatis() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("test.findUserById", 10);
        System.out.println(user);
    }

    @Test
    public void testFindUserByUsername() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("test.findUserByUsername", "路");
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("陆晓");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("北京市昌平区");
        int i = sqlSession.insert("test.insertUser", user);
        sqlSession.commit();
        System.out.println("影响行数："+i);
        System.out.println("新增用户Id："+user.getId());
    }

    @Test
    public void testUpdateUser() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(12);
        user.setUsername("王路路10");

        int i = sqlSession.update("test.updateUser", user);
        sqlSession.commit();
        System.out.println("影响行数："+i);
    }

    @Test
    public void testDeleteUser() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int i = sqlSession.delete("test.deleteUserById", 10);
        sqlSession.commit();
        System.out.println("影响行数："+i);
    }

}
