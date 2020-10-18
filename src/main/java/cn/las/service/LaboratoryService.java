package cn.las.service;

import org.apache.ibatis.annotations.Param;

public interface LaboratoryService {

    void updateLaboratoryState(@Param("id") int id)throws Exception;

    void updateLaboratoryPnum(@Param("id") int id)throws Exception;

    void updateLaboratoryType(@Param("id") int id)throws Exception;
}
