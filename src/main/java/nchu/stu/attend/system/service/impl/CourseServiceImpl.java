package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.CourseMapper;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author XuTian
 * @description
 * @date 2020/3/11
 */

@Service("CourseService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class CourseServiceImpl extends BaseService<Course> implements CourseService {

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
    public List<Course> findAllCourse(Course course) {
        return courseMapper.findAllCourse(course);
    }

    @Override
    public void deleteCourse(String courseId) {
        this.delete(courseId);
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

//    @Override
//    public List<Course> findAllTodayCourse() {
//        Course course = new Course();
//        course.setCourseDateTime(new Date());
//        List<Course> courses = courseMapper.findAllCourse(course);
//
//    }
    //batchAddCourses

//    @Override
//    public List<Course> findAllTodayCourse() {
////        Date date = new Date();
//////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//////        String d =sdf.format(date);
//////        try {
//////            Date newDate = sdf.parse(d);
//////            System.out.println("newDate:"+newDate);
//////            Course course = new Course();
//////            course.setCourseDate(newDate);
//////            List<Course> courses = courseMapper.findAllCourse(course);
//////            System.out.println(courses);
//////            return courses;
//////        }catch (Exception e){
//////            log.error("error", e);
//////            return new ArrayList<>();
//////        }
//        return new ArrayList<>();
//
//    }
}
