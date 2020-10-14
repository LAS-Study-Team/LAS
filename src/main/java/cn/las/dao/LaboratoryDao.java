package cn.las.dao;

import org.apache.ibatis.annotations.Param;

public interface LaboratoryDao {

    void updateLaboratoryState(@Param("id") int id);

    void updateLaboratoryPnum(@Param("id") int id);

    void updateLaboratoryType(@Param("id") int id);
}
