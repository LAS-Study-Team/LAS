package cn.las.controller;

import cn.las.domain.Arrange;
import cn.las.domain.IClass;
import cn.las.domain.Message;
import cn.las.service.ArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        message.putData("AllArrange",all);
        message.setMessage("获取排课信息成功");

        if(all == null){
            message.setCode(201);
            message.setMessage("不存在此课程");
            return message;
        }

        message.setCode(203);
        message.putData("AllArrange",all);
        message.setMessage("目前没有任何排课");

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

        List<IClass> iClass=arrange.getIclasses();
        String userId=String.valueOf(arrange.getUserId());
        String laboratoryId=String.valueOf(arrange.getLaboratoryId());
        String courseId=String.valueOf(arrange.getCourseId());
        String weeks=arrange.getWeeks();
        String sections=arrange.getSections();


        //验证信息是否为空

        if(userId == null){
            message.setCode(100);
            message.setMessage("教师id不能为空");
            return message;
        }

        if(laboratoryId == null){
            message.setCode(101);
            message.setMessage("实验室id不能为空");
            return message;
        }

        if(courseId == null){
            message.setCode(102);
            message.setMessage("课程id不能为空");
            return message;
        }

        if(weeks == null){
            message.setCode(103);
            message.setMessage("上课周数不能为空");
            return message;
        }

        if(sections == null){
            message.setCode(101);
            message.setMessage("所选节数不能为空");
            return message;
        }


        //验证课程是否冲突

        arrangeService.insertone(arrange);
        message.setCode(200);
        message.setMessage("增加排课成功");

        return message;
    }

    /**
     * @param courseId 课程id
     * @return  返回成功 | 失败信息
     * @throws Exception
     *
     * 删除选课信息
     */
    @RequestMapping(value = "deleteArrangeByCourseId", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteArrangeById(@RequestParam int courseId)throws Exception{
        Message message = new Message();

        List<Arrange> all = arrangeService.findArrangeByCourseId(courseId);
        if(all == null){
            message.setCode(204);
            message.setMessage("没有此课程的排课情况");
            return message;
        }

        arrangeService.deleteArrangeByCourseId(courseId);
        message.setCode(200);
        message.setMessage("删除课程成功");

        return message;
    }

    /**
     * @param courseId 课程id
     * @return  返回成功 | 失败信息
     * @throws Exception
     *
     * 修改选课信息
     */
    @RequestMapping(value = "updateArrangeByCourseId", method = RequestMethod.POST)
    @ResponseBody
    public Message updateArrangeById(@RequestParam int courseId)throws Exception{
        Message message = new Message();
        List<Arrange> arrange = arrangeService.findArrangeByCourseId(courseId);

        if(arrange == null){
            message.setCode(201);
            message.setMessage("不存在此课程");
            return message;
        }

        arrangeService.updateArrangeByCourseId(courseId);
        message.setCode(200);
        message.setMessage("修改课程成功");
        return message;
    }


    /**
     * @param laboratoryId 实验室id
     * @return  返回带有某实验室排课arranges的message
     * @throws Exception
     *
     * 查询某实验室排课信息
     */
    @RequestMapping(value = "findArrangeByLaboratoryId", method = RequestMethod.POST)
    @ResponseBody
    public Message findArrangeByLaboratoryId(@RequestParam int laboratoryId)throws Exception{
        Message message = new Message();
        List<Arrange> all = arrangeService.findArrangeByLaboratoryId(laboratoryId);

        if(all == null){
            message.setCode(202);
            message.setMessage("此实验室未安排课程");
            return message;
        }

        message.setCode(200);
        message.putData("ArrangeByLaboratoryId",all);
        message.setMessage("获取排课信息成功");

        return message;
    }

}
