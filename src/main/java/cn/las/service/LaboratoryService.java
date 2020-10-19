package cn.las.service;

import org.apache.ibatis.annotations.Param;

public interface LaboratoryService {
    void updateLaboratoryState(@Param("id") int id)throws Exception;

    void updateLaboratoryPnum(@Param("id") int id)throws Exception;

<<<<<<< HEAD
    void updateLaboratoryType(@Param("id") int id)throws Exception;
=======
    void updateLaboratoryType(@Param("type") String type, @Param("id") Integer id) throws Exception;

    List<Laboratory> findAll() throws Exception;

    Laboratory findById(Integer id) throws Exception;

    void deleteById(Integer id) throws Exception;

    void addLaboratory(Laboratory laboratory) throws Exception;



>>>>>>> fa89e733f196db1d9e4899561c623dced027cc3d
}
