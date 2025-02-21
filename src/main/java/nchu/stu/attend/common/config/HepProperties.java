package nchu.stu.attend.common.config;

import nchu.stu.attend.common.scheduled.CronScheduler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author XuTian
 * @description
 * @date 2020/3/3
 */
//总的配置类
@Configuration
@ConfigurationProperties(prefix = "febs")
public class HepProperties {


    //shiro配置
 //   private ShiroProperties shiro = new ShiroProperties();


    //验证码配置
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();

    //时间格式
    private String timeFormat = "yyyy-MM-dd HH:mm:ss";

    //是否开启日志
    private boolean openAopLog = true;

//    public ShiroProperties getShiro() {
//        return shiro;
//    }
//
//    public void setShiro(ShiroProperties shiro) {
//        this.shiro = shiro;
//    }

    public ValidateCodeProperties getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(ValidateCodeProperties validateCode) {
        this.validateCode = validateCode;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isOpenAopLog() {
        return openAopLog;
    }

    public void setOpenAopLog(boolean openAopLog) {
        this.openAopLog = openAopLog;
    }
}

