package cn.las.domain;

import java.util.List;

public class Arrange {

    private int id;

    private Laboratory laboratory;

    private User user;

    private Course course;

    private String weeks;

    private String sections;

    private String classes;

    private List<Integer> lweeks;

    private List<Integer> lsections;

    private List<Integer> lclasses;

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
                ", lweeks=" + lweeks +
                ", lsections=" + lsections +
                ", lclasses=" + lclasses +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Laboratory getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Laboratory laboratory) {
        this.laboratory = laboratory;
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

    public List<Integer> getLweeks() {
        if(weeks != null) {
            String[] split = weeks.split(",");
            for (int i = 0; i < split.length; i++) {
                lweeks.add(Integer.valueOf(split[i]));
            }
        }
        return lweeks;
    }

    public void setLweeks(List<Integer> lweeks) {
        this.lweeks = lweeks;
    }

    public List<Integer> getLsections() {
        if(sections != null) {
            String[] split = sections.split(",");
            for (int i = 0; i < split.length; i++) {
                lsections.add(Integer.valueOf(split[i]));
            }
        }
        return lsections;
    }

    public void setLsections(List<Integer> lsections) {
        this.lsections = lsections;
    }

    public List<Integer> getLclasses() {
        if(classes != null) {
            String[] split = classes.split(",");
            for (int i = 0; i < split.length; i++) {
                lclasses.add(Integer.valueOf(split[i]));
            }
        }
        return lclasses;
    }

    public void setLclasses(List<Integer> lclasses) {
        this.lclasses = lclasses;
    }
}
