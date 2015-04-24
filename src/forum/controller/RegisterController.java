package forum.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import forum.model.User;
import forum.service.UserService;
import forum.util.DataUtil;
import forum.util.StringUtil;
import forum.util.json.JSONObject;

/**
 * 用户注册
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 转向注册
	 */
	@RequestMapping
	public String register() {
		return "register";
	}
	
	/**
	 * 登录验证
	 */
	@RequestMapping("/us")
	@ResponseBody
	public void verify(String username, HttpServletResponse response) throws IOException {
		boolean isExists = false;
		if(DataUtil.isValid(username)) {
			isExists = userService.isUserExists(username);
		}
		JSONObject json = new JSONObject();
		if(isExists) {
			json.addElement("result", "1").addElement("message", "此用户名已存在");
		}else {
			json.addElement("result", "0");
		}
		DataUtil.writeJSON(json, response, true);
	}
	
	/**
	 * 检查验证码
	 */
	@RequestMapping("/verify")
	@ResponseBody
	public void verify(String verifycode, HttpSession session, HttpServletResponse response) {
		String code = (String) session.getAttribute("rand");
		JSONObject json = new JSONObject();
		if(code.equals(verifycode)) {
			json.addElement("result", "1");
		}else {
			json.addElement("result", "0").addElement("message", "验证码有误");
		}
		DataUtil.writeJSON(json, response, true);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public String save(String username, String password, String email, HttpSession session) {
		//md5加密
		String md5Pw = StringUtil.md5(password);
		User user = new User(username, md5Pw, email);
		user.setAvatar("avatar/default.gif");
		userService.save(user);
		//存入session
		session.setAttribute("user", user);
		return "message";
	}
	
}
