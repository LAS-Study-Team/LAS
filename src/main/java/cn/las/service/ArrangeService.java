package cn.las.service;

import cn.las.domain.Arrange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArrangeService {

    List<Arrange> findAll() throws Exception;

    //根据课程号删除排课
    void deleteArrangeByCourseId(@Param("courseId") int courseId)throws Exception;

    //增加排课
    void insertone(Arrange arrange)throws Exception;

    //通过课程号修改排课
    void updateArrangeByCourseId(@Param("courseId") int courseId)throws Exception;

    //通过实验室号查询排课情况
    List<Arrange> findArrangeByLaboratoryId(@Param("laboratoryId")int laboratory)throws Exception;
}
