package cn.las.controller;

import cn.las.service.LaboratoryService;
import cn.las.domain.Course;
import cn.las.domain.Message;
import cn.las.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping("findAll")
    @ResponseBody
    public Message findAll() throws Exception {
        Message message = new Message(200, "获取课程成功");
        List<Course> all = courseService.findAll();
        message.putData("courses", all);
        return message;
    }

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

    public Message findCourseByCourseName() {
        Message message = new Message();



        return message;
    }








}
