package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.dao.AttendMapper;
import nchu.stu.attend.system.dao.CourseMapper;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.AttendRule;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.AttendRuleService;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author XuTian
 * @description
 * @date 2020/3/12
 */
@Service("AttendService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AttendServiceImpl extends BaseService<Attend> implements AttendService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendMapper attendMapper;

    @Autowired
    private AttendRuleService attendRuleService;

    @Autowired
    private CourseService courseService;

    @Override
    public List<Attend> findAllAttend(Attend attend, QueryRequest request) {
        try{
            return attendMapper.findAllAttend(attend);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    //增加考勤信息
    @Override
    public void addAttend(Attend attend) {
        this.save(attend);
    }

    @Override
    public void deleteAttend(String attendId) {
        this.delete(attendId);
    }

    @Override
    public Attend findById(Long attendId) {
        return this.selectByKey(attendId);
    }

//    //用触发器控制
//    @Override
//    public void updateAttend(Attend attend) {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        String a = sdf.format(date);
//        AttendRule attendRule = attendRuleService.findById(attend.getRuleId());
//        String b =  attendRule.getCheckEndTime();
//        if(a.equals(b)){
//            //设置为考勤结束
//            attend.setAttendStatus("0");
//        }
//        this.updateNotNull(attend);
//    }

    @Override
    public void batchAddAttend(){
//        List<Course> courses = courseService.findAllTodayCourse();
//        for (Course course:courses){
//            Attend attend = new Attend();
//            attend.setCourseId(course.getCourseId());
//            attend.setClassroomId(course.getClassroomId());
//            //默认为“课堂考勤规则一”
//            attend.setRuleId(1);
//            //考勤状态默认为“正在考勤”
//            attend.setAttendStatus("1");
//            this.save(attend);
//        }

    }



}
