package cn.las.domain;

import java.util.List;

/**
 * laboratory : 储存实验室对象
 * user : 储存教师对象
 * course ：储存课程对象
 *
 * 这些都可以在查询数据库"课程安排信息"的时候顺便查出来
 */
public class Arrange {

    private int id;

    private int laboratoryId;

    private int userId;

    private int courseId;

    private int week;

    private int section;

    private int classId;

    @Override
    public String toString() {
        return "Arrange{" +
                "id=" + id +
                ", laboratoryId=" + laboratoryId +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", week=" + week +
                ", section=" + section +
                ", classId=" + classId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
