package nchu.stu.attend.system.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "attend_time")
    private Date attendTime;

    @Column(name = "attend_reslut")
    private String attendReslut;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public String getAttendReslut() {
        return attendReslut;
    }

    public void setAttendReslut(String attendReslut) {
        this.attendReslut = attendReslut;
    }

    @Override
    public String toString() {
        return "AttendItem{" +
                "attendItemId=" + attendItemId +
                ", attendId=" + attendId +
                ", userId=" + userId +
                ", attendTime=" + attendTime +
                ", attendReslut='" + attendReslut + '\'' +
                '}';
    }
}
