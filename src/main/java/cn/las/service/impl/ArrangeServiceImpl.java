package cn.las.service.impl;

import cn.las.dao.ArrangeDao;
import cn.las.domain.Arrange;
import cn.las.mapper.ArrangeMapper;
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

    @Autowired
    ArrangeMapper arrangeMapper;

    public List<Arrange> findAll() throws Exception {
        return arrangeDao.findAll();
    }

    public void deleteArrangeByCourseId(int courseId)throws Exception{
        arrangeMapper.deleteById(courseId);
    }

    public void insertone(Arrange arrange)throws Exception{
        arrangeMapper.insertone(arrange);
    }

    public void updateArrangeByCourseId(int courseId)throws Exception{
        arrangeMapper.updateArrangeById(courseId);
    }

    public List<Arrange> findArrangeByLaboratoryId(int laboratoryId)throws Exception{
        return arrangeMapper.findArrangeByLaboratoryId(laboratoryId);
    }

    public List<Arrange> findArrangeByCourseId(int courseId)throws Exception{
        return arrangeMapper.findArrangeByCourseId(courseId);
    }

    //可能不需要（先保留）
    public List<Arrange> findArrangeByweek(int weeks)throws Exception{
        return arrangeMapper.findArrangeByweek(weeks);
    }
}
