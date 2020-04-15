package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Classroom;

import java.util.List;

public interface ClassroomService extends IService<Classroom> {

    List<Classroom> findAllClassroom(Classroom classroom, QueryRequest queryRequest);
    void addClassroom(Classroom classroom);
    void updateClassroom(Classroom classroom);
    void deleteClassroom(String ids);
    Classroom findById(Integer classroomId);
}
