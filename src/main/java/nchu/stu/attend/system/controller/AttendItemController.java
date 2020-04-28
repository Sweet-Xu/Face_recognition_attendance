package nchu.stu.attend.system.controller;

import com.github.pagehelper.PageHelper;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.common.util.ResponseUtil;
import nchu.stu.attend.system.domain.*;
import nchu.stu.attend.system.domain.dto.AttendOutputDto;
import nchu.stu.attend.system.domain.dto.CheckRecordInputDto;
import nchu.stu.attend.system.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author XuTian
 * @description
 * @date 2020/3/16
 */
@Controller
public class AttendItemController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendItemService attendItemService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CameraService cameraService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendRuleService attendRuleService;

    @GetMapping("/api/receiveAttendItem")
    @ResponseBody
    public void receiveAttendItem(CheckRecordInputDto inputDto){
        try {
       // Face face = this.faceService.findById(inputDto.getId());
        Student student  = this.studentService.findById(inputDto.getStudentId());
        Camera camera = this.cameraService.findById(inputDto.getCameraId());
        Attend attend = new Attend();
        attend.setAttendStatus("进行中");
        attend.setClassroomId(camera.getClassroomId());
        List<Attend> attends = this.attendService.findAttend(attend);
        for(Attend t:attends){
            //获取该考勤单所考勤的课程
           // Course course = this.courseService.findById(t.getCourseId());
            //获取该考勤单使用的规则
            AttendRule attendRule = this.attendRuleService.findById(t.getRuleId());
            AttendItem attendItem = new AttendItem();
            attendItem.setAttendId( t.getAttendId());
            attendItem.setStudentId(inputDto.getStudentId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentDate = new Date();
            String dateString = dateFormat.format(currentDate);
            System.out.println("格式化以后的当前的时间："+dateString);
            StringBuffer stringBuffer = new StringBuffer(dateString);
            String courseStartTimeString = stringBuffer.replace(12,20,attendRule.getCourseStartTime()).toString();
            String courseEndTimeString = stringBuffer.replace(12,20,attendRule.getCourseEndTime()).toString();
            String checkStartTimeString = stringBuffer.replace(12,20,attendRule.getCheckStartTime()).toString();
            String checkEndTimeString = stringBuffer.replace(12,20,attendRule.getCheckEndTime()).toString();

            System.out.println("替换后的课程开始时间："+courseStartTimeString);
            System.out.println("替换后的课程结束时间："+courseEndTimeString);
            System.out.println("替换后的打卡开始时间："+checkStartTimeString);
            System.out.println("替换后的打卡结束时间："+checkEndTimeString);

            Date courseStartTimeDate = dateFormat.parse(courseStartTimeString);
            Date courseEndTimeDate = dateFormat.parse(courseEndTimeString);
            Date checkStartTimeDate = dateFormat.parse(checkStartTimeString);
            Date checkEndTimeDate= dateFormat.parse(checkEndTimeString);
            //用来判断签到还是签退的时间,第二小节课开始的时间
            Long compareSecond = courseStartTimeDate.getTime()+1000*60*55;
            //实际打卡时间
            Long checkTimeSecond = inputDto.getCheckTime().getTime();
            //可开始打卡时间
            Long checkStartTimeSecond = checkStartTimeDate.getTime();
            //结束打卡时间
            Long checkEndTimeSecond = checkEndTimeDate.getTime();
            //课程开始时间
            Long courseStartTimeSecond = courseStartTimeDate.getTime();
            //课程结束时间
            Long courseEndTimeSecond = courseStartTimeDate.getTime();
            if(checkStartTimeSecond<=checkTimeSecond &&checkTimeSecond< compareSecond){
                attendItem.setCheckType("签到");
                List<AttendItem> signInAttendItems = this.attendItemService.findAllAttendItem(attendItem);
                for (AttendItem a:signInAttendItems){
                    a.setAttendTime(inputDto.getCheckTime());
                    if (checkTimeSecond <= courseStartTimeSecond){
                        a.setAttendResult("正常");
                    }else if(courseStartTimeSecond <checkTimeSecond && checkTimeSecond<=(courseStartTimeSecond+Integer.valueOf(attendRule.getNormalLateMin())*1000*60)){
                        a.setAttendResult("迟到");
                    }else{
                        a.setAttendResult("旷课");
                    }
                }
            }
            if (compareSecond<=checkTimeSecond && checkTimeSecond<=checkEndTimeSecond){
                attendItem.setCheckType("签退");
                List<AttendItem> signOutAttendItems = this.attendItemService.findAllAttendItem(attendItem);
                for (AttendItem b:signOutAttendItems){
                    b.setAttendTime(inputDto.getCheckTime());
                    if(checkTimeSecond >= courseEndTimeSecond){
                        b.setAttendResult("正常");
                    }else if (courseEndTimeSecond > checkTimeSecond && checkTimeSecond >= (courseEndTimeSecond-Integer.valueOf(attendRule.getNormalLeaveEarlyMin())*1000*60)){
                        b.setAttendResult("早退");
                    }else{
                        b.setAttendResult("旷课");
                    }
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/api/attendItem")
    @ResponseBody
    public Map<String ,Object> attendItemList(QueryRequest request, AttendItem attendItem, HttpSession session){
        User user =(User)session.getAttribute("currentUser");
        System.out.println(user.getRole());
        if(user.getRole()==2) {
            attendItem.setStudentId(user.getUsername());
        }
        int total = attendItemService.findAllAttendItem(attendItem).size();
        PageHelper.startPage(request.getCurrent(),request.getPageSize());
        Map<String,Object> map = ResponseUtil.pageResult(this.attendItemService.findAllAttendItem(attendItem),total,true,request.getPageSize(),request.getCurrent());
        return map;
    }

    @GetMapping("/api/attendDetail")
    @ResponseBody
    public Map<String,Object> attendDetail(){
       return this.attendItemService.findAllAttendDetail();
    }

    //@Log("增加考勤明细信息")
    //  @RequiresPermissions("attendItem:add")
    @PostMapping("/api/attendItem")
    @ResponseBody
    public ResponseBo addAttendItem(@RequestBody AttendItem attendItem){
        try {
            //System.out.println(attendItem);
            this.attendItemService.addAttendItem(attendItem);
            return ResponseBo.ok("增加考勤明细信息成功！");
        }catch (Exception e){
            log.error("增加考勤明细信息失败",e);
            return ResponseBo.error("增加考勤明细失败，请联系网站管理员");
        }
    }

    @GetMapping("attendItem/getAttendItem")
    @ResponseBody
    public ResponseBo getAttendItemById(Integer attendItemId){
        try{
            AttendItem attendItem = this.attendItemService.findById(attendItemId);
            return ResponseBo.ok(attendItem);
        }catch (Exception e){
            log.error("获取考勤明细信息失败",e);
            return ResponseBo.error("获取考勤明细信息失败，请联系网站管理员！");
        }
    }

    //  @Log("更新考勤明细信息")
    // @RequiresPermissions("attendItem:update")
    @PutMapping("/api/attendItem")
    @ResponseBody
    public ResponseBo updateAttendItem(@RequestBody AttendItem attendItem){
        try{
            System.out.println(attendItem);
            this.attendItemService.updateAttendItem(attendItem);
            return ResponseBo.ok("修改考勤明细信息成功");
        }catch (Exception e){
            log.error("修改考勤明细信息失败",e);
            return ResponseBo.error("修改考勤明细信息失败，请联系网站管理员！");
        }
    }

    // @Log("删除考勤明细信息")
    // @RequiresPermissions("attendItem:delete")
    @DeleteMapping("/api/attendItem")
    @ResponseBody
    public ResponseBo deleteAttendItem(Integer attendItemId){
        try{
            this.attendItemService.deleteAttendItem(attendItemId);
            return ResponseBo.ok("删除考勤明细信息成功");
        }catch(Exception e){
            log.error("删除考勤明细信息失败");
            return ResponseBo.error("删除考勤明细信息失败，请联系网站管理员！");
        }
    }


    @GetMapping("attendItem/excel")
    @ResponseBody
    public ResponseBo attendItemExcel(AttendItem attendItem){
        try{
            List<AttendItem> list = this.attendItemService.findAllAttendItem(attendItem,null);
            return FileUtil.createExcelByPOIKit("考勤明细表",list,AttendItem.class);
        }catch (Exception e){
            log.error("导出考勤明细信息Excel失败");
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }


    @GetMapping("attendItem/csv")
    @ResponseBody
    public ResponseBo attendItemCsv(AttendItem attendItem) {
        try {
            List<AttendItem> list = this.attendItemService.findAllAttendItem(attendItem,null);
            return FileUtil.createCsv("考勤明细表", list, AttendItem.class);
        } catch (Exception e) {
            log.error("获取考勤明细信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
}
