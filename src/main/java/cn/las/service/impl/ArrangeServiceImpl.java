package cn.las.service.impl;

import cn.las.dao.ArrangeDao;
import cn.las.domain.Arrange;
import cn.las.service.ArrangeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArrangeServiceImpl implements ArrangeService {

    // 注入持久层对象
    @Autowired
    ArrangeDao arrangeDao;

    public List<Arrange> findAll() throws Exception {
        return arrangeDao.findAll();
    }

    public void deleteArrangeByCourseId(int courseId)throws Exception{
        arrangeDao.deleteById(courseId);
    }

    public void insertone(Arrange arrange)throws Exception{
        arrangeDao.insertone(arrange);
    }

    public void updateArrangeByCourseId(int courseId)throws Exception{
        arrangeDao.updateArrangeById(courseId);
    }

    public List<Arrange> findArrangeByLaboratoryId(int laboratoryId)throws Exception{
        return arrangeDao.findArrangeByLaboratoryId(laboratoryId);
    }
}
