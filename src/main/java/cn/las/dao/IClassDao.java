package cn.las.dao;

import cn.las.domain.IClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IClassDao {

    @Select("select * from iclass where name=#{classname}")
    IClass findByClassName(String classname) throws Exception;

    @Select("select * from iclass where iclass.id=#{id}")
    IClass findById(Integer id) throws Exception;

    @Select("select * from iclass")
    List<IClass> findAll() throws Exception;

    @Insert("insert into iclass(iclass.name, iclass.number) values(name, number)")
    void addClass(IClass iClass) throws Exception;

    @Update("update iclass set iclass.name=#{name}, iclass.number=#{number} " +
            " where iclass.id=#{id}")
    void updateClass(IClass iClass) throws Exception;

    @Delete("delete from iclass where iclass.id=#{id}")
    void deleteByClassId(Integer id) throws Exception;
}
