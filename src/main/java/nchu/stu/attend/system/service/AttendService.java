package nchu.stu.attend.system.service;


import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.dto.AttendOutputDto;
import nchu.stu.attend.system.domain.dto.AttendTitleOutputDto;

import java.util.List;
import java.util.function.LongFunction;

public interface AttendService extends IService<Attend> {

    //查找所有考勤表记录
   List<Attend> findAttend(Attend attend);

    List<AttendOutputDto> findAllAttend(Attend attend);

    //查找所有进行时的考勤表
    List<AttendOutputDto> findAllProgressAttend();

    List<AttendOutputDto> updateAttend(AttendOutputDto attend);

    //手动增加考勤表记录
    long addAttend(Attend attend);

    void addAttend(AttendOutputDto dto);
    //删除考勤表记录
    void deleteAttend(Long attendId);

    AttendTitleOutputDto findById(Long attendId);

    //更新考勤表记录
    //void updateAttend(Attend attend);

    //批量插入考勤表
   void batchAddAttend();


    //根据当天课程插入考勤表
    void batchCreateAttendByToday();

    void batchCreateAllStudentAttendItem();


}
