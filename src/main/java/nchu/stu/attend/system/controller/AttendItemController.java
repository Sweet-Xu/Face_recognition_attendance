package nchu.stu.attend.system.controller;

import com.github.pagehelper.PageHelper;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.common.util.ResponseUtil;
import nchu.stu.attend.system.domain.AttendItem;
import nchu.stu.attend.system.service.AttendItemService;
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
 * @date 2020/3/16
 */
@Controller
public class AttendItemController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AttendItemService attendItemService;

    @GetMapping("api/attendItem")
    @ResponseBody
    public Map<String ,Object> attendItemList(QueryRequest request, AttendItem attendItem){
        int total = attendItemService.findAllAttendItem(attendItem).size();
        PageHelper.startPage(request.getCurrent(),request.getPageSize());
        Map<String,Object> map = ResponseUtil.pageResult(this.attendItemService.findAllAttendItem(attendItem),total,true,request.getPageSize(),request.getCurrent());
        return map;
    }

    //@Log("增加考勤明细信息")
    //  @RequiresPermissions("attendItem:add")
    @PostMapping("api/attendItem")
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
    @PutMapping("api/attendItem")
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
    @DeleteMapping("api/attendItem")
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
