package cn.las.domain;

public class Arrange {

    private int id;

    private int laboratory;

    private int userId;

    private int courseId;

    private String weeks;

    private String sections;

    private String classes;

    public Arrange() {
    }

    public Arrange(int id, int laboratory, int userId, int courseId, String weeks, String sections, String classes) {
        this.id = id;
        this.laboratory = laboratory;
        this.userId = userId;
        this.courseId = courseId;
        this.weeks = weeks;
        this.sections = sections;
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(int laboratory) {
        this.laboratory = laboratory;
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
}
