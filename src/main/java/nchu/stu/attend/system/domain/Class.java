package nchu.stu.attend.system.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XuTian
 * @description 班级类
 * @date 2020/3/16
 */
@Table(name = "t_class")
public class Class implements Serializable {
    private static final long serialVersionUID = -4852732617765810959L;

    @Id
    @Column(name = "class_id")
    private Integer classId;

    @Column(name = "class_people_name")
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

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", classPeopleNum=" + classPeopleNum +
                '}';
    }
}
