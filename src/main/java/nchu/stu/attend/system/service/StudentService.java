package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    List<Student> findAllStudent(Student student, QueryRequest request);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(String ids);
    Student findById(Long stuId);
}