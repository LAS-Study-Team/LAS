package cn.las.dao;

import cn.las.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {

    @Select("select * from user where username=#{username}")
    User findByUsername(String username) throws Exception;

    @Insert("insert into user(username, password, teacher) values(#{username},#{password},#{teacher})")
    void addUser(User user) throws Exception;

    @Delete("delete from user where username=#{username}")
    void deleteUserByUsername(String username) throws Exception;

    @Select("select * from user")
    List<User> findAll() throws Exception;

    @Update("update user set user.password=#{password} where user.username=#{username}")
    void changePassword(String username, String password) throws Exception;
}
