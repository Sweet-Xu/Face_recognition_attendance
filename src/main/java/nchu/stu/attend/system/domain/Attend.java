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

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "classroom_id")
  //  @ExportConfig(value = "教室编号")
    private Long classroomId;

    @Column(name = "real_arrive_num")
    private Integer realArriveNum;

    @Column(name = "should_arrive_num")
    private Integer shouldArriveNum;


    @Column(name = "attend_start_time")
    private Date attendStartTime;

    @Column(name = "attend_end_time")
    private Date attendEndTime;

    @Column(name = "describes")
    private String describes;

    @Column(name = "attend_status")
    private String attendStatus;

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Integer getRealArriveNum() {
        return realArriveNum;
    }

    public void setRealArriveNum(Integer realArriveNum) {
        this.realArriveNum = realArriveNum;
    }

    public Integer getShouldArriveNum() {
        return shouldArriveNum;
    }

    public void setShouldArriveNum(Integer shouldArriveNum) {
        this.shouldArriveNum = shouldArriveNum;
    }

    public Date getAttendStartTime() {
        return attendStartTime;
    }

    public void setAttendStartTime(Date attendStartTime) {
        this.attendStartTime = attendStartTime;
    }

    public Date getAttendEndTime() {
        return attendEndTime;
    }

    public void setAttendEndTime(Date attendEndTime) {
        this.attendEndTime = attendEndTime;
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

    @Override
    public String toString() {
        return "Attend{" +
                "attendId=" + attendId +
                ", courseId=" + courseId +
                ", classroomId=" + classroomId +
                ", realArriveNum=" + realArriveNum +
                ", shouldArriveNum=" + shouldArriveNum +
                ", attendStartTime=" + attendStartTime +
                ", attendEndTime=" + attendEndTime +
                ", describes='" + describes + '\'' +
                ", attendStatus='" + attendStatus + '\'' +
                '}';
    }
}
