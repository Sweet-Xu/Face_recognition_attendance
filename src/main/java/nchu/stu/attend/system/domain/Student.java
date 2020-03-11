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
    @GeneratedValue(generator = "JDBC")
    @Column(name = "stu_id")
    private Long stuId;

    @Column(name = "stu_college")
    @ExportConfig(value = "学院")
    private String stuCollege;

    @Column(name = "stu_classNo")
    @ExportConfig(value = "班级")
    private String stuClassNo;

    @Column(name = "stu_number")
    @ExportConfig(value = "学号")
    private String stuNumber;

    @Column(name = "stu_role")
    private int stuRole;

    @Column(name = "stu_name")
    @ExportConfig(value = "姓名")
    private String stuName;

    @Column(name = "stu_phone")
    @ExportConfig(value = "联系电话")
    private String stuPhone;

    @Column(name = "stu_email")
    @ExportConfig(value = "电子邮箱")
    private String stuEmail;


    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuCollege() {
        return stuCollege;
    }

    public void setStuCollege(String stuCollege) {
        this.stuCollege = stuCollege;
    }

    public String getStuClassNo() {
        return stuClassNo;
    }

    public void setStuClassNo(String stuClassNo) {
        this.stuClassNo = stuClassNo;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public int getStuRole() {
        return stuRole;
    }

    public void setStuRole(int stuRole) {
        this.stuRole = stuRole;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuCollege='" + stuCollege + '\'' +
                ", stuClassNo='" + stuClassNo + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuRole=" + stuRole +
                ", stuName='" + stuName + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                '}';
    }

}
