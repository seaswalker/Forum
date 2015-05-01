package forum.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import forum.model.User;

/**
 * 后台拦截器
 * @author skywalker
 *
 */
public class AdminIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Object object = request.getSession().getAttribute("user");
		if(object != null) {
			User user = (User) object;
			if(user.getIsAdmin() || user.getIsManager()) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/index.html");
		return false;
	}
	
}
