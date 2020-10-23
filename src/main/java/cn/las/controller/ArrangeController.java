package cn.las.controller;

import cn.las.domain.*;
import cn.las.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    IClassService iClassService;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

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

//    /**
//     * @param arrange 自动封装排课对象
//     * @return  返回成功 | 失败信息
//     * @throws Exception
//     *
//     * 添加选课信息
//     */
//    @RequestMapping(value = "addArrange", method = RequestMethod.POST)
//    @ResponseBody
//    public Message addArrange(@RequestBody Arrange arrange) throws Exception {
//        Message message = new Message();
//
//        String userId=String.valueOf(arrange.getUserId());
//        String laboratoryId=String.valueOf(arrange.getLaboratoryId());
//        String courseId=String.valueOf(arrange.getCourseId());
//        String weeks=String.valueOf(arrange.getWeek());
//        String sections=String.valueOf(arrange.getSection());
//
//
//        //验证信息是否为空
//
//        if(userId == null){
//            message.setCode(100);
//            message.setMessage("教师id不能为空");
//            return message;
//        }
//
//        if(laboratoryId == null){
//            message.setCode(101);
//            message.setMessage("实验室id不能为空");
//            return message;
//        }
//
//        if(courseId == null){
//            message.setCode(102);
//            message.setMessage("课程id不能为空");
//            return message;
//        }
//
//        if(weeks == null){
//            message.setCode(103);
//            message.setMessage("上课周数不能为空");
//            return message;
//        }
//
//        if(sections == null){
//            message.setCode(101);
//            message.setMessage("所选节数不能为空");
//            return message;
//        }
//
//
//        //验证排课是否冲突
//
//
//        try {
//            arrangeService.insertone(arrange);
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            message.setCode(203);
//            message.setMessage("增加排课信息失败");
//            return message;
//        }
//
//        message.setCode(200);
//        message.setMessage("增加排课成功");
//
//        return message;
//    }

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
     * 1、按照指定的周数（weeks）、周几（day）、课程的节数（sections）和教室类型（type）进行选课
     *      要求前端给的数据是本课的排课周数，周几和第几节以及实验室的类型
     * 2、不指定教室的号，按照指定的信息获取合适的教室让教师自己选
     * 3、选好课之后再次提交，添加排课信息
     *
     * 周数可（weeks）多选，周几（days）可多选，课程的节数（section）可多选但是最多两节，教室的类型（type）单选
     *
     * 测试通过--白宝玉
     */
    @RequestMapping("selectEnableLabByTWDS")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Message genArrangeByTypeAndWeeksAndDayAndSection(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        //获取参数信息
        List<Integer> weeks = (List<Integer>) maps.get("weeks");
        Integer day = (Integer) maps.get("day");
        Integer section = (Integer) maps.get("section");
        String type = (String) maps.get("type");

        //非空验证
        if(weeks == null || section == null || type == null || day == null) {
            message.setCode(403);
            message.setMessage("参数不能为空");
            return message;
        }

        // 进行教室的查询
        try {
            /*
             * 首先查询教室是否可用
             * 如果不可用使用返回错误信息和错误代码
             */
            List<Arrange> arranges = arrangeService.isEnableByWeeksAndDayAndSection(weeks, day, section);

            System.out.println("arranges size = " + arranges.size());

            if(arranges.size() != 0) {
                message.setCode(502);
                message.setMessage("所选时间段冲突");
                message.putData("arranges", arranges);
                return message;
            }

            /*
             * 时间段可用，选择合适的实验室 并且进行返回
             */
            List<Laboratory> laboratories = laboratoryService.findByType(type);
            message.putData("laboratories", laboratories);
        } catch (Exception e) {
            e.printStackTrace();
            message.setMessage("查询空闲实验室失败-系统错误");
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
     *     userId:...,
     *     labId:...,
     *     courseId:...,
     *     classIds:...,
     *     weeks:...,
     *     days:...,
     *     sections:...
     * }
     * @return
     *
     * 1、根据给出的排课信息进行课程的增加
     * 2、需要考虑的情况
     *      1) 参数的非空验证
     *      2) 课程是否冲突查询 ==  如果冲突返回错误信息并且返回冲突数据
     * 3、生成排课信息
     *
     * 本接口可谓是最强接口
     *
     * 测试未通过--白宝玉
     */
    @RequestMapping(value = "addArrange", method = RequestMethod.POST)
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Message addArrange(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        //获取参数信息
        List<Integer> weeks = (List<Integer>) maps.get("weeks");
        List<Integer> days = (List<Integer>) maps.get("days");
        List<Integer> sections = (List<Integer>) maps.get("sections");
        Integer userId = (Integer) maps.get("userId");
        Integer courseId = (Integer) maps.get("courseId");
        Integer laboratoryId = (Integer) maps.get("labId");
        List<Integer> classIds = (List<Integer>) maps.get("classIds");

        //非空验证
        if(
                weeks == null || days == null || sections == null ||
                userId == null || courseId == null || classIds == null || laboratoryId == null
        ) {
            message.setCode(403);
            message.setMessage("参数不能为空");
            return message;
        }

        // 检查教室是否可用
        try {
            Laboratory laboratory = laboratoryService.findById(laboratoryId);
            if(laboratory == null) {
                message.setMessage("教室不存在");
                message.setCode(601);
                return message;
            }

            if(laboratory.getStatus() == 0) {
                message.setMessage("教室不可用");
                message.setCode(602);
                return message;
            }

            // 查询人数是否超过限制
            int humanNum = 0;
            for (Integer cid : classIds) {
                IClass iclass = iClassService.findById(cid);
                if(iclass != null) humanNum += iclass.getNumber();
            }
            if(humanNum > laboratory.getSize()) {
                message.setCode(603);
                message.setMessage("教室人数超限");
                return message;
            }

            // 判断用户和课程的信息是否存在
            Course course = courseService.findCourseById(courseId);
            if(course == null) return new Message(604, "课程不存在");

            // 检查用户是否存在
            User user = userService.findUserInfoById(userId);
            if(user == null) return new Message(605, "用户信息不存在");

            // 进行课程的生成
            List<Arrange> conflicts = new ArrayList<Arrange>();
            List<Arrange> arranges = new ArrayList<Arrange>();

            boolean permitAdd = true;
            for (Integer week : weeks) {
                for (Integer day : days) {
                    for (Integer section : sections) {
                        // 检查时间段是否可用
                        List<Arrange> arr = arrangeService.findArrangeByWeekAndDayAndSection(week, day, section);
                        if(arr != null) {
                            conflicts.addAll(arr);
                            permitAdd = false;
                        }

                        // 当不允许插入数据的时候，就不需要继续了
                        if(!permitAdd) continue;
                        for (Integer cid : classIds) {
                            Arrange arrange = new Arrange();
                            arrange.setLaboratoryId(laboratoryId);
                            arrange.setCourseId(courseId);
                            arrange.setClassId(cid);
                            arrange.setUserId(userId);
                            arrange.setWeek(week);
                            arrange.setDay(day);
                            arrange.setSection(section);
                            arranges.add(arrange);
                        }
                    }
                }
            }

            // 检查是否存在冲突
            if(conflicts.size() != 0) {
                message.setCode(606);
                message.setMessage("时间段存在冲突");
                message.putData("conflicts", conflicts);
                return message;
            }

            // 否则直接添加排课
            for (Arrange arrange : arranges) {
                arrangeService.insertArrange(arrange);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 基本这里的错误都是系统的错误了
            logger.error(e.getMessage());
            message.setCode(500);
            message.setMessage("服务器出错");
            return message;
        }

        message.setCode(200);
        message.setMessage("增加排课信息成功");
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
     * 查询某几周的某一天可用的时间段，查询全部的这几周可用时间段
     *
     * 测试已通过--白宝玉
     */
    @RequestMapping(value = "findSectionsByWD", method = RequestMethod.POST)
    @ResponseBody
    public Message findEnableSectionsByWeeksAndDay(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 获取数据
        List<Integer> weeks = (List<Integer>) maps.get("weeks");
        Integer day = (Integer) maps.get("day");

        //非空验证
        if(weeks == null || day == null) {
            message.setCode(403);
            message.setMessage("参数非空");
            return message;
        }

        // 进行可用时间段查询
        try {
            Set<Integer> sections = arrangeService.findSectionsByWeeksAndDay(weeks, day);
            message.putData("sections", sections);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            message.setMessage("获取空闲时间段错误");
            message.setCode(500);
            return message;
        }

        message.setCode(200);
        message.setMessage("查询成功");
        return message;
    }

    /**
     * @param maps
     * {
     *     week:...,
     *     day:...,
     *     section:...
     * }
     * @return
     *
     * 按照周数和第几天和第几节课查找课程安排
     *
     * 测试通过--高义博
     */
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
            List<Arrange> arrange = arrangeService.findArrangeByWeekAndDayAndSection(week, day, section);
            message.putData("arranges", arrange);
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
