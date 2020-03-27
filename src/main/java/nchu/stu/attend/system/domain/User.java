package nchu.stu.attend.system.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author XuTian
 * @description
 * @date 2020/2/24
 */
public class User implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    /**
     * 账户状态1
     */
    public static final String STATUS_VALID = "1";

    public static final String STATUS_LOCK = "0";

    public static final String DEFAULT_AVATAR = "default.jpg";

    /**
     * 性别
     */
    public static final String SEX_MALE = "0";

    public static final String SEX_FEMALE = "1";

    public static final String SEX_UNKNOW = "2";

    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    // @ExportConfig(value = "用户名")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "user_create_time")
    private Date userCreateTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    //用户类型
    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_status")
    //@ExportConfig(value = "状态", convert = "s:0=锁定,1=有效")
    private String status = STATUS_VALID;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", classId=" + classId +
                ", userEmail='" + userEmail + '\'' +
                ", userCreateTime=" + userCreateTime +
                ", lastLoginTime=" + lastLoginTime +
                ", userType='" + userType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
