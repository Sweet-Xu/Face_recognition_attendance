package nchu.stu.attend.system.controller;

import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.service.AttendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author XuTian
 * @description
 * @date 2020/3/12
 */
//@Controller
public class AttendController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendService attendService;

    @GetMapping("attend/list")
    @ResponseBody
    public Map<String,Object> findAllAttend(QueryRequest request, Attend attend){
        return super.selectByPageNumSize(request,()->attendService.findAllAttend(attend,request));
    }

    @PostMapping("attend/add")
    @ResponseBody
    public ResponseBo addAttend(Attend attend){
        try {
            this.attendService.addAttend(attend);
            return ResponseBo.ok("增加考勤记录成功");
        }catch (Exception e){
            log.error("增加考勤记录失败",e);
            return ResponseBo.error("增加考勤信息失败，请联系网站管理员");
        }
    }

    @DeleteMapping("attend/delete")
    @ResponseBody
    public  ResponseBo deleteAttend(String ids){
        try{
            this.attendService.deleteAttend(ids);
            return ResponseBo.ok("删除考勤信息成功");
        }catch (Exception e){
            log.error("删除考勤信息失败",e);
            return ResponseBo.error("删除考勤信息失败，请联系网站管理员");
        }
    }

    @PutMapping("attend/update")
    @ResponseBody
    public ResponseBo updateAttend(Attend attend){
        try{
            this.attendService.updateAttend(attend);
            return ResponseBo.ok("修改考勤信息成功");
        }catch (Exception e){
            log.error("修改考勤信息失败",e);
            return ResponseBo.error("修改考勤信息失败，请联系网站管理员");
        }
    }

    @GetMapping("attend/excel")
    @ResponseBody
    public ResponseBo attendExcel(Attend attend){
        try{
            List<Attend> list = this.attendService.findAllAttend(attend,null);
            return FileUtil.createExcelByPOIKit("考勤表",list,Attend.class);
        }catch (Exception e){
            log.error("导出考勤信息Excel失败",e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

}
