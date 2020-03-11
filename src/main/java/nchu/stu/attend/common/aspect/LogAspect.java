package nchu.stu.attend.common.aspect;

import nchu.stu.attend.common.config.HepProperties;
import nchu.stu.attend.common.util.HttpContextUtils;
import nchu.stu.attend.common.util.IPUtils;
import nchu.stu.attend.system.domain.SysLog;
import nchu.stu.attend.system.domain.User;
import nchu.stu.attend.system.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author XuTian
 * @description
 * @date 2020/3/3
 */
//日志切面
@Aspect
@Component
public class LogAspect {

    @Autowired
    private HepProperties hepProperties;

    @Autowired
    private LogService logService;


    @Pointcut("@annotation(nchu.stu.attend.common.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtils.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (hepProperties.isOpenAopLog()) {
            // 保存日志
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            SysLog log = new SysLog();
            log.setUsername(user.getUserName());
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }
        return result;
    }
}

