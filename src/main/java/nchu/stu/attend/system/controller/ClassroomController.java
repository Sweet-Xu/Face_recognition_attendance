package nchu.stu.attend.system.controller;

import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.domain.Classroom;
import nchu.stu.attend.system.service.ClassroomService;
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
public class ClassroomController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassroomService classroomService;

    @GetMapping("classroom/list")
    @ResponseBody
    public Map<String ,Object> classroomList(QueryRequest request, Classroom classroom)
    {
        System.out.println(classroom);
        return super.selectByPageNumSize(request,()->this.classroomService.findAllClassroom(classroom,request));
    }

    //@Log("增加教室信息")
    //  @RequiresPermissions("classroom:add")
    @PostMapping("classroom/add")
    @ResponseBody
    public ResponseBo addClassroom(Classroom classroom){
        try {
            this.classroomService.addClassroom(classroom);
            return ResponseBo.ok("增加教室信息成功！");
        }catch (Exception e){
            log.error("增加教室信息失败",e);
            return ResponseBo.error("增加教室失败，请联系网站管理员");
        }
    }

    @GetMapping("classroom/getClassroom")
    @ResponseBody
    public ResponseBo getClassroomById(Integer classroomId){
        try{
            Classroom classroom = this.classroomService.findById(classroomId);
            return ResponseBo.ok(classroom);
        }catch (Exception e){
            log.error("获取教室信息失败",e);
            return ResponseBo.error("获取教室信息失败，请联系网站管理员！");
        }
    }


    //  @Log("更新教室信息")
    // @RequiresPermissions("classroom:update")
    @PutMapping("classroom/update")
    @ResponseBody
    public ResponseBo updateClassroom(Classroom classroom){
        try{
            this.classroomService.updateClassroom(classroom);
            return ResponseBo.ok("修改教室信息成功");
        }catch (Exception e){
            log.error("修改教室信息失败",e);
            return ResponseBo.error("修改教室信息失败，请联系网站管理员！");
        }
    }

    // @Log("删除教室信息")
    // @RequiresPermissions("classroom:delete")
    @DeleteMapping("classroom/delete")
    @ResponseBody
    public ResponseBo deleteClassroom(String classroomId){
        try{
            this.classroomService.deleteClassroom(classroomId);
            return ResponseBo.ok("删除教室信息成功");
        }catch(Exception e){
            log.error("删除教室信息失败");
            return ResponseBo.error("删除教室信息失败，请联系网站管理员！");
        }
    }

    @GetMapping("classroom/excel")
    @ResponseBody
    public ResponseBo classroomExcel(Classroom classroom){
        try{
            List<Classroom> list = this.classroomService.findAllClassroom(classroom,null);
            return FileUtil.createExcelByPOIKit("教室表",list,Classroom.class);
        }catch (Exception e){
            log.error("导出教室信息Excel失败");
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }


    @GetMapping("classroom/csv")
    @ResponseBody
    public ResponseBo classroomCsv(Classroom classroom) {
        try {
            List<Classroom> list = this.classroomService.findAllClassroom(classroom,null);
            return FileUtil.createCsv("教室表", list, Classroom.class);
        } catch (Exception e) {
            log.error("获取教室信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }


}
