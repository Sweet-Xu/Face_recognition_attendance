package nchu.stu.attend.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import nchu.stu.attend.common.annotation.ExportConfig;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author XuTian
 * @description 个人考勤记录项类
 * @date 2020/3/16
 */
@Table(name = "t_attendItem")
public class AttendItem implements Serializable {

    private static final long serialVersionUID = -4852732617765810959L;

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "attendItem_id")
    private Long attendItemId;

    @Column(name = "attend_id")
    private Long attendId;

    @Column(name = "attend_name")
    @ExportConfig(value = "考勤表名称")
    private String attendName;

    @Column(name = "student_id")
    @ExportConfig(value = "学号")
    private String studentId;

    @Column(name = "student_name")
    @ExportConfig(value = "学生姓名")
    private String studentName;

    @Column(name = "check_type")
//    @ExportConfig(value = "考勤类型")
    private String checkType;

    @Column(name = "attend_result")
    @ExportConfig(value = "考勤结果")
    private String attendResult;

//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "attend_time")
  //  @ExportConfig(value = "打卡时间",convert = "c:nchu.stu.attend.common.util.poi.convert.TimeConvert")
    private Date attendTime;

//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")


    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getAttendItemId() {
        return attendItemId;
    }

    public void setAttendItemId(Long attendItemId) {
        this.attendItemId = attendItemId;
    }

    public Long getAttendId() {
        return attendId;
    }

    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public String getAttendResult() {
        return attendResult;
    }

    public void setAttendResult(String attendResult) {
        this.attendResult = attendResult;
    }

    public String getAttendName() {
        return attendName;
    }

    public void setAttendName(String attendName) {
        this.attendName = attendName;
    }



    @Override
    public String toString() {
        return "AttendItem{" +
                "attendItemId=" + attendItemId +
                ", attendId=" + attendId +
                ", studentId='" + studentId + '\'' +
                ", attendTime=" + attendTime +
                ", attendResult='" + attendResult + '\'' +
                '}';
    }
}
