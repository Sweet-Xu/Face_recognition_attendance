package nchu.stu.attend.system.controller;

import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.Student;
import nchu.stu.attend.system.service.CourseService;
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
 * @date 2020/3/11
 */
@Controller
public class CourseController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseService courseService;

    @GetMapping("course/list")
    @ResponseBody
    public Map<String ,Object> courseList(QueryRequest request, Course course)
    {
        System.out.println(course);
        return super.selectByPageNumSize(request,()->this.courseService.findAllCourse(course,request));
    }

    @PostMapping("course/add")
    @ResponseBody
    public ResponseBo addCourse(Course course){
        try{
            courseService.addCourse(course);
            return ResponseBo.ok("增加课程信息成功！");
        }catch(Exception e){
            log.error("增加课程信息失败",e);
            return ResponseBo.error("增加课程信息失败，请联系管理员");
        }
    }


    @DeleteMapping("course/delete")
    @ResponseBody
    public ResponseBo deleteCourse(String ids){
        try{
            this.courseService.deleteCourse(ids);
            return ResponseBo.ok("删除课程信息成功");
        }catch(Exception e){
            log.error("删除课程信息失败");
            return ResponseBo.error("删除课程信息失败，请联系网站管理员！");
        }
    }

    @PutMapping("course/update")
    @ResponseBody
    public ResponseBo updateStudent(Course course){
        try{
            this.courseService.updateCourse(course);
            return ResponseBo.ok("修改课程信息成功");
        }catch (Exception e){
            log.error("修改课程信息失败",e);
            return ResponseBo.error("修改课程信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("course/excel")
    @ResponseBody
    public ResponseBo studentExcel(Course course){
        try{
            List<Course> list = this.courseService.findAllCourse(course,null);
            return FileUtil.createExcelByPOIKit("课程表",list,Course.class);
        }catch (Exception e){
            log.error("导出课程信息Excel失败");
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }



}
