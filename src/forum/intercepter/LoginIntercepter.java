package forum.intercepter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import forum.model.User;
import forum.service.UserService;
import forum.util.CookieUtil;
import forum.util.DataUtil;

/**
 * 判断cookie中有没有记录
 * 如果有，查询数据库登录
 * @author skywalker
 *
 */
public class LoginIntercepter extends HandlerInterceptorAdapter {
	
	@Resource(name = "userService")
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		if(object == null) {
			//查询cookie
			String cookie = CookieUtil.getCookie("user", request);
			if(DataUtil.isValid(cookie)) {
				User user = DataUtil.generateUserFromCookie(cookie);
				User result = userService.login(user.getUsername(), user.getPassword());
				if(result != null) {
					//如果用户是版主，查出板块号
					if(result.getIsManager()) {
						result.setSections(userService.getSectionIds(result.getId()));
					}
					session.setAttribute("user", result);
				}
			}
		}
		return true;
	}
	
}
