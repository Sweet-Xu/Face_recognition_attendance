package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.Student;

import java.util.List;

public interface StudentMapper extends MyMapper<Student> {
    List<Student> findAllStudent(Student student);
}
