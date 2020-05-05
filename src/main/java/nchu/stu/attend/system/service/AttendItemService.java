package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.AttendItem;
import nchu.stu.attend.system.domain.dto.AttendDynamicsOutputDto;
import nchu.stu.attend.system.domain.dto.AttendResultExport;

import java.util.List;
import java.util.Map;

public interface AttendItemService extends IService<AttendItem> {

    List<AttendItem> findAllAttendItem(AttendItem attendItem, QueryRequest queryRequest);
    List<AttendItem> findAllAttendItem(AttendItem attendItem);
    void addAttendItem(AttendItem attendItem);
    void updateAttendItem(AttendItem attendItem);
    void deleteAttendItem(Integer id);
    AttendItem findById(Integer attendItemId);
    Map<String,Object> findAllAttendDetail(Long attendId);
    List<AttendResultExport> exportAttendAllResult(AttendItem attendItem);
    List<AttendDynamicsOutputDto> getAttendDynamics();
}
