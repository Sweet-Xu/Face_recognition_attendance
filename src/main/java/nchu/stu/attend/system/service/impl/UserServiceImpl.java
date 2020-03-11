package nchu.stu.attend.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.impl.BaseService;
import nchu.stu.attend.system.domain.User;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import nchu.stu.attend.common.util.MD5Utils;
import nchu.stu.attend.system.dao.UserMapper;
import nchu.stu.attend.system.dao.UserRoleMapper;
import nchu.stu.attend.system.domain.UserRole;
import nchu.stu.attend.system.domain.UserWithRole;
import nchu.stu.attend.system.service.UserRoleService;
import nchu.stu.attend.system.service.UserService;
import tk.mybatis.mapper.entity.Example;

@Service("UserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<User> implements UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;


    //根据姓名查找用户
    @Override
    public User findByName(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
        List<User> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }


//    @Override
//    public List<User> findUserWithDept(User user, QueryRequest request) {
//        try {
//            return this.userMapper.findUserWithDept(user);
//        } catch (Exception e) {
//            log.error("error", e);
//            return new ArrayList<>();
//        }
//    }

    //查找所有用户信息
    @Override
    public List<User> findAllUser(User user, QueryRequest request) {
        try {
            return this.userMapper.findAllUser(user);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }


    //注册用户
    @Override
    @Transactional
    public void registUser(User user) {
        user.setUserCreateTime(new Date());
     //   user.setTheme(User.DEFAULT_THEME);
     //   user.setAvatar(User.DEFAULT_AVATAR);
        user.setUserPicture(User.DEFAULT_AVATAR);
       // user.setSsex(User.SEX_UNKNOW);
        user.setPassword(MD5Utils.encrypt(user.getUserName(), user.getPassword()));
        this.save(user);
        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(2L);
        this.userRoleMapper.insert(ur);
    }

//    @Override
//    @Transactional
//    public void updateTheme(String theme, String userName) {
//        Example example = new Example(User.class);
//        example.createCriteria().andCondition("username=", userName);
//        User user = new User();
//        user.setTheme(theme);
//        this.userMapper.updateByExampleSelective(user, example);
//    }


    //增加用户
    @Override
    @Transactional
    public void addUser(User user, Long[] roles) {
        user.setUserCreateTime(new Date());
     //   user.setTheme(User.DEFAULT_THEME);
     //  user.setAvatar(User.DEFAULT_AVATAR);
        user.setUserPicture(User.DEFAULT_AVATAR);
        user.setPassword(MD5Utils.encrypt(user.getUserName(), user.getPassword()));
        this.save(user);
        setUserRoles(user, roles);
    }


    //设置用户的角色
    private void setUserRoles(User user, Long[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            this.userRoleMapper.insert(ur);
        });
    }

    //更新用户信息
    @Override
    @Transactional
    public void updateUser(User user, Long[] roles) {
        user.setPassword(null);
        user.setUserName(null);
      //  user.setModifyTime(new Date());
        this.updateNotNull(user);
        Example example = new Example(UserRole.class);
        example.createCriteria().andCondition("user_id=", user.getUserId());
        this.userRoleMapper.deleteByExample(example);
        setUserRoles(user, roles);
    }


    //批量删除用户
    @Override
    @Transactional
    public void deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        this.batchDelete(list, "userId", User.class);
        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    //更新最后登录时间
    @Override
    @Transactional
    public void updateLoginTime(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
        User user = new User();
        user.setLastLoginTime(new Date());
        this.userMapper.updateByExampleSelective(user, example);
    }

    //修改密码
    @Override
    @Transactional
    public void updatePassword(String password) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", user.getUserName());
        String newPassword = MD5Utils.encrypt(user.getUserName().toLowerCase(), password);
        user.setPassword(newPassword);
        this.userMapper.updateByExampleSelective(user, example);
    }

    //根据用户Id查找用户的信息包括用户的角色
    @Override
    public UserWithRole findById(Long userId) {
        List<UserWithRole> list = this.userMapper.findUserWithRole(userId);
        List<Long> roleList = list.stream().map(UserWithRole::getRoleId).collect(Collectors.toList());
        if (list.isEmpty())
            return null;
        UserWithRole userWithRole = list.get(0);
        userWithRole.setRoleIds(roleList);
        return userWithRole;
    }

    //查询用户个人信息
    @Override
    public User findUserProfile(User user) {
        return this.userMapper.findUserProfile(user);
    }

    @Override
    @Transactional
    public void updateUserProfile(User user) {
        user.setUserName(null);
        user.setPassword(null);
//        if (user.getDeptId() == null)
//            user.setDeptId(0L);
        this.updateNotNull(user);
    }

}
