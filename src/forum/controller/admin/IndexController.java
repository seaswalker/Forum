package forum.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页
 * @author skywalker
 *
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

	/**
	 * 转向主页
	 */
	@RequestMapping("/index")
	public String index() {
		return "admin/index";
	}
	
}
