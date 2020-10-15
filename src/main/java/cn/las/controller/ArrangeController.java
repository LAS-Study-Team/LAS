package cn.las.controller;

import cn.las.domain.Arrange;
import cn.las.domain.Message;
import cn.las.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 关于选课的控制器
 */
@Controller
@RequestMapping("arrange")
public class ArrangeController {

    // 注入arrange服务层对象
    @Autowired
    ArrangeService arrangeService;

    /**
     * 查询所有的排课信息
     * @return 返回带有所有排课arranges的message
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Message findAll() throws Exception {
        Message message = new Message();
        List<Arrange> all = arrangeService.findAll();
        message.setCode(200);
        message.setMessage("获取排课信息成功");
        return message;
    }

    /**
     * @param arrange 自动封装排课对象
     * @return  返回成功 | 失败信息
     * @throws Exception
     *
     * 添加选课信息
     */
    @RequestMapping(value = "addArrange", method = RequestMethod.POST)
    @ResponseBody
    public Message addArrange(@RequestBody Arrange arrange) throws Exception {
        Message message = new Message();




        return message;
    }




}
