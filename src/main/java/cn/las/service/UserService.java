package cn.las.service;

import cn.las.domain.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username) throws Exception;

    void addUser(User user) throws Exception;

    void deleteUserByUsername(String username) throws Exception;

    List<User> findAll() throws Exception;
<<<<<<< HEAD
=======

    void changePassword(String username, String password) throws Exception;
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
}
