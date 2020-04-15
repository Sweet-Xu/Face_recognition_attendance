package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Classes;
import nchu.stu.attend.system.domain.Classes;

import java.util.List;

public interface ClassesService extends IService<Classes> {

    List<Classes> findAllClasses(Classes classes, QueryRequest queryRequest);
    List<Classes> findAllClasses(Classes classes);
    void addClasses(Classes classes);
    void updateClasses(Classes classes);
    void deleteClasses(String classId);
    Classes findById(Integer classId);

}
