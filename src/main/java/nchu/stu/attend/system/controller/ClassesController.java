package nchu.stu.attend.system.controller;

import com.github.pagehelper.PageHelper;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.common.util.ResponseUtil;
import nchu.stu.attend.system.domain.Classes;
import nchu.stu.attend.system.service.ClassesService;
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
public class ClassesController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClassesService classesService;

    @GetMapping("api/classes")
    @ResponseBody
    public Map<String ,Object> classesList(QueryRequest request, Classes classes)
    {
        int total = classesService.findAllClasses(classes).size();
        PageHelper.startPage(request.getCurrent(),request.getPageSize());
        Map<String,Object> map = ResponseUtil.pageResult(this.classesService.findAllClasses(classes),total,true,request.getPageSize(),request.getCurrent());
        return map;
    }
    
    
    //@Log("增加班级信息")
    //  @RequiresPermissions("classes:add")
    @PostMapping("api/classes")
    @ResponseBody
    public ResponseBo addClasses(@RequestBody Classes classes){
        try {
            this.classesService.addClasses(classes);
            return ResponseBo.ok("增加班级信息成功！");
        }catch (Exception e){
            log.error("增加班级信息失败",e);
            return ResponseBo.error("增加班级失败，请联系网站管理员");
        }
    }

    @GetMapping("classes/getClasses")
    @ResponseBody
    public ResponseBo getClassesById(Integer classId){
        try{
            Classes classes = this.classesService.findById(classId);
            return ResponseBo.ok(classes);
        }catch (Exception e){
            log.error("获取班级信息失败",e);
            return ResponseBo.error("获取班级信息失败，请联系网站管理员！");
        }
    }


    //  @Log("更新班级信息")
    // @RequiresPermissions("classes:update")
    @PutMapping("api/classes")
    @ResponseBody
    public ResponseBo updateClasses(@RequestBody Classes classes){
        try{
            this.classesService.updateClasses(classes);
            return ResponseBo.ok("修改班级信息成功");
        }catch (Exception e){
            log.error("修改班级信息失败",e);
            return ResponseBo.error("修改班级信息失败，请联系网站管理员！");
        }
    }

    // @Log("删除班级信息")
    // @RequiresPermissions("classes:delete")
    @DeleteMapping("api/classes")
    @ResponseBody
    public ResponseBo deleteClasses(String classId){
        try{
            this.classesService.deleteClasses(classId);
            return ResponseBo.ok("删除班级信息成功");
        }catch(Exception e){
            log.error("删除班级信息失败");
            return ResponseBo.error("删除班级信息失败，请联系网站管理员！");
        }
    }

    @GetMapping("classes/excel")
    @ResponseBody
    public ResponseBo classesExcel(Classes classes){
        try{
            List<Classes> list = this.classesService.findAllClasses(classes,null);
            return FileUtil.createExcelByPOIKit("班级表",list,Classes.class);
        }catch (Exception e){
            log.error("导出班级信息Excel失败");
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }


    @GetMapping("classes/csv")
    @ResponseBody
    public ResponseBo classesCsv(Classes classes) {
        try {
            List<Classes> list = this.classesService.findAllClasses(classes,null);
            return FileUtil.createCsv("班级表", list, Classes.class);
        } catch (Exception e) {
            log.error("获取班级信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

}
