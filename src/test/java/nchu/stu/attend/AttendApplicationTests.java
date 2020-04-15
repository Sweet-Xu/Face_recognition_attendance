package nchu.stu.attend;

import nchu.stu.attend.common.scheduled.CronScheduler;
import nchu.stu.attend.common.util.MD5Utils;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.CourseService;
import nchu.stu.attend.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AttendApplicationTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private UserService userService;


    @Test
    void contextLoads() {
    }

    @Test
    void testDate() {
        String username = "16201302";
        String password = "16201302";
        String encrypt = MD5Utils.encrypt(username, password);
        System.out.println(encrypt);
    }

}
