package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.AttendItemMapper;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.AttendItem;
import nchu.stu.attend.system.domain.AttendRule;
import nchu.stu.attend.system.domain.dto.AttendItemOutputDto;
import nchu.stu.attend.system.service.AttendItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XuTian
 * @description
 * @date 2020/3/16
 */
@Service("AttendItemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AttendItemServiceImpl extends BaseService<AttendItem> implements AttendItemService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendItemMapper attendItemMapper;

    @Override
    public List<AttendItem> findAllAttendItem(AttendItem attendItem, QueryRequest request){
        try{
            return attendItemMapper.findAllAttendItem(attendItem);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, Object> findAllAttendDetail() {
        Map<String,Object> map = new HashMap<>();
        AttendItem attendItem = new AttendItem();
        attendItem.setCheckType("签到");
        List<AttendItem> attendSignInItems = this.findAllAttendItem(attendItem);
//        List<AttendItemOutputDto> attendSignInItems =new ArrayList<>();
//        for(AttendItem t:attendSignInItems){
//            AttendItemOutputDto attendItemOutputDto = new AttendItemOutputDto();
//            attendItemOutputDto.setAttendCreateTime();
//        }
        map.put("basicGoods",attendSignInItems);
        attendItem.setCheckType("签退");
        List<AttendItem> attendSignOutItems = this.findAllAttendItem(attendItem);
        map.put("basicProgress",attendSignOutItems);
        return map;
    }

    @Override
    public List<AttendItem> findAllAttendItem(AttendItem attendItem) {
        return attendItemMapper.select(attendItem);
    }

    @Override
    public void addAttendItem(AttendItem attendItem){
        try{
            this.save(attendItem);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void updateAttendItem(AttendItem attendItem){
        try{
            this.updateNotNull(attendItem);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void deleteAttendItem(Integer attendItemId){
        this.delete(attendItemId);
    }

    @Override
    public AttendItem findById(Integer attendItemId){
        System.out.println(attendItemId);
        return this.selectByKey(attendItemId);
    }
}
