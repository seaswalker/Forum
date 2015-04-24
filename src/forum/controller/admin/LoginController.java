package forum.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.User;
import forum.service.UserService;
import forum.util.DataUtil;
import forum.util.StringUtil;

/**
 * 后台登录
 * @author skywalker
 *
 */
@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController {
	
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 转向登录
	 */
	@RequestMapping("/login")
	public String toLogin() {
		return "admin/login";
	}
	
	/**
	 * 登录
	 */
	@RequestMapping("/check")
	public String login(String username, String password, Model model, HttpSession session) {
		if(!DataUtil.isValid(username)) {
			model.addAttribute("error", "请输入用户名");
			return "admin/login";
		}else if(!DataUtil.isValid(password)) {
			model.addAttribute("username", username);
			model.addAttribute("error", "请输入密码");
			return "admin/login";
		}else {
			//检查权限
			User user = userService.checkAuthority(username);
			if(user == null) {
				model.addAttribute("username", username);
				model.addAttribute("error", "用户名不存在或没有权限");
				return "admin/login";
			}else if(!StringUtil.md5(password).equals(user.getPassword())) {
				model.addAttribute("username", username);
				model.addAttribute("error", "密码错误");
				return "admin/login";
			}else {
				//存入session
				session.setAttribute("admin", user);
				return "redirect:/admin/index.html";
			}
		}
	}
	
}
