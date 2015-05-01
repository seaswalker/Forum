package forum.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.ShieldSearchResult;
import forum.model.User;
import forum.service.UserService;
import forum.util.DataUtil;
import forum.util.json.JSON;
import forum.util.json.JSONArray;
import forum.util.json.JSONObject;

/**
 * 后台用户管理
 * @author skywalker
 *
 */
@Controller("adminUserController")
@RequestMapping("/admin/user")
public class UserController {
	
	@Resource(name = "userService")
	private UserService userService;

	/**
	 * 用户封禁
	 * @param uid用户id
	 * @param sid 被那个板块封禁
	 * @param pid 被封禁板块的父板块
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
			//被封禁板块的版主--1 被封禁板块总版主 --2 不是本板块版主 --0
			int manageSections = 0;
			if(uid == user.getId()) {
				json.addElement("result", "0").addElement("message", "您不能封禁自己");
			}
			//uid用户也是此板块的版主
			else if((manageSections = userService.isManager(uid, sid)) > 0) {
				//我是总版主，uid是分版主，可以封禁
				if(manageSections == 1 && userService.isManager(user.getId(), sid) > 1) {
					userService.shield(uid, sid, days);
					json.addElement("result", "1").addElement("message", "此用户已被封禁" + days + "天");
				}else {
					json.addElement("result", "0").addElement("message", "您没有权限封禁此用户");
				}
			}
			//uid不是此板块的版主
			else {
				//直接关小黑屋
				userService.shield(uid, sid, days);
				json.addElement("result", "1").addElement("message", "此用户已被封禁" + days + "天");
			}
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 查询用户封禁情况
	 */
	@RequestMapping("/shield_query")
	public void shieldQuery(String username, HttpSession session, HttpServletResponse response) {
		User user = (User) session.getAttribute("user");
		JSON json = new JSONArray();
		List<ShieldSearchResult> results = userService.shieldSearchResult(username, user.getSections());
		for(ShieldSearchResult result : results) {
			json.addObject(result.getJSON());
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 解封
	 */
	@RequestMapping("/unshield")
	public void unshield(String username, int sid, HttpSession session, HttpServletResponse response) {
		User user = (User) session.getAttribute("user");
		JSONObject json = new JSONObject();
		if(user.getUsername().equals(username)) {
			json.addElement("result", "0").addElement("message", "您不能解封自己");
		}else {
			userService.unshield(username, sid);
			json.addElement("result", "1").addElement("message", "解封成功");
		}
		DataUtil.writeJSON(json, response);
	}
	
}
