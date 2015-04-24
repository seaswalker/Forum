package forum.controller.user;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.util.CookieUtil;

/**
 * �ǳ�
 * @author skywalker
 *
 */
@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		CookieUtil.removeCookie("user", response);
		session.removeAttribute("user");
		return "redirect:index.html";
	}
	
}
