package nchu.stu.attend.system.dao;

import nchu.stu.attend.common.config.MyMapper;
import nchu.stu.attend.system.domain.User;

import java.util.List;

public interface UserMapper extends MyMapper<User> {

    List<User> findAllUser(User user);

    User findUserProfile(User user);

    User findUserByName(User user);
}
