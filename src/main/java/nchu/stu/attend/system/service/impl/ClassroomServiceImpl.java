package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.ClassroomMapper;
import nchu.stu.attend.system.domain.Classroom;
import nchu.stu.attend.system.service.ClassroomService;
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
 * @date 2020/3/16
 */
@Service("ClassroomService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClassroomServiceImpl extends BaseService<Classroom> implements ClassroomService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public List<Classroom> findAllClassroom(Classroom classroom, QueryRequest request){
        try{
            return classroomMapper.findAllClassroom(classroom);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void addClassroom(Classroom classroom){
        try{
            this.save(classroom);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void updateClassroom(Classroom classroom){
        try{
            this.updateNotNull(classroom);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void deleteClassroom(String classroomId){
        this.delete(classroomId);
    }

    @Override
    public Classroom findById(Integer classroomId){
        System.out.println(classroomId);
        return this.selectByKey(classroomId);
    }
}
