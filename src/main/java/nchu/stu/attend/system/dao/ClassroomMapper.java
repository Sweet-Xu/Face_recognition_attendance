package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.Classroom;

import java.util.List;

public interface ClassroomMapper extends MyMapper<Classroom> {
    List<Classroom> findAllClassroom(Classroom classroom);
}
