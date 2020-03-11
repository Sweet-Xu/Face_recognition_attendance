package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.StudentMapper;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<Student> findAllStudent(Student student, QueryRequest request){
        try{
            return studentMapper.findAllStudent(student);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void addStudent(Student student){
        try{
            this.save(student);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void updateStudent(Student student){
        try{
            this.updateNotNull(student);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void deleteStudent(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"stuId",Student.class);
    }

    @Override
    public Student findById(Long studentId){
        System.out.println(studentId);
        return this.selectByKey(studentId);
    }

}
