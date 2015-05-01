package test.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import forum.dao.UserDao;
import forum.model.Shield;
import forum.model.ShieldSearchResult;
import forum.model.User;
import forum.service.UserService;

/**
 * 测试用户服务
 * @author skywalker
 *
 */
public class UserTest extends TestBase {
	
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "userDao")
	private UserDao userDao;

	@Test
	public void login() {
		User user = userService.login("skywalker", "12345");
		System.out.println(user.getAvatar());
	}
	
	@Test
	public void getSectionIds() {
		List<Integer> ids = userDao.getSectionIds(2);
		System.out.println(ids.size());
	}
	
	@Test
	public void shield() {
		List<Shield> shields = userDao.getShieldSections(2);
		System.out.println(shields.size());
	}
	
	/**
	 * 测试封禁查询
	 */
	@Test
	public void shieldSearch() {
		List<Integer> ids = new ArrayList<Integer>(Arrays.asList(24, 12, 11));
		List<ShieldSearchResult> results = userDao.shieldSearchResult("skywalker", ids);
		System.out.println(results.size());
	}
	
}
