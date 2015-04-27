package forum.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import forum.model.User;
import forum.service.UserService;
import forum.util.DataUtil;
import forum.util.StringUtil;
import forum.util.json.JSONObject;

/**
 * 用户信息修改
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 转向个人信息设置
	 */
	@RequestMapping("/set")
	public String set(HttpSession session) {
		Object object = session.getAttribute("user");
		if(object == null) {
			return "redirect:/index.html";
		}
		return "set";
	}
	
	/**
	 * 修改密码
	 * mp --modify password
	 */
	@RequestMapping("/mp")
	public String mp(String old_password, String password, String repassword, Model model, HttpSession session) {
		//略去服务器验证
		Object object = session.getAttribute("user");
		if(object == null) {
			return "redirect:/index.html";
		}
		User user = (User) object;
		if(!user.getPassword().equals(StringUtil.md5(old_password))) {
			model.addAttribute("error", "密码错误!");
			return "set";
		}
		userService.update(new User(user.getId(), StringUtil.md5(repassword)));
		user.setPassword(password);
		model.addAttribute("message", "密码修改成功");
		return "message";
	}
	
	/**
	 * 修改头像
	 * ma --modify avatar
	 * @param new_avatar 新的头像的地址，需要同步到数据库和session
	 */
	@RequestMapping("/ma")
	public String ma(String new_avatar, HttpSession session, Model model) throws IllegalStateException, IOException {
		User user = (User) session.getAttribute("user");
		User update = new User(user.getId());
		update.setAvatar(new_avatar);
		userService.update(update);
		user.setAvatar(new_avatar);
		model.addAttribute("message", "头像修改成功");
		return "message";
	}
	
	/**
	 * 检查用户名是否存在
	 * 存在返回1
	 */
	@RequestMapping("/verify")
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
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 用户封禁
	 * @param uid用户id
	 * @param sid 被那个板块封禁
	 * @param days 封禁的天数
	 */
	@RequestMapping("/shield")
	public void shield(Integer uid, Integer sid, Integer days, HttpSession session, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		Object object = session.getAttribute("user");
		if(object == null) {
			json.addElement("result", "0").addElement("message", "请先登录");
		}else if(!DataUtil.isValid(uid, sid, days)) {
			json.addElement("result", "0").addElement("message", "数据有误");
		}else {
			User user = (User) object;
			if(uid == user.getId()) {
				json.addElement("result", "0").addElement("message", "您不能封禁自己");
			}else if(user.getSections().contains(sid)) {
				//TODO 怎么验证总版主?
			}
		}
	}
	
}
