package forum.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import forum.dao.base.BaseDao;
import forum.model.User;

@Repository("userDao")
public interface UserDao extends BaseDao<User> {

	/**
	 * 根据用户的属性查找
	 */
	public User find(User user);
	
	/**
	 * 查出关联的板块id
	 * 如果用户为版主时需要用到
	 */
	public List<Integer> getSectionIds(int userid);
	
}
