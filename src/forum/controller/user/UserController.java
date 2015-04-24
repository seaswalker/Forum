package forum.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.User;
import forum.service.UserService;
import forum.util.StringUtil;

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
	
}
