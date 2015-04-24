package test.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import forum.dao.UserDao;
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
	
}
