package forum.controller.user;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import forum.model.User;
import forum.model.Verify;
import forum.service.UserService;
import forum.service.VerifyService;
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
	@Resource(name = "verifyService")
	private VerifyService verifyService;
	@Resource(name = "mailSender")
	private JavaMailSenderImpl mailSender;

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
	 * 找回密码
	 */
	@RequestMapping("/find")
	@ResponseBody
	public void findPassword(String email, String username, HttpServletResponse response) throws MessagingException {
		User user = userService.checkEmail(email, username);
		JSONObject json = new JSONObject();
		if(user == null) {
			json.addElement("result", "0").addElement("message", "此帐号不存在!");
		}else {
			//检查是否已经发送过邮件
			Verify verify = verifyService.find(new Verify(null, user.getEmail()));
			if(verify == null || verify.isExpired()) {
				//保存记录
				String id = Verify.generateId();
				verifyService.save(new Verify(id, user.getUsername(), email, Verify.computeExpire(), user.getId()));
				json.addElement("result", "1").addElement("message", "链接已经发送到您的邮箱，请于3天之内完成修改");
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				helper.setFrom("xsdwem7@sina.cn");
				helper.setTo(email);
				helper.setSubject("找回密码:");
				helper.setText(StringUtil.getMailContent(id), true);
				mailSender.send(message);
			}else {
				json.addElement("result", "0").addElement("message", "您已申请找回密码，请检查您的邮箱");
			}
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 经过邮箱的链接，修改密码
	 * cp =>> change password
	 */
	@RequestMapping("/cp")
	public String verify(String id, Model model) {
		if(!DataUtil.isValid(id)) {
			return "redirect:/index.html";
		}
		Verify verify = verifyService.find(new Verify(id, null));
		//如果没有此验证记录或者已经过期，转向错误页面
		if(verify == null) {
			model.addAttribute("tips", "此链接不存在");
			return "error";
		}else if(verify.isExpired()) {
			model.addAttribute("tips", "此链接已过期，请重新申请");
			verifyService.delete(id);
			return "error";
		}
		model.addAttribute("verify", verify);
		return "verify";
	}
	
	/**
	 * 保存重置密码
	 */
	@RequestMapping("/cp/save")
	public String save(String id, String password, Integer userid, Model model) {
		if(!DataUtil.isValid(id, password) || !DataUtil.isValid(userid)) {
			return "error";
		}
		//更新密码
		userService.update(new User(userid, StringUtil.md5(password)));
		//删除重置记录
		verifyService.delete(id);
		model.addAttribute("message", "密码重置成功！");
		return "message";
	}
	
}
