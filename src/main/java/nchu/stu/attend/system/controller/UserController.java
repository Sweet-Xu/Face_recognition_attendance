package nchu.stu.attend.system.controller;

import com.github.pagehelper.PageHelper;
import nchu.stu.attend.common.annotation.Log;
import nchu.stu.attend.common.controller.BaseController;
import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.domain.ResponseBo;
import nchu.stu.attend.common.dto.CurrentUserDto;
import nchu.stu.attend.common.util.MD5Utils;
import nchu.stu.attend.common.util.ResponseUtil;
import nchu.stu.attend.system.domain.User;
import nchu.stu.attend.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author XuTian
 * @description
 * @date 2020/2/24
 */
@Controller
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    //获取到用户的所有信息包括角色名
    @GetMapping("/api/user/currentUser")
    @ResponseBody
    public CurrentUserDto getCurrentUser(HttpSession session) {
        CurrentUserDto dto = new CurrentUserDto();
        User user = (User) session.getAttribute("currentUser");
        dto.setUserid(user.getUserId());
        dto.setName(user.getUsername());
        if(user.getRole()==0) {
            dto.setRole("admin");
        }else if(user.getRole()==1){
            dto.setRole("user");
        }else if(user.getRole()==2){
            dto.setRole("guest");
        }else{
            dto.setRole("");
        }
        return dto;
    }

//    @GetMapping("user")
//  //  @RequiresPermissions("user:list")
//    public String index(Model model) {
//        User user = super.getCurrentUser();
//        model.addAttribute("user", user);
//        return "system/user/user";
//    }

