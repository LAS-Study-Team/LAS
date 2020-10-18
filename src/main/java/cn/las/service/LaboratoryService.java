package cn.las.service;

import org.apache.ibatis.annotations.Param;

public interface LaboratoryService {

<<<<<<< HEAD
    void updateLaboratoryState(@Param("id") int id)throws Exception;

    void updateLaboratoryPnum(@Param("id") int id)throws Exception;

    void updateLaboratoryType(@Param("id") int id)throws Exception;
=======
    void updateLaboratoryState(@Param("id") int id);

    void updateLaboratoryPnum(@Param("id") int id);

    void updateLaboratoryType(@Param("id") int id);
>>>>>>> f020a5f02153ae4a5da58744e046a0a719c1d9c0
}
