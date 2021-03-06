package cn.las.controller;

import cn.las.domain.Message;
import cn.las.domain.User;
import cn.las.service.UserService;
import cn.las.utils.AESUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;

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

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * @param user 传输的用户数据
     *             {
     *                  username:...,
     *                  password:...
     *             }
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

        String encrypt = AESUtil.encrypt(user.getPassword());
        System.out.println(encrypt);
        if(!usr.getPassword().equals(encrypt)) {
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
     *             {
     *                  username:...,
     *                  password:...,
     *                  teacher:...
     *             }
     * @return 返回操作成功 | 失败数据
     * @throws Exception
     *
     * 1、非空验证
     * 2、添加用户
     *
     * 当出现添加用户过程当中抛出错误的时候，进行事务回滚
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Message addUser(@RequestBody User user) throws Exception {
        Message message = new Message();

        if(user.getUsername() == null || user.getPassword() == null || user.getTeacher() == null) {
            message.setCode(500);
            message.setMessage("参数有误");
            return message;
        }

        // 查询当前账户是否已经被使用
        try {
            User u = userService.findByUsername(user.getUsername());
            if(u != null) throw new RuntimeException("用户已存在");
        } catch (Exception e) {
            message.setCode(403);
            message.setMessage(e.getMessage());
            return message;
        }

        // 对密码进行加密
        user.setPassword(AESUtil.encrypt(user.getPassword()));

        // 增加用户
        try {
            userService.addUser(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(403);
            message.setMessage("账户已存在");
            return message;
        }

        message.setCode(200);
        message.setMessage("添加用户信息成功");
        return message;
    }

    /**
     * @param datas  前端传输数据
     *               {
     *                  username: ...,
     *                  new_password: ...,
     *                  old_password:...
     *               }
     * @return
     * @throws Exception
     *
     * 当抛出错误的的时候进行事务回滚
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Message changePassword(@RequestBody Map<String, Object> datas) throws Exception {
        Message message = new Message();

        // 获取参数
        String oldPassword = (String) datas.get("old_password");
        String username = (String) datas.get("account");
        String newPassword = (String) datas.get("new_password");

        if(username == null || oldPassword == null || newPassword == null) {
            message.setCode(501);
            message.setMessage("参数不能为空");
            return message;
        }

        User user = userService.findByUsername(username);

        // 验证当前用户是否存在
        if(user == null) {
            message.setCode(500);
            message.setMessage("用户不存在");
            return message;
        }

        String secretOldPswd = AESUtil.encrypt(oldPassword);
        if(!secretOldPswd.equals(user.getPassword())) {
            message.setCode(502);
            message.setMessage("旧密码输入错误");
            return message;
        }

        // 验证通过开始修改当前账户的密码
        userService.changePassword(username, AESUtil.encrypt(newPassword));

        message.setCode(200);
        message.setMessage("修改密码成功");
        return message;
    }
}
