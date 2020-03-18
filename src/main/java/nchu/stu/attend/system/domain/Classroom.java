package nchu.stu.attend.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author XuTian
 * @description 教室类
 * @date 2020/3/16
 */
public class Classroom implements Serializable {
    private static final Long serialVersionUID = -4852732617765810959L;

    @Id
    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "classroom_name")
    private String classroomName;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "describes")
    private String describes;

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId=" + classroomId +
                ", classroomName='" + classroomName + '\'' +
                ", courseId=" + courseId +
                ", describes='" + describes + '\'' +
                '}';
    }
}
