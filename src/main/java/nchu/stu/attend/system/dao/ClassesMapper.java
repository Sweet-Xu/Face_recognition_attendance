package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.Classes;

import java.util.List;

public interface ClassesMapper extends MyMapper<Classes> {
    List<Classes> findAllClasses(Classes classes);
}
