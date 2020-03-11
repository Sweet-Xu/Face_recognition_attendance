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
    private static  final long serialVersionUID = -4852732617765810959L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name")
    @ExportConfig(value = "课程名")
    private String courseName;

    @Column(name = "teacher")
    @ExportConfig(value = "任课老师")
    private String teacher;

    @Column(name = "address")
    @ExportConfig(value = "教室")
    private  String address;

    @Column(name = "course_date")
    @ExportConfig(value = "任课日期")
    private Date courseDate;

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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacher='" + teacher + '\'' +
                ", address='" + address + '\'' +
                ", courseDate=" + courseDate +
                '}';
    }
}
