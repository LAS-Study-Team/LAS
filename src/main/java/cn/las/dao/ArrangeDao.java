package cn.las.dao;

import cn.las.domain.Arrange;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 排课持久层
 */
public interface ArrangeDao {

    @Select("select * from arrange")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId"),
            @Result(property = "laboratoryId", column = "laboratoryId")

    })
    List<Arrange> findAll() throws Exception;

    @Select("select * from arrange where userId in (select id from user where teacher=#{teacherName})")
    List<Arrange> findByTeacherName(String teacherName) throws Exception;

    @Select("select * from arrange where laboratoryId=#{laboratoryId}")
    List<Arrange> findByLaboratoryId(Integer laboratoryId) throws Exception;

    @Insert("insert into arrange" +
            "(laboratoryId, userId, courseId, weeks, sections, classes)" +
            " values(#{laboratoryId},#{userId},#{courseId},#{weeks},#{sections},#{classes})")
    void addArrange(Arrange arrange) throws Exception;




}