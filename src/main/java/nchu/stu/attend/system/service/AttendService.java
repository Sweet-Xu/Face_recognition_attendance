package nchu.stu.attend.system.service;


import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.Attend;

import java.util.List;

public interface AttendService extends IService<Attend> {

    List<Attend> findAllAttend(Attend attend,QueryRequest request);

    void addAttend(Attend attend);

    void deleteAttend(String ids);

    Attend findById(Long attendId);

    void updateAttend(Attend attend);

}
