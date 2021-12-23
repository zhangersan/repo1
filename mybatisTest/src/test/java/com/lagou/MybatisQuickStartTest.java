package com.lagou;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisQuickStartTest {

    /**
     * 查询所有
     * @throws IOException
     */
    @Test
    public void MybatisQuickStart() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql
        List<User> usersList = sqlSession.selectList("userMapper.findAll");

        //5.打印
        for (User user: usersList) {
            System.out.println(user);
            System.out.println("-------------------------------------");
        }

        //6.释放资源
        sqlSession.close();
    }

    /**
     * 增
     * @throws IOException
     */
    @Test
    public void saveUser() throws IOException {

        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //创建user对象,定义参数
        User user = new User();
        user.setUsername("唐伯虎");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京市朝阳区");

        //4.执行sql
        sqlSession.insert("userMapper.saveUser",user);

        //5.提交事务
        sqlSession.commit();

        //6.释放资源
        sqlSession.close();
    }

    /**
     * 改
     * @throws IOException
     */
    @Test
    public void update() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(4);
        user.setUsername("点秋香");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("哈尔滨漠河");

        sqlSession.update("userMapper.updateUser",user);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删
     * @throws IOException
     */
    @Test
    public void deleteUser() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("userMapper.deleteUser",3);
        sqlSession.commit();
        sqlSession.close();
    }
}
