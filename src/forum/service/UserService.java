package forum.service;

import java.util.List;

import forum.model.User;
import forum.service.base.BaseService;

public interface UserService extends BaseService<User> {

	public boolean isUserExists(String username);
	
	/**
	 * 登录
	 */
	public User login(String username, String password);
	
	/**
	 * 检查用户名是否可用
	 */
	public User checkAuthority(String username);
	
	/**
	 * 查出关联的板块id
	 * 如果用户为版主时需要用到
	 */
	public List<Integer> getSectionIds(int userid);
	
}
