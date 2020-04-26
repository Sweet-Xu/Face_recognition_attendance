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
 * @description  考勤表
 * @date 2020/3/12
 */
@Table(name = "t_attend")
public class Attend implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "attend_id")
    private Long attendId;

    @Column(name = "attend_name")
    private String attendName;

    @Column(name = "rule_id")
    private Integer ruleId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "classroom_id")
  //  @ExportConfig(value = "教室编号")
    private String classroomId;

    @Column(name = "describes")
    private String describes;

    @Column(name = "attend_status")
    private String attendStatus;

    @Column(name = "manager")
    private String manager;

    @Column(name = "attend_create_time")
    private Date attendCreateTime;

    @Column(name = "class_id")
    private Integer classId;



    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(String classroomId) {
        this.classroomId = classroomId;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getAttendStatus() {
        return attendStatus;
    }

    public void setAttendStatus(String attendStatus) {
        this.attendStatus = attendStatus;
    }

    public String getAttendName() {
        return attendName;
    }

    public void setAttendName(String attendName) {
        this.attendName = attendName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Date getAttendCreateTime() {
        return attendCreateTime;
    }

    public void setAttendCreateTime(Date attendCreateTime) {
        this.attendCreateTime = attendCreateTime;
    }

    @Override
    public String toString() {
        return "Attend{" +
                "attendId=" + attendId +
                ", attendName='" + attendName + '\'' +
                ", ruleId=" + ruleId +
                ", courseId=" + courseId +
                ", classroomId='" + classroomId + '\'' +
                ", describes='" + describes + '\'' +
                ", attendStatus='" + attendStatus + '\'' +
                ", manager='" + manager + '\'' +
                ", attendCreateTime=" + attendCreateTime +
                '}';
    }
}
