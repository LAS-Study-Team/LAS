package cn.las.controller;

import cn.las.domain.Message;
import cn.las.domain.User;
import cn.las.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @爱出bug的代码小白
 *
 *　　┏┓　　　┏┓+ +
 *　┏┛┻━━━┛┻┓ + +
 *　┃　　　　　　　┃ 　
 *　┃　　　━　　　┃ ++ + + +
 * ████━████ ┃+
 *　┃　　　　　　　┃ +
 *　┃　　　┻　　　┃
 *　┃　　　　　　　┃ + +
 *　┗━┓　　　┏━┛
 *　　　┃　　　┃　　　　　　　　　　　
 *　　　┃　　　┃ + + + +
 *　　　┃　　　┃
 *　　　┃　　　┃ +  神兽保佑
 *　　　┃　　　┃    代码无bug　　
 *　　　┃　　　┃　　+　　　　　　　　　
 *　　　┃　 　　┗━━━┓ + +
 *　　　┃ 　　　　　　　┣┓
 *　　　┃ 　　　　　　　┏┛
 *　　　┗┓┓┏━┳┓┏┛ + + + +
 *　　　　┃┫┫　┃┫┫
 *　　　　┗┻┛　┗┻┛+ + + +
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @param user 传输的用户数据
     * @return 返回登陆成功 | 失败信息
     * @throws Exception
     *
     * 1、非空验证
     * 2、查询用户信息是否存在
     * 3、检验密码是否正确
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Message login(@RequestBody User user) throws Exception {
        Message message = new Message();

        // 非空验证
        if(user.getUsername() == null || user.getPassword() == null) {
            message.setCode(500);
            message.setMessage("填写信息不完整");
            return  message;
        }

        User usr = userService.findByUsername(user.getUsername());
        if(usr == null) {
            message.setCode(404);
            message.setMessage("用户不存在");
            return  message;
        }

        if(!usr.getPassword().equals(user.getPassword())) {
            message.setCode(403);
            message.setMessage("登录失败");
            return  message;
        }

        message.setCode(200);
        message.setMessage("登录成功");
        return message;
    }

    /**
     * @return  返回带有所有用户信息的数据 users
     * @throws Exception
     */
    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    @ResponseBody
    public Message findAll() throws Exception {
        Message message = new Message(200, "查询成功");
        List<User> all = userService.findAll();
        message.putData("users", all);
        return message;
    }

    /**
     * @param user 传输过来的用户信息
     * @return 返回操作成功 | 失败数据
     * @throws Exception
     *
     * 1、非空验证
     * 2、添加用户
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public Message addUser(User user) throws Exception {
        Message message = new Message();

        if(user.getUsername() == null || user.getPassword() == null || user.getTeacher() == null) {
            message.setCode(500);
            message.setMessage("参数不能为空");
            return message;
        }

        // 增加用户
        userService.addUser(user);

        message.setCode(200);
        message.setMessage("添加用户信息");
        return message;
    }

}
