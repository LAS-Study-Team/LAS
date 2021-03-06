package cn.las.service;

import cn.las.domain.Arrange;
import cn.las.domain.Laboratory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ArrangeService {

    List<Arrange> findAll() throws Exception;

    //根据课程号删除排课
    void deleteArrangeByCourseId(@Param("courseId") int courseId)throws Exception;

    //增加排课
    void insertone(Arrange arrange)throws Exception;

    //通过课程号修改排课
    void updateArrangeByCourseId(@Param("courseId") int courseId)throws Exception;

    //通过实验室号查询排课情况
    List<Arrange> findArrangeByLaboratoryId(@Param("laboratoryId")int laboratory)throws Exception;

    //通过课程号找到排课情况
    List<Arrange> findArrangeByCourseId(@Param("courseId")int courseId)throws Exception;

    //根据周数查看所有排课信息（可能不需要，先保留）
    List<Arrange> findArrangeByweek(@Param("weeks")int weeks)throws Exception;

    // 按照
    List<Laboratory> findEmptyLabByTypeAndWeeksAndDayAndSections(
            String type, List<Integer> weeks, Integer day, List<Integer> sections
    ) throws Exception;

    List<Integer> findEmptySectionsByLabIdAndWeeksAndDay (
            Integer laboratoryId, List<Integer> weeks, Integer day
    ) throws Exception;

    // 按照所选周数和周几进行时间段查询
    Set<Integer> findSectionsByWeeksAndDay(List<Integer> weeks, Integer day) throws Exception;

    // 按照第几周、周几、节数查询这节课的安排
    List<Arrange> findArrangeByWeekAndDayAndSection(Integer week, Integer day, Integer section) throws Exception;

    // 按照周、周几、第几段 判断是否有课程冲突
    List<Arrange> isEnableByWeeksAndDayAndSection(List<Integer> weeks, Integer day, Integer section) throws Exception;

    void insertArrange(Arrange arrange) throws Exception;

    void addArrange(Arrange arrange) throws Exception;
}
