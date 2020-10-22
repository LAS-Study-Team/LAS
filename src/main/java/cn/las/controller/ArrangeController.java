package cn.las.controller;

import cn.las.domain.Arrange;
import cn.las.domain.IClass;
import cn.las.domain.Laboratory;
import cn.las.domain.Message;
import cn.las.service.ArrangeService;
import cn.las.service.LaboratoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 关于选课的控制器
 */
@Controller
@RequestMapping("arrange")
public class ArrangeController {

    private static Logger logger = Logger.getLogger(CourseController.class);

    // 注入arrange服务层对象
    @Autowired
    ArrangeService arrangeService;

    @Autowired
    LaboratoryService laboratoryService;

    /**
     * 查询所有的排课信息
     * @return 返回带有所有排课arranges的message
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Message findAll() throws Exception {
        Message message = new Message();
        List<Arrange> all = null;
        try {
            all = arrangeService.findAll();
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(205);
            message.setMessage("获取排课信息失败");
            return message;
        }

        if(all == null){
            message.setCode(201);
            message.setMessage("不存在任何课程");
            return message;
        }

        message.setCode(200);
        message.putData("AllArrange",all);
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

        String userId=String.valueOf(arrange.getUserId());
        String laboratoryId=String.valueOf(arrange.getLaboratoryId());
        String courseId=String.valueOf(arrange.getCourseId());
        String weeks=String.valueOf(arrange.getWeek());
        String sections=String.valueOf(arrange.getSection());


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


        //验证排课是否冲突


        try {
            arrangeService.insertone(arrange);
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(203);
            message.setMessage("增加排课信息失败");
            return message;
        }

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

        List<Arrange> all = null;
        try {
            all = arrangeService.findArrangeByCourseId(courseId);
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(207);
            message.setMessage("查询此课程的排课情况失败");
            return message;
        }
        if(all == null){
            message.setCode(204);
            message.setMessage("没有此课程的排课情况");
            return message;
        }

        try {
            arrangeService.deleteArrangeByCourseId(courseId);
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(208);
            message.setMessage("删除此课程排课信息失败");
            return message;
        }
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
        List<Arrange> all = null;
        try {
            all = arrangeService.findArrangeByCourseId(courseId);
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(207);
            message.setMessage("查询此课程的排课情况失败");
            return message;
        }

        if(all == null){
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
        List<Arrange> all = null;
        try {
            all = arrangeService.findArrangeByLaboratoryId(laboratoryId);
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(208);
            message.setMessage("查询此实验室的排课情况失败");
            return message;
        }

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


    /**
     * @param weeks 教学周数
     * @return  返回带有某实验室排课arranges的message
     * @throws Exception
     *
     * 根据教学周查询排课情况（可能不需要，先保留）
     */
    @RequestMapping(value = "findArrangeByweek", method = RequestMethod.POST)
    @ResponseBody
    public Message findArrangeByweek(@RequestParam int weeks)throws Exception{
        Message message = new Message();

        List<Arrange> all = null;
        try {
            all = arrangeService.findAll();
        }catch (Exception e){
            logger.error(e.getMessage());
            message.setCode(206);
            message.setMessage("获取排课信息失败");
            return message;
        }
        List<Arrange> some = new ArrayList<Arrange>();

        for(Arrange s : all){
            int week = s.getWeek();
            if(Arrays.asList(week).contains(weeks))
                some.add(s);
        }

        if (some.isEmpty()){
            message.setCode(210);
            message.setMessage("当前周没有任何排课");
            return message;
        }

        message.setCode(200);
        message.putData("findArrangeByweek",some);
        message.setMessage("获取当前周排课成功");

        return message;
    }


