package cn.las.dao;

import org.apache.ibatis.annotations.Insert;

public interface CourseDao {

    @Insert("select * from course")
    void insert();
}
