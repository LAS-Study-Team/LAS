package cn.las.dao;

import org.apache.ibatis.annotations.Param;

public interface LaboratoryDao {
<<<<<<< HEAD


<<<<<<< HEAD
    void updateLaboratoryState(@Param("id") int id);

    void updateLaboratoryPnum(@Param("id") int id);

    void updateLaboratoryType(@Param("id") int id);
=======
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
>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d
}
