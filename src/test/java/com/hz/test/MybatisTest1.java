package com.hz.test;

import com.hz.dao.UserDao;
import com.hz.dao.UserDao1;
import com.hz.domain.QueryVo;
import com.hz.domain.QueryVo1;
import com.hz.domain.User;
import com.hz.domain.User1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/*
* 测试mybatis的crud操作
* */
public class MybatisTest1 {

    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserDao1 userDao;

    @Before//用于在测试方法执行之前执行
    public void init() throws IOException {
        //1.读取配置文件,生成字节文件输入流
         inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(inputStream);
        //3.获取SqlSession对象
         sqlSession=factory.openSession();
        //4.获取dao的代理对象
         userDao=sqlSession.getMapper(UserDao1.class);
    }
    @After//用于在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        inputStream.close();
    }

    /*
    * 测试查询所有
    * */
    @Test
    public  void testFinAll() throws IOException {

        //5.执行查询所有方法
        List<User1> users=userDao.findAll();
        for (User1 user :
                users) {
            System.out.println(user);
        }

    }

    //测试保存方法
    @Test
    public void testSave() throws IOException {
        User1 user=new User1();
        user.setUserName("猪猪2221");
        user.setUserAddress("北京顺义区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前:"+user);
        //5.执行保存方法
        userDao.saveUser1(user);
        System.out.println("保存操作之后:"+user);
    }

    //测试更新操作
    @Test
    public void testUpdate() {
        User1 user=new User1();
        user.setUserId(50);
        user.setUserName("猪猪1");
        user.setUserAddress("北京顺义区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        //5.执行保存方法
        userDao.updateUser1(user);
    }

    //测试删除操作
    @Test
    public void testDelete() {

        //5.执行删除方法
        userDao.deleteUser(50);
    }

    //测试查询一个方法
    @Test
    public void testFindOne() {

        //5.执行查询一个方法
        User1 user = userDao.findById(48);
        System.out.println(user);
    }

    //测试模糊查询操作
    @Test
    public void testFindByName() {

        //5.执行查询一个方法
      //  List<User> users=userDao.findByName("%王");
        List<User1> users=userDao.findByName("王");
        for (User1 user :
                users) {
            System.out.println(user);
        }
    }

    //测试查询总记录的条数
    @Test
    public void testFindTotal() {

        //5.执行删除方法
        int total = userDao.findTotal();
        System.out.println(total);
    }

    //测试使用QueryVo作为查询条件
    @Test
    public void testFindByVo() {
        QueryVo1 queryVo=new QueryVo1();
        User1 user=new User1();
        user.setUserName("%王%");
        queryVo.setUser1(user);
        List<User1> users=userDao.findUserByVo(queryVo);
        for (User1 u :
                users) {
            System.out.println(u);
        }
    }

}
