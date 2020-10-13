package cn.las.service.impl;

import cn.las.dao.CourseDao;
import cn.las.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

}
