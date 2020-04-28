package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.StudentMapper;
import nchu.stu.attend.system.domain.dto.StudentOutputDto;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.domain.User;
import nchu.stu.attend.system.service.StudentService;
import nchu.stu.attend.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuTian
 * @description
 * @date 2020/2/22
 */
@Service("StudentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentServiceImpl extends BaseService<Student> implements StudentService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<StudentOutputDto> findAllStudent(Student student, QueryRequest request){
        List<StudentOutputDto> dtos= new ArrayList<>();
        List<Student> students = this.studentMapper.findAllStudent(student);
        for (Student t: students) {
            User u = userService.findById(t.getUserId());
            StudentOutputDto dto = new StudentOutputDto();
            if(u!=null) {
                dto.setUserId(u.getUserId());
                dto.setPassword(u.getPassword());
                dto.setUsername(u.getUsername());
                dto.setUserType(u.getUserType());
                dto.setUserCreateTime(u.getUserCreateTime());
                dto.setLastLoginTime(u.getLastLoginTime());
                dto.setUserStatus(u.getUserStatus());
            }
            dto.setStudentId(t.getStudentId());
            dto.setClassId(t.getClassId());
            dto.setStudentName(t.getStudentName());
            dto.setStudentGender(t.getStudentGender());
            dto.setStudentEmail(t.getStudentEmail());
            dto.setStudentQQ(t.getStudentQQ());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<Student> findAllStudent(Student student) {
        return this.studentMapper.select(student);
    }

    @Override
    public void addStudent(StudentOutputDto dto){
        User user = new User();
        Student student= new Student();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        user.setUserCreateTime(dto.getUserCreateTime());
        user.setLastLoginTime(dto.getLastLoginTime());
        user.setUserStatus(dto.getUserStatus());

        student.setStudentId(dto.getStudentId());
        student.setClassId(dto.getClassId());
        student.setStudentName(dto.getStudentName());
        student.setStudentGender(dto.getStudentGender());
        student.setStudentEmail(dto.getStudentEmail());
        student.setStudentQQ(dto.getStudentQQ());
        student.setUserId(dto.getUserId());
        long userId = userService.addUser(user);
        student.setUserId(userId);
        this.save(student);
    }

    @Override
    public void addStudent(Student student){
        User user = new User();
        user.setUsername(student.getStudentId());
        user.setPassword(student.getStudentId());
        user.setUserType("2");
        user.setUserStatus("1");
        long userId = userService.addUser(user);
        student.setUserId(userId);
        this.save(student);
    }

    @Override
    public void updateStudent(StudentOutputDto dto){
        User user = new User();
        Student student = new Student();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        user.setUserCreateTime(dto.getUserCreateTime());
        user.setLastLoginTime(dto.getLastLoginTime());
        user.setUserStatus(dto.getUserStatus());
        System.out.println(user);
        student.setStudentId(dto.getStudentId());
        student.setClassId(dto.getClassId());
        student.setStudentGender(dto.getStudentGender());
        student.setStudentName(dto.getStudentName());
        student.setStudentEmail(dto.getStudentEmail());
        student.setStudentQQ(dto.getStudentQQ());
        student.setUserId(dto.getUserId());
        //根据主键更新
        userService.updateUser(user);
        this.updateNotNull(student);
    }

    @Override
    public void updateStudent(Student student) {
        this.updateNotNull(student);
    }

    @Override
    public void deleteStudent(String studentId){
        System.out.println(studentId);
        Student student = studentMapper.selectByPrimaryKey(studentId);
        this.delete(studentId);
        userService.deleteUsers(student.getUserId());
    }

    @Override
    public Student findById(String studentId){
        System.out.println(studentId);
        return this.selectByKey(studentId);
    }

}