    /**
     * @param maps
     * {
     *     type:...,
     *     weeks:...,
     *     day:...,
     *     sections:...
     * }
     * @return
     *
     * 1、按照指定的周数（weeks）、周几（day）、课程的节数（sections）和教室类型（type）获取可用的教室
     *      要求前端给的数据是本课的排课周数，周几和第几节
     * 2、不指定教室的号，按照指定的信息获取合适的教室让教师自己选
     * 3、选好课之后再次提交，添加排课信息
     *
     * 周数可（weeks）多选，周几（days）可多选，课程的节数（section）可多选但是最多两节，教室的类型（type）单选
     */
    @RequestMapping("findEmpLabByTWDS")
    @ResponseBody
    public Message findEmptyLabByTypeWeekDayAndSection(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        //获取参数信息
        String week = (String) maps.get("weeks");
        Integer day = (Integer) maps.get("day");
        String section = (String) maps.get("sections");
        String type = (String) maps.get("type");

        //非空验证
        if(week == null || section == null || type == null || day == null) {
            message.setCode(403);
            message.setMessage("参数不能为空");
            return message;
        }

        // 进行数据的处理
        List<Integer> weeks = new ArrayList<Integer>();
        for (String s : week.split(",")) {
            weeks.add(Integer.valueOf(s));
        }

        List<Integer> sections = new ArrayList<Integer>();
        for (String s : section.split(",")) {
            sections.add(Integer.valueOf(s));
        }

        // 进行教室的查询
        try {
            List<Laboratory> laboratories = arrangeService.findEmptyLabByTypeAndWeeksAndDayAndSections(
                    type, weeks, day, sections
            );
            message.putData("laboratories", laboratories);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMessage("查询空闲课程失败-系统错误");
            message.setCode(500);
            return message;
        }

        message.setCode(200);
        message.setMessage("获取空实验室信息成功");
        return message;
    }


    /**
     * @param maps
     * {
     *     weeks:...,
     *     id:...,
     *     day:...
     * }
     * @return
     *
     * 1、教师选定第几周到第几周，每周的周几，选定教室，但是不指定节数
     * 2、后台查询数据库，查看这天有几节课是闲置的
     * 3、返回可用的节数
     *
     * 如果出现冲突，返回错误码，前端负责页面的跳转
     */
    @RequestMapping("findEmpLabByIdAndWD")
    @ResponseBody
    public Message findEmptyLabByLabIdAndWeeksAndDay(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        //获取参数信息
        String week = (String) maps.get("weeks");
        Integer id = (Integer) maps.get("id");
        Integer day = (Integer) maps.get("day");

        //非空验证
        if(week == null || id == null || day == null) {
            message.setCode(403);
            message.setMessage("参数不能为空");
            return message;
        }

        // 封装weeks数据
        List<Integer> weeks = new ArrayList<Integer>();
        for (String s : week.split(",")) {
            weeks.add(Integer.valueOf(s));
        }

        try {
            Set<Integer> empty = arrangeService.findSectionsByWeeksAndDay(weeks, day);

            // 如果empty是空的，那么准备返回课程冲突信息
            if(empty == null) {
                message.setCode(502);
                message.setMessage("无空闲课程");
                return message;
            }

            // 否则直接返回
            message.putData("emptySections", empty);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMessage("服务器错误");
            message.setCode(500);
            return message;
        }

        // 进行教室的查询--按照教室号和周数


        message.setCode(200);
        message.setMessage("获取空实验室信息成功");
        return message;
    }

    /**
     * @param maps
     * {
     *     weeks:...,
     *     day:...
     * }
     * @return
     *
     * 查询某几周的某一天可用的时间段
     */
    @RequestMapping(value = "findSectionsByWD", method = RequestMethod.POST)
    @ResponseBody
    public Message findEnableSectionsByWeeksAndDay(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 获取数据
        String week = (String) maps.get("weeks");
        Integer day = (Integer) maps.get("day");

        //非空验证
        if(week == null || day == null) {
            message.setCode(403);
            message.setMessage("参数非空");
            return message;
        }

        // 进行可用时间段查询


        message.setCode(200);
        message.setMessage("查询成功");
        return message;
    }

    @RequestMapping("findArrangeByWDS")
    @ResponseBody
    public Message findArrangeByWeekDayAndSection(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 进行非空验证
        Integer week = (Integer) maps.get("week");
        Integer day = (Integer) maps.get("day");
        Integer section = (Integer) maps.get("section");

        if(week == null || day == null || section == null) {
            message.setCode(403);
            message.setMessage("参数不能为空");
            return message;
        }

        // 获取教室安排信息
        try {
            Arrange arrange = arrangeService.findArrangeByWeekAndDayAndSection(week, day, section);
            message.putData("arrange", arrange);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            message.setCode(500);
            message.setMessage("查询排课数据失败");
            return message;
        }

        message.setCode(200);
        message.setMessage("获取教室安排成功");
        return message;
    }


    /**
     * @爱出bug的代码小白
     * 后面思考的
     *
     * 1、能否实现教师指定第几周到第几周上课，指定周几，教室的类型，之后返回可安排的教室
     * 2、能否实现教师指定第几周到第几周上课，指定教室，返回可安排的周数和可安排的节数
     * 3、能否实现教师指定第几周到第几周上课，指定周几，指定教室类型，返回可安排的教室和可安排的节数
     * 4、能否实现教师指定第几周到第几周上课，指定周几，指定教室类型，返回可安排的教室和可安排的节数
     */


}
