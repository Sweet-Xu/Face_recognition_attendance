package nchu.stu.attend.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XuTian
 * @description 班级类
 * @date 2020/3/16
 */
@Table(name = "t_classes")
public class Classes implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    @Id
    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "teacher_id")
    private String teacherId;

    @Column(name = "class_people_num")
    private Integer classPeopleNum;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getClassPeopleNum() {
        return classPeopleNum;
    }

    public void setClassPeopleNum(Integer classPeopleNum) {
        this.classPeopleNum = classPeopleNum;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                ", collegeName=" + collegeName +
                ", teacherId='" + teacherId + '\'' +
                ", classPeopleNum=" + classPeopleNum +
                '}';
    }

}
