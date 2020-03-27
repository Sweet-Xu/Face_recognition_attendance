package nchu.stu.attend.system.service.impl;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.dao.AttendMapper;
import nchu.stu.attend.system.dao.CourseMapper;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.AttendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author XuTian
 * @description
 * @date 2020/3/12
 */
//@Service("AttendService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AttendServiceImpl extends BaseService<Attend> implements AttendService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendMapper attendMapper;

    @Override
    public List<Attend> findAllAttend(Attend attend, QueryRequest request) {
        try{
            return attendMapper.findAllAttend(attend);
        }catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }


    @Autowired
//    private CourseMapper courseMapper;


    //增加考勤信息
    @Override
    public void addAttend(Attend attend) {

//        String username = attend.getUsername();
//        String classNo = username.substring(0,5);
//        attend.setClassNo(classNo);
//
//        Date attendTime = attend.getAttendTime();
//        System.out.println(attendTime);
//
//        String address = attend.getAddress();
//        System.out.println("  "+address);
//
//        Course course = courseMapper.findByDateAndAddress(attendTime,address);
//
//        System.out.println("\n"+course);
//        attend.setCourseName(course.getCourseName());
//        attend.setTeacher(course.getTeacher());
//        //判断考勤结果
//        String result = "1";
//
//        attend.setAttendResult(result);
        this.save(attend);
    }

    @Override
    public void deleteAttend(String ids) {
        List<String> list = Arrays.asList(ids.split(","));
        this.batchDelete(list,"attendId", Attend.class);
    }

    @Override
    public Attend findById(Long attendId) {
        return this.selectByKey(attendId);
    }

    @Override
    public void updateAttend(Attend attend) {
        try{
            this.updateNotNull(attend);
        }catch (Exception e){
            log.error("error",e);
        }
    }

}
