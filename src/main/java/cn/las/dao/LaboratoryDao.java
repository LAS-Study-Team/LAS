package cn.las.dao;

import cn.las.domain.Laboratory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LaboratoryDao {

    @Select("select * from laboratory")
    List<Laboratory> findAll() throws Exception;

    @Select("select * from laboratory where laboratory.id=#{id}")
    Laboratory findById(Integer id) throws Exception;

    @Delete("delete from laboratory where laboratory.id=#{id}")
    void deleteById(Integer id) throws Exception;
}
