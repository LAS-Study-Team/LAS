package cn.las.dao;

import cn.las.domain.Laboratory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LaboratoryDao {

    void updateLaboratoryStatus(@Param("status") Integer status, @Param("id") Integer id) throws Exception;

    void updateLaboratoryPnum(@Param("size") Integer size, @Param("id") Integer id) throws Exception;

    void updateLaboratoryType(@Param("type") Integer type, @Param("id") Integer id) throws Exception;

    @Select("select * from laboratory")
    List<Laboratory> findAll() throws Exception;

    @Select("select * from laboatory where laboratory.id=#{id}")
    Laboratory findById(Integer id) throws Exception;

    @Delete("delete from laboratory where laboratory.id=#{id}")
    void deleteById(Integer id) throws Exception;

    void addLaboratory(Laboratory laboratory) throws Exception;
}
