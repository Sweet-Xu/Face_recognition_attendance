package nchu.stu.attend.system.controller;

import com.github.pagehelper.PageHelper;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.util.FileUtil;
import nchu.stu.attend.common.util.ResponseUtil;
import nchu.stu.attend.system.domain.SysLog;
import nchu.stu.attend.system.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class LogController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

//    @RequestMapping("log")
//  //  @RequiresPermissions("log:list")
//    public String index() {
//        return "system/log/log";
//    }

    @GetMapping("api/log")
    @ResponseBody
    public Map<String,Object> logList(QueryRequest request, SysLog log) {
        int total = logService.findAllLogs(log).size();
        PageHelper.startPage(request.getCurrent(),request.getPageSize());
        Map<String,Object> map = ResponseUtil.pageResult(this.logService.findAllLogs(log),total,true,request.getPageSize(),request.getCurrent());
        return map;
    }

    @GetMapping("log/excel")
    @ResponseBody
    public ResponseBo logExcel(SysLog log) {
        try {
            List<SysLog> list = this.logService.findAllLogs(log);
            return FileUtil.createExcelByPOIKit("系统日志表", list, SysLog.class);
        } catch (Exception e) {
            logger.error("导出系统日志Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @GetMapping("log/csv")
    @ResponseBody
    public ResponseBo logCsv(SysLog log) {
        try {
            List<SysLog> list = this.logService.findAllLogs(log);
            return FileUtil.createCsv("系统日志表", list, SysLog.class);
        } catch (Exception e) {
            logger.error("导出系统日志Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

   // @RequiresPermissions("log:delete")
    @DeleteMapping("api/log")
    @ResponseBody
    public ResponseBo deleteLogss(String logId) {
        try {
            this.logService.deleteLogs(logId);
            return ResponseBo.ok("删除日志成功！");
        } catch (Exception e) {
            logger.error("删除日志失败", e);
            return ResponseBo.error("删除日志失败，请联系网站管理员！");
        }
    }
}
