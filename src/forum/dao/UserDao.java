package forum.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import forum.dao.base.BaseDao;
import forum.model.Shield;
import forum.model.ShieldSearchResult;
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
	public List<Integer> getSectionIds(int userId);
	
	/**
	 * 查出被哪些板块封禁
	 */
	public List<Shield> getShieldSections(int userId);
	
	/**
	 * 当版主的顶级板块
	 */
	public List<Integer> getTopSectionIds(int userId);
	
	/**
	 * 用户封禁情况查询
	 * @param sectionIds 当前用户是版主的那些板块
	 */
	public List<ShieldSearchResult> shieldSearchResult(@Param("username") String username, 
			@Param("sectionIds") List<Integer> sectionIds);
	
	/**
	 * 关小黑屋
	 * @param userId 被封禁的id
	 * @param sectionId 被哪个板块封禁
	 * @param endTime 到期时间
	 */
	public void shield(@Param("userId") int userId, @Param("sectionId") int sectionId, @Param("endTime") Date endTime);
	
}
