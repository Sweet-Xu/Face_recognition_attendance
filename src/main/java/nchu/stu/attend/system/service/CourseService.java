package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Course;

import java.util.List;

public interface CourseService extends IService<Course>{

    Course findById(Long courseId);

    List<Course> findAllCourse(Course course, QueryRequest request);
    List<Course> findAllCourse(Course course);

    //查找当天所有课程

    void deleteCourse(String courseId);

    void addCourse(Course course);

    void updateCourse(Course course);

    List<Course> findAllTodayCourse();




}
