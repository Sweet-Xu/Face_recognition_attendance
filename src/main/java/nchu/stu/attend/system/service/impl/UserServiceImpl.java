package nchu.stu.attend.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import nchu.stu.attend.system.service.UserService;
import tk.mybatis.mapper.entity.Example;

@Service("UserService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<User> implements UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    //根据姓名查找用户
    @Override
    public User findByName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("lower(username)=", username.toLowerCase());
        List<User> list = this.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

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

    @Override
    public List<User> findAllUser(User user){
        return this.userMapper.select(user);
    }

    //一个新增用户
    @Override
    @Transactional
    public long addUser(User user){
        user.setUserCreateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        userMapper.insert(user);
        return user.getUserId();
    }


    @Override
    public User findById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        this.updateNotNull(user);
    }

    //批量删除用户
    @Override
    @Transactional
    public void deleteUsers(Long id) {
//        List<String> list = new ArrayList<>();
//        list.add(id+"");
//        this.batchDelete(list,"userId", User.class);
        this.delete(id);
    }

    //更新最后登录时间
    @Override
    @Transactional
    public void updateLoginTime(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("lower(username)=", username.toLowerCase());
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
        example.createCriteria().andCondition("username=", user.getUsername());
        String newPassword = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        user.setPassword(newPassword);
        this.userMapper.updateByExampleSelective(user, example);
    }


    //查询用户个人信息
    @Override
    public User findUserProfile(User user) {
        return this.userMapper.findUserProfile(user);
    }

    @Override
    public boolean checkUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        User newUser= userMapper.findUserByName(user);
        String encrypt = MD5Utils.encrypt(user.getUsername(), password);
        return newUser.getPassword().equals(encrypt);
    }

    //    @Override
//    @Transactional
//    public void updateUserProfile(User user) {
//        user.setUsername(null);
//        user.setPassword(null);
////        if (user.getDeptId() == null)
////            user.setDeptId(0L);
//        this.updateNotNull(user);
//    }
}

//    //注册用户
//    @Override
//    @Transactional
//    public void registUser(User user) {
//        user.setUserCreateTime(new Date());
//     //   user.setTheme(User.DEFAULT_THEME);
//     //   user.setAvatar(User.DEFAULT_AVATAR);
//       // user.setUserPicture(User.DEFAULT_AVATAR);
//       // user.setSsex(User.SEX_UNKNOW);
//        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
//        this.save(user);
//        UserRole ur = new UserRole();
//        ur.setUserId(user.getUserId());
//        ur.setRoleId(2L);
//        this.userRoleMapper.insert(ur);
//    }


//
//    //设置用户的角色
//    private void setUserRoles(User user, Long[] roles) {
//        Arrays.stream(roles).forEach(roleId -> {
//            UserRole ur = new UserRole();
//            ur.setUserId(user.getUserId());
//            ur.setRoleId(roleId);
//            this.userRoleMapper.insert(ur);
//        });
//    }





