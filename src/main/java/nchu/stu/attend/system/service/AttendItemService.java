package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.AttendItem;

import java.util.List;

public interface AttendItemService extends IService<AttendItem> {

    List<AttendItem> findAllAttendItem(AttendItem attendItem, QueryRequest queryRequest);
    List<AttendItem> findAllAttendItem(AttendItem attendItem);
    void addAttendItem(AttendItem attendItem);
    void updateAttendItem(AttendItem attendItem);
    void deleteAttendItem(Integer id);
    AttendItem findById(Integer attendItemId);
}
