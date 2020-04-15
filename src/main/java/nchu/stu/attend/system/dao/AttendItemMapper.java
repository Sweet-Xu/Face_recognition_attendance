package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.AttendItem;

import java.util.List;

public interface AttendItemMapper extends MyMapper<AttendItem> {
    List<AttendItem> findAllAttendItem(AttendItem attendItem);
}
