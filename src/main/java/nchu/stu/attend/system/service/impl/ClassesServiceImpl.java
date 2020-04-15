package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.ClassesMapper;
import nchu.stu.attend.system.domain.Classes;
import nchu.stu.attend.system.service.ClassesService;
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
@Service("ClassesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ClassesServiceImpl extends BaseService<Classes> implements ClassesService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public List<Classes> findAllClasses(Classes classes, QueryRequest request){
        try{
            return classesMapper.findAllClasses(classes);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Classes> findAllClasses(Classes classes) {
        return this.classesMapper.select(classes);
    }

    @Override
    public void addClasses(Classes classes){
        try{
            this.save(classes);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void updateClasses(Classes classes){
        try{
            this.updateNotNull(classes);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void deleteClasses(String classId){
        this.delete(classId);
    }

    @Override
    public Classes findById(Integer classId){
        System.out.println(classId);
        return this.selectByKey(classId);
    }
    
}
