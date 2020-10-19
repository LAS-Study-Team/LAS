package cn.las.service;

import cn.las.domain.Laboratory;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface LaboratoryService {
    void updateLaboratoryStatus(@Param("status") Integer status, @Param("id") Integer id) throws Exception;

    void updateLaboratoryPnum(@Param("size") Integer size, @Param("id") Integer id) throws Exception;

    void updateLaboratoryType(@Param("type") Integer type, @Param("id") Integer id) throws Exception;

    List<Laboratory> findAll() throws Exception;

    Laboratory findById(Integer id) throws Exception;

    void deleteById(Integer id) throws Exception;

    void addLaboratory(Laboratory laboratory) throws Exception;
}
