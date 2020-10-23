package cn.las.service.impl;

import cn.las.dao.UserDao;
import cn.las.domain.User;
import cn.las.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public User findByUsername(String username) throws Exception {
        return userDao.findByUsername(username);
    }

    public void addUser(User user) throws Exception {
        userDao.addUser(user);
    }

    public void deleteUserByUsername(String username) throws Exception {
        userDao.deleteUserByUsername(username);
    }

    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    public void changePassword(String username, String password) throws Exception {
        userDao.changePassword(username, password);
    }

    @Override
    public User findUserInfoById(Integer id) throws Exception {
        return userDao.findUserInfoById(id);
    }
}
