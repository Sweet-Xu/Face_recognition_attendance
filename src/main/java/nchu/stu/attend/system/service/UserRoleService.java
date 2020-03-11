package nchu.stu.attend.system.service;

import nchu.stu.attend.common.service.IService;
import nchu.stu.attend.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
