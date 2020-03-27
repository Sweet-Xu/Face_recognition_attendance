package nchu.stu.attend.system.controller;

import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author XuTian
 * @description
 * @date 2020/2/22
 */
@Controller
public class StudentController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentService studentService;

   // @Log("获取学生信息")
//    @GetMapping("student")
//    public String studentIndex(){return "system/student/student";}

    @GetMapping("student/list")
    @ResponseBody
    public Map<String ,Object> studentList(QueryRequest request, Student student)
    {
        System.out.println(student);
        return super.selectByPageNumSize(request,()->this.studentService.findAllStudent(student,request));
    }



   //@Log("增加学生信息")
  //  @RequiresPermissions("student:add")
    @PostMapping("student/add")
    @ResponseBody
    public ResponseBo addStudent(Student student){
        try {
            this.studentService.addStudent(student);
            return ResponseBo.ok("增加学生信息成功！");
        }catch (Exception e){
            log.error("增加学生信息失败",e);
            return ResponseBo.error("增加学生失败，请联系网站管理员");
        }
    }

    @GetMapping("student/getStudent")
    @ResponseBody
    public ResponseBo getStudentById(Long stuId){
        try{
            Student student = this.studentService.findById(stuId);
            return ResponseBo.ok(student);
        }catch (Exception e){
            log.error("获取学生信息失败",e);
            return ResponseBo.error("获取学生信息失败，请联系网站管理员！");
        }
    }


  //  @Log("更新学生信息")
   // @RequiresPermissions("student:update")
    @PutMapping("student/update")
    @ResponseBody
    public ResponseBo updateStudent(Student student){
        try{
            this.studentService.updateStudent(student);
            return ResponseBo.ok("修改学生信息成功");
        }catch (Exception e){
            log.error("修改学生信息失败",e);
            return ResponseBo.error("修改学生信息失败，请联系网站管理员！");
        }
    }

   // @Log("删除学生信息")
   // @RequiresPermissions("student:delete")
    @DeleteMapping("student/delete")
    @ResponseBody
    public ResponseBo deleteStudent(String ids){
        try{
            this.studentService.deleteStudent(ids);
            return ResponseBo.ok("删除学生信息成功");
        }catch(Exception e){
            log.error("删除学生信息失败");
            return ResponseBo.error("删除学生信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("student/excel")
    @ResponseBody
    public ResponseBo studentExcel(Student student){
        try{
            List<Student> list = this.studentService.findAllStudent(student,null);
            return FileUtil.createExcelByPOIKit("学生表",list,Student.class);
        }catch (Exception e){
            log.error("导出学生信息Excel失败");
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }


    @RequestMapping("student/csv")
    @ResponseBody
    public ResponseBo studentCsv(Student student) {
        try {
            List<Student> list = this.studentService.findAllStudent(student,null);
            return FileUtil.createCsv("学生表", list, Student.class);
        } catch (Exception e) {
            log.error("获取学生信息Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

}
