package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.User;
import nchu.stu.attend.system.domain.UserWithRole;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    List<User> findAllUser(User user);

    List<UserWithRole> findUserWithRole(Long userId);

    User findUserProfile(User user);

}
