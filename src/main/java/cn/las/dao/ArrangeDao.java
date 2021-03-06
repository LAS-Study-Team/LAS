package cn.las.dao;

import cn.las.domain.Arrange;
import cn.las.domain.Laboratory;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * 排课持久层
 */
public interface ArrangeDao {

    @Select("select * from arrange")
    List<Arrange> findAll() throws Exception;

    @Select("select * from arrange where userId in (select id from user where teacher=#{teacherName})")
    List<Arrange> findByTeacherName(String teacherName) throws Exception;

    @Select("select * from arrange where laboratoryId=#{laboratoryId}")
    List<Arrange> findByLaboratoryId(Integer laboratoryId) throws Exception;

    // 插入课程，确定之前已经进行了验证
    @Insert("insert into arrange " +
            "(laboratoryId, userId, courseId, classId, week, day, section) " +
            "values(#{laboratoryId},#{userId},#{courseId},#{classId},#{week},#{day},#{section})")
    void addArrange(Arrange arrange) throws Exception;

    /**
     * @param type  查询教室的类型
     * @param sections 没被占用的课节数
     * @param weeks 没被占用的课的周数
     * @return
     * @throws Exception
     *
     * 这是最详细的一个查询
     * 查询课程在某一周内的周几的某一节课没有课的
     */
    @Select({
            "<script> ",
                "select * from laboratory ",
                "where id not in ( ",
                    "select laboratoryId from arrange ",
                    "where week in ",
                    "<foreach collection='weeks' item='week' open='(' separator=',' close=')'> ",
                    "#{week} ",
                    "</foreach> ",
                    "and day=#{day} ",
                    "and section in ",
                    "<foreach collection='sections' item='section' open='(' separator=',' close=')'> ",
                    "#{section} ",
                    "</foreach> ",
                ") ",
                "and type=#{type} ",
            "</script>"
    })
    List<Laboratory> findEmptyLabByTypeAndWeeksAndDayAndSections(
            @Param("type") String type, @Param("weeks") List<Integer> weeks,
            @Param("day") Integer day, @Param("sections") List<Integer> sections
    ) throws Exception;


    /**
     * 教师选定第几周到第几周，每周的周几，选定教室，但是不指定节数
     * 返回符合条件的课程的section的集合sections
     * 返回之后排除这些section
     * @param laboratoryId
     * @param weeks
     * @return
     * @throws Exception
     */
    @Select({
            "<script>  ",
                "select section from arrange ",
                "where week in ",
                "<foreach collection='weeks' item='week' open='(' separator=',' close=')'> ",
                "#{week} ",
                "</foreach> ",
                "and day=#{day}",
            "</script>"
    })
    List<Integer> findEmptyLabByLabIdAndWeeksAndDay (
            @Param("laboratoryId") Integer laboratoryId, @Param("weeks") List<Integer> weeks,
            @Param("day") Integer day
    ) throws Exception;

    /**
     * @param weeks
     * @param day
     * @return
     * @throws Exception
     *
     * 按照周数和第几周查询当天有课的时间段
     */
    @Select({
            "<script> ",
                "select section from arrange ",
                "where week not in ",
                "<foreach collection='weeks' item='week' open='(' separator=',' close=')'> ",
                "#{week} ",
                "</foreach> ",
                "and day=#{day}",
            "</script>"
    })
    Set<Integer> findSectionsByWeeksAndDay(
            @Param("weeks") List<Integer> weeks, @Param("day") Integer day
    ) throws Exception;

    @Select("select * from arrange where week=#{week} and day=#{day} and section=#{section}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "week", property = "week"),
            @Result(column = "day", property = "day"),
            @Result(column = "section", property = "section"),
            @Result(column = "userId", property = "user",
                    one = @One(select = "cn.las.dao.UserDao.findUserInfoById")),
            @Result(column = "laboratoryId", property = "laboratory",
                    one = @One(select = "cn.las.dao.LaboratoryDao.findById")),
            @Result(column = "courseId", property = "course",
                    one = @One(select = "cn.las.dao.CourseDao.findCourseById")),
            @Result(column = "classId", property = "iClass",
                    one = @One(select = "cn.las.dao.IClassDao.findById"))
    })
    List<Arrange> findArrangeByWeekAndDayAndSection(
            @Param("week") Integer week, @Param("day") Integer day, @Param("section") Integer section
    ) throws Exception;

    /**
     * @param section 没被占用的课节数
     * @param weeks 没被占用的课的周数
     * @param day 指定某一天
     * @return
     * @throws Exception
     *
     * 这是最详细的一个查询
     * 查询课程在某一周内的周几的某一节课没有课的
     */
    @Select({
            "<script> ",
            "select * from arrange ",
            "where week in ",
            "<foreach collection='weeks' item='week' open='(' separator=',' close=')'> ",
            "#{week} ",
            "</foreach> ",
            "and day=#{day} and section=#{section}",
            "</script>"
    })
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "week", property = "week"),
            @Result(column = "day", property = "day"),
            @Result(column = "section", property = "section"),
            @Result(column = "userId", property = "user",
                    one = @One(select = "cn.las.dao.UserDao.findUserInfoById")),
            @Result(column = "laboratoryId", property = "laboratory",
                    one = @One(select = "cn.las.dao.LaboratoryDao.findById")),
            @Result(column = "courseId", property = "course",
                    one = @One(select = "cn.las.dao.CourseDao.findCourseById")),
            @Result(column = "classId", property = "iClass",
                    one = @One(select = "cn.las.dao.IClassDao.findById"))
    })
    List<Arrange> isEnableByWeeksAndDayAndSection(
            @Param("weeks") List<Integer> weeks, @Param("day") Integer day, @Param("section") Integer section
    ) throws Exception;
}
