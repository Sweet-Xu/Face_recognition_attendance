package nchu.stu.attend.system.controller;

import com.github.pagehelper.PageHelper;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.common.util.ResponseUtil;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.dto.AttendOutputDto;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author XuTian
 * @description
 * @date 2020/3/12
 */
@Controller
public class AttendController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendService attendService;


    @GetMapping("/api/attend")
    @ResponseBody
    public List<AttendOutputDto> attendList(QueryRequest request, Attend attend)
    {
        System.out.println(attend);
        //int total = attendService.findAllAttend(attend).size();
        //PageHelper.startPage(request.getCurrent(),request.getPageSize());
        //Map<String,Object> map = ResponseUtil.pageResult(this.attendService.findAllAttend(attend),total,true,request.getPageSize(),request.getCurrent());
        return this.attendService.findAllAttend(attend);
    }

    @GetMapping("/api/attend/progressList")
    @ResponseBody
    public List<AttendOutputDto> attendProgressList(){
        return this.attendService.findAllProgressAttend();
    }

    //@Log("增加考勤信息")
    //  @RequiresPermissions("attend:add")
    @PostMapping("/api/attend")
    @ResponseBody
    public List<AttendOutputDto> addAttend(@RequestBody AttendOutputDto dto){
        System.out.println("增加时获取的AttendOutputDto："+dto);
        this.attendService.addAttend(dto);
        Attend attend = new Attend();
        return this.attendService.findAllAttend(attend);
    }

    @PutMapping("/api/attend")
    @ResponseBody
    public List<AttendOutputDto> updateAttend(@RequestBody AttendOutputDto dto){
        return this.attendService.updateAttend(dto);
    }

    @GetMapping("attend/getAttend")
    @ResponseBody
    public ResponseBo getAttendById(Long attendId){
        try{
            Attend attend = this.attendService.findById(attendId);
            return ResponseBo.ok(attend);
        }catch (Exception e){
            log.error("获取考勤信息失败",e);
            return ResponseBo.error("获取考勤信息失败，请联系网站管理员！");
        }
    }



    //  @Log("更新考勤信息")
    // @RequiresPermissions("attend:update")
//    @PutMapping("api/attend")
//    @ResponseBody
//    public ResponseBo updateAttend(Attend attend){
//        try{
//            this.attendService.updateAttend(attend);
//            return ResponseBo.ok("修改考勤信息成功");
//        }catch (Exception e){
//            log.error("修改考勤信息失败",e);
//            return ResponseBo.error("修改考勤信息失败，请联系网站管理员！");
//        }
//    }

    // @Log("删除考勤信息")
    // @RequiresPermissions("attend:delete")
    @DeleteMapping("/api/attend")
    @ResponseBody
    public List<AttendOutputDto> deleteAttend(String id){
        System.out.println(id);
        this.attendService.deleteAttend(Long.valueOf(id));
        Attend attend =new Attend();
        return this.attendService.findAllAttend(attend);
    }



    //更新考勤表状态

//    @GetMapping("attend/excel")
//    @ResponseBody
//    public ResponseBo attendExcel(Attend attend){
//        try{
//            List<Attend> list = this.attendService.findAllAttend(attend);
//            return FileUtil.createExcelByPOIKit("考勤表",list,Attend.class);
//        }catch (Exception e){
//            log.error("导出考勤信息Excel失败");
//            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
//        }
//    }
//
//
//    @GetMapping("attend/csv")
//    @ResponseBody
//    public ResponseBo attendCsv(Attend attend) {
//        try {
//            List<Attend> list = this.attendService.findAllAttend(attend,null);
//            return FileUtil.createCsv("考勤表", list, Attend.class);
//        } catch (Exception e) {
//            log.error("获取考勤信息Csv失败", e);
//            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
//        }
//    }

}
