package forum.service;

import java.util.List;

import forum.model.Shield;
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
	public List<Integer> getSectionIds(int userId);
	
	/**
	 * 被那些板块封禁
	 * @param userId用户id
	 */
	public List<Shield> getShieldSections(int userId);
	
	/**
	 * 在sid板块下封禁用户uid
	 * @param uid 用户id
	 * @param sid 板块id
	 * @param days 封禁的天数
	 */
	public void shield(int uid, int sid, int days);
	
	/**
	 * 判断用户是否是版主
	 * @param userId
	 * @return 返回管理的板块的数量，如果返回数字大于零，也就说明是版主
	 */
	public int isManager(int userId, int sectionId);
	
}
