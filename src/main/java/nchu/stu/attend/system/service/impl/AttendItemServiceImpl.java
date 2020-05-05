package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.dao.AttendItemMapper;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.AttendItem;
import nchu.stu.attend.system.domain.AttendRule;
import nchu.stu.attend.system.domain.dto.AttendDynamicsOutputDto;
import nchu.stu.attend.system.domain.dto.AttendItemOutputDto;
import nchu.stu.attend.system.domain.dto.AttendResultExport;
import nchu.stu.attend.system.service.AttendItemService;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.CourseService;
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

    @Autowired
    private AttendService attendService;

    @Autowired
    private CourseService courseService;

    @Override
    public List<AttendItem> findAllAttendItem(AttendItem attendItem, QueryRequest request){
        try{
            return this.attendItemMapper.select(attendItem);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, Object> findAllAttendDetail(Long attendId) {
        Map<String,Object> map = new HashMap<>();
        AttendItem attendItem = new AttendItem();
        attendItem.setAttendId(attendId);
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

    //导出考勤表中所汇总的所有考勤结果
    @Override
    public List<AttendResultExport> exportAttendAllResult(AttendItem attendItem){
        try{
            List<AttendItem> attendItems = this.findAllAttendItem(attendItem,null);
            List<AttendResultExport> results = new ArrayList<>();
            for(int i=0;i<attendItems.size()/2; i++){
                AttendResultExport attendResultExport = new AttendResultExport();
                attendResultExport.setStudentId(attendItems.get(i).getStudentId());
                attendResultExport.setStudentName(attendItems.get(i).getStudentName());
                attendResultExport.setAttendName(attendItems.get(i).getAttendName());
                Attend attend = this.attendService.selectByKey(attendItems.get(i).getAttendId());
                attendResultExport.setTeacherName(attend.getManager());
                attendResultExport.setCourseName(this.courseService.findById(attend.getCourseId()).getCourseName());
                AttendItem attendItem1 = new AttendItem();
                attendItem1.setAttendId(attendItems.get(i).getAttendId());
                attendItem1.setStudentName(attendItems.get(i).getStudentName());
                List<AttendItem> attendItems1 = this.findAllAttendItem(attendItem1,null);
                String result1 = attendItems1.get(0).getAttendResult();
                String result2=attendItems1.get(1).getAttendResult();
//                attendResultExport.setSignInResult(result1);
//                attendResultExport.setSignOutResult(result2);
                attendResultExport.setSignInDateTime(attendItems1.get(0).getAttendTime());
                attendResultExport.setSignOutDateTime(attendItems1.get(1).getAttendTime());
                if (result1.equals("正常")&&result2.equals("正常")){
                    attendResultExport.setAttendResult("正常");
                }
                if(result1.equals("迟到")){
                    attendResultExport.setAttendResult("迟到");
                }
                if (result2.equals("早退")){
                    if(attendResultExport.getAttendResult()!=null&&(!attendResultExport.getAttendResult().equals(""))){
                        attendResultExport.setAttendResult("迟到、早退");
                    }else{
                        attendResultExport.setAttendResult("早退");
                    }
                }
                if(result1.equals("旷课")||result2.equals("旷课")){
                    attendResultExport.setAttendResult("旷课");
                }
                if(result1.equals("暂无记录")||result2.equals("暂无记录")){
                    attendResultExport.setAttendResult("旷课");
                }
                results.add(attendResultExport);
            }
            return results;
        }catch (Exception e){
            log.error("error",e);
            return new ArrayList<>();
        }
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

    //监测类
    //如果系统时间达到了 通过考勤表id获取到的考勤表的规则id，再通过规则id获取到的“打卡开始时间”
    //则将 考勤表状态设置为“进行中”
    //如果系统时间达到了，同上理获取到的“打卡结束时间”
    //则将 状态设置为“已结束”

    @Override
    public List<AttendDynamicsOutputDto> getAttendDynamics(){
        List<AttendDynamicsOutputDto> attendDynamicsOutputDtos = new ArrayList<>();
        //未写完
        return attendDynamicsOutputDtos;
    }



}
