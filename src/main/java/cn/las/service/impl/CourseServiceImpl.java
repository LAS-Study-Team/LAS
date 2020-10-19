package cn.las.service.impl;

import cn.las.dao.CourseDao;
import cn.las.domain.Course;
import cn.las.mapper.CourseMapper;
import cn.las.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Autowired
    CourseMapper courseMapper;

    public void addCourse(Course course) throws Exception {
        courseDao.addCourse(course);
    }

    public Course findCourseById(Integer id) throws Exception {
        return courseDao.findCourseById(id);
    }

    public Course findCourseByCourseName(String courseName) throws Exception {
        return courseDao.findCourseByCourseName(courseName);
    }

    public void removeCourseById(Integer id) throws Exception {
        courseDao.removeCourseById(id);
    }

    public void removeCourseByCourseName(String courseName) throws Exception {
        courseDao.removeCourseByCourseName(courseName);
    }

    public List<Course> findAll() throws Exception {
        return courseDao.findAll();
    }

    public void updateCourse(Course course) throws Exception {
        courseDao.updateCourse(course);
    }
    
    public List<Course> selectAll() throws Exception {
        return courseMapper.selectAll();
    }
}
