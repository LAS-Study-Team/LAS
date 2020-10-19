package cn.las.dao;

import cn.las.domain.Laboratory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LaboratoryDao {
<<<<<<< HEAD


    void updateLaboratoryStatus(@Param("status") Integer status, @Param("id") Integer id) throws Exception;

    void updateLaboratoryPnum(@Param("size") Integer size, @Param("id") Integer id) throws Exception;

    void updateLaboratoryType(@Param("type") Integer type, @Param("id") Integer id) throws Exception;

=======
>>>>>>> 44324db9295fcaf41f48c98c0c1e3d77e0286b40
    @Select("select * from laboratory")
    List<Laboratory> findAll() throws Exception;

    @Select("select * from laboratory where laboratory.id=#{id}")
    Laboratory findById(Integer id) throws Exception;

    @Delete("delete from laboratory where laboratory.id=#{id}")
    void deleteById(Integer id) throws Exception;
<<<<<<< HEAD

    void addLaboratory(Laboratory laboratory) throws Exception;

=======
>>>>>>> 44324db9295fcaf41f48c98c0c1e3d77e0286b40
}
