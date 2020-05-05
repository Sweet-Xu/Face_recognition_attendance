package nchu.stu.attend;

import nchu.stu.attend.common.controller.LoginController;
import nchu.stu.attend.common.dto.UserLoginInputDto;
import nchu.stu.attend.common.scheduled.CronScheduler;
import nchu.stu.attend.common.util.MD5Utils;
import nchu.stu.attend.system.controller.AttendController;
import nchu.stu.attend.system.controller.AttendItemController;
import nchu.stu.attend.system.domain.Attend;
import nchu.stu.attend.system.domain.AttendItem;
import nchu.stu.attend.system.domain.Course;
import nchu.stu.attend.system.domain.dto.AttendResultExport;
import nchu.stu.attend.system.domain.dto.CheckRecordInputDto;
import nchu.stu.attend.system.service.AttendItemService;
import nchu.stu.attend.system.service.AttendService;
import nchu.stu.attend.system.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class AttendApplicationTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AttendService attendService;

    @Autowired
    private AttendItemController attendItemController;

    @Autowired
    private AttendItemService attendItemService;

    @Test
    void contextLoads() {
    }

    @Test
    void testDate() {
//        try {
//            CheckRecordInputDto checkRecordInputDto = new CheckRecordInputDto();
//            checkRecordInputDto.setStudentId("16201417");
//            checkRecordInputDto.setCameraId(3);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            checkRecordInputDto.setCheckTime(simpleDateFormat.parse("2020-05-03 15:16:00"));
//            attendItemController.receiveAttendItem(checkRecordInputDto);
//        }catch (ParseException e){
//            System.out.println("错误");
//        }

     this.attendService.batchCreateAllStudentAttendItem();

    }




}
