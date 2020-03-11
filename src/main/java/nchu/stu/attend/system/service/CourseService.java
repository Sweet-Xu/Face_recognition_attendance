package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Course;

import java.util.List;

public interface CourseService extends IService<Course>{

    Course findById(Long courseId);

    List<Course> findAllCourse(Course course, QueryRequest request);

    void deleteCourse(String ids);

    void addCourse(Course course);

    void updateCourse(Course course);



}
