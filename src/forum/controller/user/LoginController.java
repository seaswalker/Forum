package forum.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import forum.model.User;
import forum.service.UserService;
import forum.util.CookieUtil;
import forum.util.DataUtil;
import forum.util.json.JSONObject;

/**
 * 登录
 * @author skywalker
 *
 */
@Controller
public class LoginController {
	
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public void login(String username, String password, int autologin, HttpSession session, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(!DataUtil.isValid(username)) {
			json.addElement("result", "0").addElement("message", "请输入用户名");
		}else if(!DataUtil.isValid(password)) {
			json.addElement("result", "0").addElement("message", "请输入密码");
		}else {
			//检查登录
			User user = userService.login(username, password);
			if(user == null) {
				json.addElement("result", "0").addElement("message", "用户名或密码错误");
			}else {
				json.addElement("result", "1");
				//初始化版主板块id
				user.initSections(userService);
				//被封禁的板块
				user.initShieldSections(userService);
				//存入session
				session.setAttribute("user", user);
				//存入cookie
				if(autologin == 1) {
					//cookie存真实密码
					user.setPassword(password);
					CookieUtil.addCookie("user", user.getCookieStr(), 7, response);
				}
			}
		}
		DataUtil.writeJSON(json, response, true);
	}
	
}
