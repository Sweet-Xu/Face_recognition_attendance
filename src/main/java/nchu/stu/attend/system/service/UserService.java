package nchu.stu.attend.system.service;

import nchu.stu.attend.common.domain.QueryRequest;
import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.User;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

@CacheConfig(cacheNames = "UserService")
public interface UserService extends IService<User> {
//    @Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
//    List<User> findUserWithDept(User user, QueryRequest request);

 //   @Cacheable(key = "#p0.toString() + (#p1 != null ? #p1.toString() : '')")
    List<User> findAllUser(User user, QueryRequest request);

    List<User> findAllUser(User user);

  //  @CacheEvict(allEntries = true)
    long addUser(User user);

  //  @CacheEvict(key = "#p0", allEntries = true)
    void updateUser(User user);

  //  @CacheEvict(key = "#p0", allEntries = true)
    void deleteUsers(Long userId);

    User findById(Long id);

    void updatePassword(String password);

    User findUserProfile(User user);

//    void updateUserProfile(User user);

    User findByName(String username);

    public void updateLoginTime(String username);

    public boolean checkUser(String username,String password);




}
