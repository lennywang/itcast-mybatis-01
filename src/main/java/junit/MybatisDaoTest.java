package junit;

import com.itcast.dao.UserDao;
import com.itcast.dao.UserDaoImpl;
import com.itcast.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 **/
public class MybatisDaoTest {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testMyBatis() throws IOException {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);

        User user = userDao.findUserById(12);
        System.out.println(user);
    }


}
