package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.Course;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface CourseMapper extends MyMapper<Course> {
    List<Course> findAllCourse(Course course);
    Course findByDateAndAddress(Date attendTime,String address);
}
