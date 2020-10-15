package cn.las.dao;

import cn.las.domain.Arrange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArrangeDao {

    List<Arrange> selectAll(@Param("userId") int userId)throws Exception;

    void deleteById(@Param("courseId") int courseId)throws Exception;

    void updateById(@Param("courseId") int courseId)throws Exception;

    void insertone(Arrange arrange)throws Exception;

    Arrange findById(@Param("laboratoryId") int laboratoryId)throws Exception;

}
