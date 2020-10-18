package cn.las.controller;

import cn.las.service.LaboratoryService;
import cn.las.domain.Course;
import cn.las.domain.Message;
import cn.las.service.CourseService;
<<<<<<< HEAD
=======
import org.apache.log4j.Logger;
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 课程管理说明
 *
 * 1、课程添加和修改的权限都只有管理员权限可以更改
 * 2、
 * 3、
 * 4、
 */
@Controller
@RequestMapping("course")
public class CourseController {

<<<<<<< HEAD
=======
    private static Logger logger = Logger.getLogger(CourseController.class);

>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
    @Autowired
    CourseService courseService;

    @Autowired
    LaboratoryService laboratoryService;

    @RequestMapping("test")
    @ResponseBody
    public Message test() {
        Message message = new Message(200, "Course Controller Test");
        System.out.println(courseService);
        return message;
    }

    /**
<<<<<<< HEAD
     * @return 返回带有所有course的mesage
=======
     * @return 返回带有所有course的 message
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
     * @throws Exception
     *
     * 查询所有的课程信息
     */
    @RequestMapping("findAll")
    @ResponseBody
<<<<<<< HEAD
    public Message findAll() throws Exception {
        Message message = new Message(200, "获取课程成功");
        List<Course> all = courseService.findAll();
=======
    public Message findAll() {
        Message message = new Message(200, "获取课程成功");
        List<Course> all = null;
        try {
            all = courseService.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(404);
            message.setMessage("数据获取失败");
            return message;
        }
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        message.putData("courses", all);
        return message;
    }

    /**
<<<<<<< HEAD
     * @param course 前端传输的信息封装成course
=======
     * @param maps 前端传输的信息封装成maps
     *             {
     *                  name:...,
     *                  time:...
     *             }
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
     * @return 返回状态码和操作提示信息
     * @throws Exception
     *
     * 增加课程功能
     *
     * 要求：
     *      课程名称不能重复
     */
    @RequestMapping(value = "addCourse", method = RequestMethod.POST)
    @ResponseBody
<<<<<<< HEAD
    public Message addCourse(@RequestBody Course course) throws Exception {
        Message message = new Message();
        Course cour = courseService.findCourseByCourseName(course.getName());
        //处理课程添加冲突
        if(cour != null) {
=======
    public Message addCourse(@RequestBody Map<String, Object> maps) throws Exception {

        for(String key : maps.keySet()) {
            System.out.println(maps.get(key));
        }

        Message message = new Message();

        // 获取课程信息
        String name = (String) maps.get("name");
        Integer time = (Integer) maps.get("time");

        // 非空验证
        if(name == null) {
            message.setCode(501);
            message.setMessage("参数不能为空");
            return message;
        }

        Course course = courseService.findCourseByCourseName(name);
        //处理课程添加冲突
        if(course != null) {
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
            message.setCode(500);
            message.setMessage("课程已存在");
            return message;
        }
<<<<<<< HEAD
        courseService.addCourse(course);
=======

        course = new Course();
        course.setName(name);
        course.setTime(time);

        try {
            courseService.addCourse(course);
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(501);
            message.setMessage("课程信息冲突");
            return message;
        }

>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        message.setCode(200);
        message.setMessage("课程添加成功");
        return message;
    }

    /**
<<<<<<< HEAD
     * @param courseName 前端传输数据（courseName : ...）
=======
     * @param maps 前端传输数据
     *             {
     *                  courseName: ...
     *             }
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
     * @return 返回带有course信息的message
     */
    @RequestMapping("findCourseByName")
    @ResponseBody
<<<<<<< HEAD
    public Message findCourseByCourseName(@RequestParam(value = "courseName") String courseName) throws Exception {
        Message message = new Message();

        Course course = courseService.findCourseByCourseName(courseName);
=======
    public Message findCourseByCourseName(@RequestBody Map<String, Object> maps) throws Exception {
        Message message = new Message();

        // 获取数据
        String name = (String) maps.get("courseName");

        // 非空验证
        if(name == null) {
            message.setCode(500);
            message.setMessage("课程名非空");
            return message;
        }

        Course course = null;
        course = courseService.findCourseByCourseName(name);
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        message.putData("course", course);
        message.setCode(200);
        message.setMessage("获取课程成功");
        return message;
    }

<<<<<<< HEAD
    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteById(@RequestParam Map<String, Object> datas) throws Exception {
=======
    /**
     * @param datas 传输课程的id
     *              {
     *                  id : ...
     *              }
     * @return
     */
    @RequestMapping(value = "deleteById", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteById(@RequestBody Map<String, Object> datas) {
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        Message message = new Message();
        String sid = (String) datas.get("id");
        if(sid == null) {
            message.setCode(500);
            message.setMessage("删除课程ID不能为空");
            return message;
        }

        Integer id = Integer.valueOf(sid);

        // 首先删其他依赖 arrange里面的


        //之后删除课程信息
<<<<<<< HEAD
        courseService.removeCourseById(id);
=======
        try {
            courseService.removeCourseById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(500);
            message.setMessage("课程删除失败");
            return message;
        }
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        message.setCode(200);
        message.setMessage("删除课程成功");
        return message;
    }

    /**
<<<<<<< HEAD
     * @param course  传输过来的数据 id是原来的课程号，名称和课时可进行修改
=======
     * @param maps  传输过来的数据 id是原来的课程号，名称和课时可进行修改
     *              {
     *                  id : ...,
     *                  name : ...,
     *                  time : ...
     *              }
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
     * @return 返回操作成功 | 失败信息
     * @throws Exception
     *
     * 更新课程信息
     * 参数： id,name,time
     */
    @RequestMapping(value = "updateCourse", method = RequestMethod.POST)
    @ResponseBody
<<<<<<< HEAD
    public Message updateCourse(@RequestBody Course course) throws Exception {
        Message message = new Message();

        // 更新课程信息
        courseService.updateCourse(course);

=======
    public Message updateCourse(@RequestBody Map<String, Object> maps) {
        Message message = new Message();

        // 获取需要的参数
        Integer id = (Integer) maps.get("id");
        String name = (String) maps.get("name");
        Integer time = (Integer) maps.get("time");

        if(id == null || name == null || time == null) {
            message.setCode(501);
            message.setMessage("参数不能为空");
            return message;
        }

        // 封装课程信息
        Course course = new Course();
        course.setTime(time);
        course.setId(id);
        course.setName(name);

        // 更新课程信息
        try {
            courseService.updateCourse(course);
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setCode(500);
            message.setMessage("更新信息失败:" + e.getMessage());
            return message;
        }
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
        message.setCode(200);
        message.setMessage("修改课程信息成功");
        return message;
    }
}
