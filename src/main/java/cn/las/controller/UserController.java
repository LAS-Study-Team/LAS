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

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

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

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    @ResponseBody
    public Message findAll() throws Exception {
        Message message = new Message(200, "查询成功");
        List<User> all = userService.findAll();
        message.putData("users", all);
        return message;
    }

}
