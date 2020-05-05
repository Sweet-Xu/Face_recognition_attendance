package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.dao.AttendMapper;
import nchu.stu.attend.system.dao.CourseMapper;
import nchu.stu.attend.system.domain.*;
import nchu.stu.attend.system.domain.dto.AttendOutputDto;
import nchu.stu.attend.system.domain.dto.AttendTitleOutputDto;
import nchu.stu.attend.system.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    private TeacherService teacherService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendItemService attendItemService;


    @Override
    public List<Attend> findAttend(Attend attend) {
        try {
            List<Attend> attends =  this.attendMapper.select(attend);
            return attends;
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
           // dto.setLogo("https://www.canva.cn/design/DAD7FUT8VKo/OxipnQM2WvwpnOLsqcaXgQ/view");
            dto.setCreatedAt(t.getAttendCreateTime());
            dto.setPercent((int)Math.ceil(Math.random() * 50) + 50);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<AttendOutputDto> findAllProgressAttend() {
        String logos[]={
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588426557922&di=8f28e01f2393dd9d41b4fdc1f27326d2&imgtype=0&src=http%3A%2F%2Fsrc.onlinedown.net%2Fimages%2Fxcs%2F10%2F59e6cd8d48129.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588426887785&di=d7ba161e170dffc60398825949ccfaa7&imgtype=0&src=http%3A%2F%2Ffscomps.fotosearch.com%2Fcompc%2Fcsp%2Fcsp995%2Fk16581492.jpg",
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1877374776,909727887&fm=15&gp=0.jpg",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3227579516,4111755553&fm=15&gp=0.jpg",
               // "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2311544639,176473718&fm=15&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588427048850&di=4c81ed5770a790d0123b3330647b841c&imgtype=0&src=http%3A%2F%2Fen.pimg.jp%2F020%2F349%2F333%2F1%2F20349333.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588427130233&di=cbf0338fdfeb193f2ff29db441a02746&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fca431f58ae2a60b02cea650860f6d4a8333e21935cb2-aHGJbk_fw658"
        };
//                "https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png", // Angular
//                "https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png", // Ant Design
//                "https://gw.alipayobjects.com/zos/rmsportal/sfjbOqnsXXJgNCjCzDBL.png", // Ant Design Pro
//                "https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png", // Bootstrap
//                "https://gw.alipayobjects.com/zos/rmsportal/kZzEzemZyKLKFsojXItE.png", // React
//                "https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png", // Vue
//                "https://gw.alipayobjects.com/zos/rmsportal/nxkuOJlFJuAUhzlMTCEe.png"};// Webpack}
        List<AttendOutputDto> dtos = new ArrayList<>();
        Attend attend = new Attend();
        attend.setAttendStatus("进行中");
        List<Attend> attends = this.attendMapper.select(attend);
        for(Attend t:attends){
            AttendOutputDto dto = new AttendOutputDto();
            dto.setId(String.valueOf(t.getAttendId()));
            dto.setTitle(t.getAttendName());
            dto.setMember(t.getManager());
            //dto.setLogo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587198205593&di=95c2e9a0d158093659f7a93a090bced4&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic2%2Fcover%2F00%2F45%2F65%2F5814f401ea5a9_610.jpg");
            //dto.setLogo(logos[(int)(Math.random()*6)]);
            dto.setLogo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588427130233&di=cbf0338fdfeb193f2ff29db441a02746&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fca431f58ae2a60b02cea650860f6d4a8333e21935cb2-aHGJbk_fw658");
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
    public long addAttend(Attend attend) {
        this.save(attend);
        return attend.getAttendId();
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
    public AttendTitleOutputDto findById(Long attendId) {
        Attend attend = this.selectByKey(attendId);
        AttendRule attendRule=this.attendRuleService.findById(attend.getRuleId());
        AttendTitleOutputDto outputDto =  new AttendTitleOutputDto();
        outputDto.setAttendName(attend.getAttendName());
        outputDto.setClassId(attend.getClassId());
        outputDto.setClassroomId(attend.getClassroomId());
        outputDto.setTeacherName(attend.getManager());
        outputDto.setRuleName(attendRule.getRuleName());
        Course course = this.courseService.findById(attend.getCourseId());
        outputDto.setCourseName(course.getCourseName());
        return outputDto;
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

    //根据当天的课程生成对应的考勤表
    @Override
    public void batchCreateAttendByToday(){
        try {
        Course course = new Course();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String string = sdf.format(new Date());
        Date newDate = sdf.parse(string);
        System.out.println("获取的"+newDate);
        Course course1 = this.courseService.findById(1L);
        System.out.println("课程表中的"+course1.getCourseDate());
        course.setCourseDate(newDate);
        List<Course> courses = this.courseService.findAllCourse(course);
        System.out.println(courses);
        for (Course c:courses){
            Attend attend = new Attend();
            attend.setCourseId(c.getCourseId());
            attend.setClassroomId(c.getClassroomId());
            attend.setAttendStatus("未开始");
            attend.setRuleId(1);
            Teacher teacher = this.teacherService.findById(c.getTeacherId());
            attend.setManager(teacher.getTeacherName());
            attend.setAttendCreateTime(newDate);
            attend.setClassId(c.getClassId());
            long  attendId = this.addAttend(attend);
            attend.setAttendId(attendId);
            attend.setAttendName("考勤表"+attendId);
            this.attendService.updateNotNull(attend);
         }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //根据当天已生成的考勤表，定时任务 获取打卡开始时间 ，然后默认生成该考勤表应考勤的所有学生明细项
    //增加结束后 将考勤表的状态设置为了进行中
    //还有 达到打卡结束时间时 考勤表的状态要设置为 已结束
    @Override
    public void batchCreateAllStudentAttendItem(){
        Attend attend = new Attend();
        attend.setAttendStatus("未开始");
        List<Attend> attends = this.attendService.findAttend(attend);
        for (Attend t:attends) {
            Student student = new Student();
            student.setClassId(t.getClassId());
            List<Student> students = this.studentService.findAllStudent(student);
            for (Student s : students) {
                AttendItem attendItem = new AttendItem();
                attendItem.setAttendId(t.getAttendId());
                attendItem.setAttendName(t.getAttendName());
                attendItem.setStudentId(s.getStudentId());
                attendItem.setStudentName(s.getStudentName());
                attendItem.setCheckType("签到");
                attendItem.setAttendResult("暂无记录");
                this.attendItemService.addAttendItem(attendItem);
            }
            for (Student s : students) {
                AttendItem attendItem1 = new AttendItem();
                attendItem1.setAttendId(t.getAttendId());
                attendItem1.setAttendName(t.getAttendName());
                attendItem1.setStudentId(s.getStudentId());
                attendItem1.setStudentName(s.getStudentName());
                attendItem1.setCheckType("签退");
                attendItem1.setAttendResult("暂无记录");
                this.attendItemService.addAttendItem(attendItem1);
            }
            t.setAttendStatus("进行中");
            this.attendService.updateNotNull(t);
        }


    }
}
