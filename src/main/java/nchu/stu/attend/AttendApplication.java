package nchu.stu.attend;

import nchu.stu.attend.common.scheduled.CronScheduler;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("nchu.stu.attend.*.dao")
public class AttendApplication {

    public static void main(String[] args) {

        SpringApplication.run(AttendApplication.class, args);

       // CronScheduler.schedulerTask();
    }

}
