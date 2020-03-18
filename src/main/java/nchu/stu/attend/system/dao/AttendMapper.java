package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.system.domain.Attend;

import java.util.List;

public interface AttendMapper extends MyMapper<Attend> {

    List<Attend> findAllAttend(Attend attend);

}
