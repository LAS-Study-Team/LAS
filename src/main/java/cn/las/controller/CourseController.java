package cn.las.controller;

import cn.las.service.LaboratoryService;
import cn.las.domain.Course;
import cn.las.domain.Message;
import cn.las.service.CourseService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("course")
public class CourseController {

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
     * @return 返回带有所有course的mesage
     * @throws Exception
     *
     * 查询所有的课程信息
     */
    @RequestMapping("findAll")
    @ResponseBody
    public Message findAll() throws Exception {
        Message message = new Message(200, "获取课程成功");
        List<Course> all = courseService.findAll();
        message.putData("courses", all);
        return message;
    }

    /**
     * @param course 前端传输的信息封装成course
     * @return 返回状态码和操作提示信息
     * @throws Exception
     *
     * 增加课程功能
     *
     * 要求：
     *      课程不能重复
     */
    @RequestMapping(value = "addCourse", method = RequestMethod.POST)
    @ResponseBody
    public Message addCourse(@RequestBody Course course) throws Exception {
        Message message = new Message();
        Course cour = courseService.findCourseByCourseName(course.getName());
        //处理课程添加冲突
        if(cour != null) {
            message.setCode(500);
            message.setMessage("课程已存在");
            return message;
        }
        courseService.addCourse(course);
        message.setCode(200);
        message.setMessage("课程添加成功");
        return message;
    }

    /**
     * @param courseName 前端传输数据（courseName : ...）
     * @return 返回带有course信息的message
     */
    @RequestMapping("findCourseByName")
    @ResponseBody
    public Message findCourseByCourseName(@RequestParam(value = "courseName") String courseName) throws Exception {
        Message message = new Message();

        Course course = courseService.findCourseByCourseName(courseName);
        message.putData("course", course);
        message.setCode(200);
        message.setMessage("获取课程成功");
        return message;
    }









}
