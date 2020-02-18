package com.hz.dao;

import com.hz.domain.QueryVo;
import com.hz.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        List<User> users=sqlSession.selectList("com.hz.dao.UserDao.findAll");
        //3.释放资源
        sqlSession.close();
        return users;
    }

    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用方法实现保存
        sqlSession.insert("com.hz.dao.UserDao.saveUser",user);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用方法实现更新
        sqlSession.insert("com.hz.dao.UserDao.updateUser",user);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    public void deleteUser(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用方法实现更新
        sqlSession.update("com.hz.dao.UserDao.deleteUser",userId);
        //3.提交事务
        sqlSession.commit();
        //4.释放资源
        sqlSession.close();
    }

    public User findById(Integer userId) {

        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用SqlSession中的方法，实现查询一个
        User user=sqlSession.selectOne("com.hz.dao.UserDao.findById",userId);
        //4.释放资源
        sqlSession.close();
        return user;
    }

    public List<User> findByName(String username) {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用SqlSession中的方法，实现查询一个
       List<User>  users=sqlSession.selectList("com.hz.dao.UserDao.findByName",username);
        //4.释放资源
        sqlSession.close();
        return users;
    }

    public int findTotal() {
        //1.根据factory获取SqlSession对象
        SqlSession sqlSession=factory.openSession();
        //2.调用SqlSession中的方法，实现查询一个
        Integer count=sqlSession.selectOne("com.hz.dao.UserDao.findTotal");
        //4.释放资源
        sqlSession.close();
        return count;
    }

    public List<User> findUserByVo(QueryVo vo) {
        return null;
    }
}
