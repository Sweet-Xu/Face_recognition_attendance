package nchu.stu.attend.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.google.gson.JsonObject;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.common.util.ResponseUtil;
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

    @GetMapping("/api/course")
    @ResponseBody
    public Map<String ,Object> courseList(QueryRequest request, Course course)
    {
        int total = courseService.findAllCourse(course).size();
        PageHelper.startPage(request.getCurrent(),request.getPageSize());
        Map<String,Object> map = ResponseUtil.pageResult(this.courseService.findAllCourse(course),total,true,request.getPageSize(),request.getCurrent());
        return map;
    }

    @GetMapping("/api/courseTable")
    @ResponseBody
    public String courseTable()
    {
        return "{ 1: [ {  startTime:1551920827000,   endTime:1551924427000,  stuNameList: ['123'],  teaName: '312' } ] }";
    }

    @PostMapping("/api/course")
    @ResponseBody
    public ResponseBo addCourse(@RequestBody Course course){
        try{
            System.out.println(JSONObject.toJSONString(course));
            courseService.addCourse(course);
            return ResponseBo.ok("增加课程信息成功！");
        }catch(Exception e){
            log.error("增加课程信息失败",e);
            return ResponseBo.error("增加课程信息失败，请联系管理员");
        }
    }


    @DeleteMapping("/api/course")
    @ResponseBody
    public ResponseBo deleteCourse(String courseId){
        try{
            this.courseService.deleteCourse(courseId);
            return ResponseBo.ok("删除课程信息成功");
        }catch(Exception e){
            log.error("删除课程信息失败",e);
            return ResponseBo.error("删除课程信息失败，请联系网站管理员！");
        }
    }

    @PutMapping("/api/course")
    @ResponseBody
    public ResponseBo updateStudent(@RequestBody Course course){
        try{
            System.out.println(course);
            System.out.println(JSONObject.toJSONString(course));
            this.courseService.updateCourse(course);
            return ResponseBo.ok("修改课程信息成功");
        }catch (Exception e){
            log.error("修改课程信息失败",e);
            return ResponseBo.error("修改课程信息失败，请联系网站管理员！");
        }
    }

    @GetMapping("course/excel")
    @ResponseBody
    public ResponseBo studentExcel(Course course){
        try{
            List<Course> list = this.courseService.findAllCourse(course,null);
            return FileUtil.createExcelByPOIKit("课程表",list,Course.class);
        }catch (Exception e){
            log.error("导出课程信息Excel失败",e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }



}
