package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.dto.StudentOutputDto;
import nchu.stu.attend.system.domain.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    List<StudentOutputDto> findAllStudent(Student student, QueryRequest request);
    List<Student> findAllStudent(Student student);
    void addStudent(StudentOutputDto student);
    void addStudent(Student student);
    void updateStudent(StudentOutputDto student);
    void updateStudent(Student student);
    void deleteStudent(String studentId);
    Student findById(String stuId);
}