//    @GetMapping("user/checkUserName")
//    @ResponseBody
//    public boolean checkUserName(String username, String oldusername) {
//        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
//            return true;
//        }
//        User result = this.userService.findByName(username);
//        return result == null;
//    }


    //获取到用户的所有信息包括角色名
    @GetMapping("user/getUser")
    @ResponseBody
    public ResponseBo getUser(Long userId) {
        try {
            User user = this.userService.findById(userId);
//            System.out.println(user);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    @Log("获取用户信息")
    @GetMapping("/api/user")
   // @RequiresPermissions("user:list")
    @ResponseBody
    public Map<String, Object> userList(QueryRequest request,User user ) {
      int total = userService.findAllUser(user).size();
        PageHelper.startPage(request.getCurrent(),request.getPageSize());
        Map<String,Object> map = ResponseUtil.pageResult(userService.findAllUser(user),total,true,request.getPageSize(),request.getCurrent());
        return map;
    }

//    @GetMapping("user/excel")
//    @ResponseBody
//    public ResponseBo userExcel(User user) {
//        try {
//            List<User> list = this.userService.findAllUser(user, null);
//            return FileUtil.createExcelByPOIKit("用户表", list, User.class);
//        } catch (Exception e) {
//            log.error("导出用户信息Excel失败", e);
//            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
//        }
//    }
//
//    @GetMapping("user/csv")
//    @ResponseBody
//    public ResponseBo userCsv(User user) {
//        try {
//            List<User> list = this.userService.findAllUser(user, null);
//            return FileUtil.createCsv("用户表", list, User.class);
//        } catch (Exception e) {
//            log.error("导出用户信息Csv失败", e);
//            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
//        }
//    }

//    @PostMapping("user/regist")
//    @ResponseBody
//    public ResponseBo regist(HttpServletRequest request, User user, String code) {
//        try {
//            User result = this.userService.findByName(user.getUsername());
//            if (result != null) {
//                return ResponseBo.warn("该用户名已被使用！");
//            }
//            String realCode = (String) request.getSession().getAttribute("code");
//            System.out.println("session里的验证码是：" + realCode);
//            System.out.println("输入的验证码是：" + code);
//            if (!realCode.equals(code)) {
//                return ResponseBo.warn("验证码错误！");
//            }
//            this.userService.registUser(user);
//            return ResponseBo.ok();
//        } catch (Exception e) {
//            log.error("注册失败", e);
//            return ResponseBo.error("注册失败，请联系网站管理员！");
//        }
//    }

 //   @Log("更换主题")
//    @RequestMapping("user/theme")
//    @ResponseBody
//    public ResponseBo updateTheme(User user) {
//        try {
//            this.userService.updateTheme(user.getTheme(), user.getUsername());
//            return ResponseBo.ok();
//        } catch (Exception e) {
//            log.error("修改主题失败", e);
//            return ResponseBo.error();
//        }
//    }


//    @Log("新增用户")
//   // @RequiresPermissions("user:add")
//    @PostMapping("user/add")
//    @ResponseBody
//    public ResponseBo addUser(User user, Long[] roles) {
//        try {
//            if (ON.equalsIgnoreCase(user.getStatus()))
//                user.setStatus(User.STATUS_VALID);
//            else
//                user.setStatus(User.STATUS_LOCK);
//            this.userService.addUser(user, roles);
//            return ResponseBo.ok("新增用户成功！");
//        } catch (Exception e) {
//            log.error("新增用户失败", e);
//            return ResponseBo.error("新增用户失败，请联系网站管理员！");
//        }
//    }

    @Log("增加用户信息")
    @PostMapping("/api/user")
    @ResponseBody
    public ResponseBo addUser(@RequestBody User user){
        try {
            this.userService.addUser(user);
            return ResponseBo.ok("增加用户信息成功！");
        }catch (Exception e){
            log.error("增加用户信息失败",e);
            return ResponseBo.error("增加用户信息失败，请联系网站管理员");
        }
    }

    @Log("修改用户")
    //   @RequiresPermissions("user:update")
    @PutMapping("/api/user")
    @ResponseBody
    public ResponseBo updateUser(@RequestBody User user) {
        try {
//            if (ON.equalsIgnoreCase(user.getUserStatus()))
//                user.setUserStatus(User.STATUS_VALID);
//            else
//                user.setUserStatus(User.STATUS_LOCK);
            this.userService.updateUser(user);
            return ResponseBo.ok("修改用户成功！");
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return ResponseBo.error("修改用户失败，请联系网站管理员！");
        }
    }

    @Log("删除用户")
    // @RequiresPermissions("user:delete")
    @DeleteMapping("/api/user")
    @ResponseBody
    public ResponseBo deleteUsers(Long userId) {
        try {
            this.userService.deleteUsers(userId);
            return ResponseBo.ok("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return ResponseBo.error("删除用户失败，请联系网站管理员！");
        }
    }


//    @Log("修改用户")
// //   @RequiresPermissions("user:update")
//    @PutMapping("user/update")
//    @ResponseBody
//    public ResponseBo updateUser(User user, Long[] rolesSelect) {
//        try {
//            if (ON.equalsIgnoreCase(user.getStatus()))
//                user.setStatus(User.STATUS_VALID);
//            else
//                user.setStatus(User.STATUS_LOCK);
//            this.userService.updateUser(user, rolesSelect);
//            return ResponseBo.ok("修改用户成功！");
//        } catch (Exception e) {
//            log.error("修改用户失败", e);
//            return ResponseBo.error("修改用户失败，请联系网站管理员！");
//        }
//    }

//    @Log("删除用户")
//   // @RequiresPermissions("user:delete")
//    @DeleteMapping("user/delete")
//    @ResponseBody
//    public ResponseBo deleteUsers(String ids) {
//        try {
//            this.userService.deleteUsers(ids);
//            return ResponseBo.ok("删除用户成功！");
//        } catch (Exception e) {
//            log.error("删除用户失败", e);
//            return ResponseBo.error("删除用户失败，请联系网站管理员！");
//        }
//    }

    @GetMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User user = getCurrentUser();
        String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        return user.getPassword().equals(encrypt);
    }

    @PutMapping("user/updatePassword")
    @ResponseBody
    public ResponseBo updatePassword(String newPassword) {
        try {
            this.userService.updatePassword(newPassword);
            return ResponseBo.ok("更改密码成功！");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return ResponseBo.error("更改密码失败，请联系网站管理员！");
        }
    }

//    @GetMapping("user/profile")
//    public String profileIndex(Model model) {
//        User user = super.getCurrentUser();
//        user = this.userService.findUserProfile(user);
//        String ssex = user.getGender();
//        if (User.SEX_MALE.equals(ssex)) {
//            user.setGender("性别：男");
//        } else if (User.SEX_FEMALE.equals(ssex)) {
//            user.setGender("性别：女");
//        } else {
//            user.setGender("性别：保密");
//        }
//        model.addAttribute("user", user);
//        return "system/user/profile";
//    }

//    //获取到用户所有信息不包括角色名
//    @GetMapping("user/getUserProfile")
//    @ResponseBody
//    public ResponseBo getUserProfile(Long userId) {
//        try {
//            User user = new User();
//            user.setUserId(userId);
//            return ResponseBo.ok(this.userService.findUserProfile(user));
//        } catch (Exception e) {
//            log.error("获取用户信息失败", e);
//            return ResponseBo.error("获取用户信息失败，请联系网站管理员！");
//        }
//    }


//    @PutMapping("user/changeAvatar")
//    @ResponseBody
//    public ResponseBo changeAvatar(String imgName) {
//        try {
//            String[] img = imgName.split("/");
//            String realImgName = img[img.length - 1];
//            User user = getCurrentUser();
//            user.setUserPicture(realImgName);
//            this.userService.updateNotNull(user);
//            return ResponseBo.ok("更新头像成功！");
//        } catch (Exception e) {
//            log.error("更换头像失败", e);
//            return ResponseBo.error("更新头像失败，请联系网站管理员！");
//        }
//    }
}
