package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.CourseMapper;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.CourseService;
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
 * @date 2020/3/11
 */

@Service("CourseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseServiceImpl  extends BaseService<Course> implements CourseService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course findById(Long courseId) {
        return this.selectByKey(courseId);
    }

    @Override
    public List<Course> findAllCourse(Course course, QueryRequest request) {
        try{
            return courseMapper.findAllCourse(course);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteCourse(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"courseId", Course.class);
    }

    @Override
    public void addCourse(Course course) {
        try{
            this.save(course);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void updateCourse(Course course) {
        try{
            this.updateNotNull(course);
        }catch (Exception e){
            log.error("error",e);
        }
    }
}
