package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.dao.AttendMapper;
import nchu.stu.attend.system.dao.CourseMapper;
import nchu.stu.attend.system.domain.*;
import nchu.stu.attend.system.domain.dto.AttendOutputDto;
import nchu.stu.attend.system.service.AttendRuleService;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.ClassesService;
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

    @Autowired
    private ClassesService classesService;


    @Override
    public List<Attend> findAttend(Attend attend) {
        try {
            return attendMapper.findAllAttend(attend);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<AttendOutputDto> findAllAttend(Attend attend) {
        List<AttendOutputDto> dtos = new ArrayList<>();
        List<Attend> attends = this.attendMapper.select(attend);
        for (Attend t : attends) {
            AttendOutputDto dto = new AttendOutputDto();
            AttendRule rule = attendRuleService.findById(t.getRuleId());
            Course course = courseService.findById(t.getCourseId());
            dto.setClassId(t.getClassId());
            dto.setCourseName(course.getCourseName());
            dto.setRuleName(rule.getRuleName());
            dto.setId(String.valueOf(t.getAttendId()));
            dto.setTitle(t.getAttendName());
            dto.setOwner(t.getManager());
            dto.setStatus("active");
            dto.setClassroomId(t.getClassroomId());
            dto.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png");
            dto.setCover("https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png");
            //Logo才是有用的图标显示
            dto.setLogo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587198205593&di=95c2e9a0d158093659f7a93a090bced4&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic2%2Fcover%2F00%2F45%2F65%2F5814f401ea5a9_610.jpg");
            dto.setCreatedAt(t.getAttendCreateTime());
            dto.setPercent((int)Math.ceil(Math.random() * 50) + 50);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<AttendOutputDto> findAllProgressAttend() {
        String logos[]={ "https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png", // Alipay
                "https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png", // Angular
                "https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png", // Ant Design
                "https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png", // Ant Design Pro
                "https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png", // Bootstrap
                "https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png", // React
                "https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png", // Vue
                "https://gw.alipayobjects.com/zos/rmsportal/nxkuOJlFJuAUhzlMTCEe.png"};// Webpack}
        List<AttendOutputDto> dtos = new ArrayList<>();
        Attend attend = new Attend();
        attend.setAttendStatus("进行中");
        List<Attend> attends = this.attendMapper.select(attend);
        for(Attend t:attends){
            AttendOutputDto dto = new AttendOutputDto();
            dto.setId(String.valueOf(t.getAttendId()));
            dto.setTitle(t.getAttendName());
            dto.setMember(t.getManager());
           // dto.setLogo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587198205593&di=95c2e9a0d158093659f7a93a090bced4&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic2%2Fcover%2F00%2F45%2F65%2F5814f401ea5a9_610.jpg");
            dto.setLogo(logos[(int)(Math.random()*7)]);
            dto.setUpadtedAt(new Date());
            dto.setDescription(t.getClassroomId());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<AttendOutputDto> updateAttend(AttendOutputDto dto){
        Attend attend  = new Attend();
        attend.setAttendId(Long.valueOf(dto.getId()));
        attend.setAttendName(dto.getTitle());
        attend.setManager(dto.getOwner());
        attend.setAttendCreateTime(dto.getCreatedAt());
        attend.setClassroomId(dto.getClassroomId());
        this.updateNotNull(attend);
        return this.findAllAttend(new Attend());
    }

    //增加考勤信息
    @Override
    public void addAttend(Attend attend) {
        this.save(attend);
    }

    @Override
    public void addAttend(AttendOutputDto dto){
        Attend attend = new Attend();
        attend.setClassId(dto.getClassId());
        attend.setClassroomId(dto.getClassroomId());
        attend.setCourseId(Long.valueOf(dto.getCourseName()));
        attend.setRuleId(Integer.valueOf(dto.getRuleName()));
        attend.setAttendName(dto.getTitle());
        attend.setAttendCreateTime(dto.getCreatedAt());
        attend.setManager(dto.getOwner());
        this.save(attend);
    }

    @Override
    public void deleteAttend(Long attendId) {
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

    //
    //public void

}
