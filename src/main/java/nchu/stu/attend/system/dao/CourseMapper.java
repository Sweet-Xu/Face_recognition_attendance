package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.Course;

import java.util.List;

public interface CourseMapper extends MyMapper<Course> {
    List<Course> findAllCourse(Course course);
}
