package nchu.stu.attend.system.domain;

import nchu.stu.attend.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XuTian
 * @description
 * @date 2020/2/22
 */
@Table(name = "t_student")
public class Student implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "student_name")
    @ExportConfig(value = "姓名")
    private String studentName;

    @Column(name = "student_gender")
    @ExportConfig(value = "性别")
    private String studentGender;

    @Column(name = "student_email")
    @ExportConfig(value = "邮箱")
    private String studentEmail;

    @Column(name = "student_qq")
    @ExportConfig(value = "QQ号")
    private String studentQQ;

    @Column(name = "user_id")
    @ExportConfig(value = "用户id")
    private Long userId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentQQ() {
        return studentQQ;
    }

    public void setStudentQQ(String studentQQ) {
        this.studentQQ = studentQQ;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", classId='" + classId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentQQ='" + studentQQ + '\'' +
                ", userId=" + userId +
                '}';
    }
}
