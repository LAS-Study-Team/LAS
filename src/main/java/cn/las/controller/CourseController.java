package cn.las.controller;

import cn.las.service.LaboratoryService;
import cn.las.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    LaboratoryService bookService;

    @Autowired
    CourseService courseService;

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        System.out.println(bookService);
        System.out.println(courseService);
        return "Course Controller Test";
    }
}
