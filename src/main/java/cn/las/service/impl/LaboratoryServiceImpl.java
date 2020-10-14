package cn.las.service.impl;

import cn.las.mapper.CourseMapper;
import cn.las.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    CourseMapper courseMapper;
}
