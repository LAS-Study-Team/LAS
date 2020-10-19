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

    private Laboratory laboratory;

    private User user;

    private Course course;

    private List<IClass> iclasses;

    private int laboratoryId;

    private int userId;

    private int courseId;

    private String weeks;

    private String sections;

    private String classes;

    @Override
    public String toString() {
        return "Arrange{" +
                "id=" + id +
                ", laboratory=" + laboratory +
                ", user=" + user +
                ", course=" + course +
                ", weeks='" + weeks + '\'' +
                ", sections='" + sections + '\'' +
                ", classes='" + classes + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
    }

<<<<<<< HEAD

=======
>>>>>>> dev
    public List<IClass> getIclasses() {
        return iclasses;
    }

    public void setIclasses(List<IClass> iclasses) {
        this.iclasses = iclasses;
    }
}
