package nchu.stu.attend.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author XuTian
 * @description 教室类
 * @date 2020/3/16
 */
@Table(name = "t_classroom")
public class Classroom implements Serializable {
    private static final Long serialVersionUID = -4852732617765810959L;

    @Id
    @Column(name = "classroom_id")
    private String classroomId;

//    @Column(name = "classroom_name")
//    private String classroomName;

    @Column(name = "describes")
    private String describes;

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

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId='" + classroomId + '\'' +
                ", describes='" + describes + '\'' +
                '}';
    }
}
