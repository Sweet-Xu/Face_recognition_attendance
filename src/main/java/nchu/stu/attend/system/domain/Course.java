package nchu.stu.attend.system.domain;

import nchu.stu.attend.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author XuTian
 * @description
 * @date 2020/3/11
 */
@Table(name = "t_course")
public class Course implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name")
    @ExportConfig(value = "课程名")
    private String courseName;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "course_start_time")
    private Date courseStartTime;

    @Column(name = "course_start_week")
    private String courseStartWeek;

    @Column(name = "course_end_time")
    private Date courseEndTime;

    @Column(name = "course_end_week")
    private String courseEndWeek;

    @Column(name = "school_year")
    @ExportConfig(value = "学年")
    private  String schoolYear;

    @Column(name = "semester")
    @ExportConfig(value = "学期")
    private String semester;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Date getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(Date courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public String getCourseStartWeek() {
        return courseStartWeek;
    }

    public void setCourseStartWeek(String courseStartWeek) {
        this.courseStartWeek = courseStartWeek;
    }

    public Date getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(Date courseEndTime) {
        this.courseEndTime = courseEndTime;
    }

    public String getCourseEndWeek() {
        return courseEndWeek;
    }

    public void setCourseEndWeek(String courseEndWeek) {
        this.courseEndWeek = courseEndWeek;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", classId=" + classId +
                ", classroomId=" + classroomId +
                ", courseStartTime=" + courseStartTime +
                ", courseStartWeek='" + courseStartWeek + '\'' +
                ", courseEndTime=" + courseEndTime +
                ", courseEndWeek='" + courseEndWeek + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}
