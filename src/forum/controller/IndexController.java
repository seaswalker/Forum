package forum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.Section;
import forum.model.view.AddrItem;
import forum.service.SectionService;

/**
 * 论坛主页
 * @author skywalker
 *
 */
@Controller
public class IndexController {
	
	@Resource(name = "sectionService")
	private SectionService sectionService;
	//是否已经设置了位置Map
	private volatile boolean hasStoredNaviMap = false;

	/**
	 * 跳转到论坛主页
	 * @param psid 父板块id
	 * 如果不是一个父板块或不存在，转到错误页面
	 * 如果为-1(默认值),那么显示所有板块
	 */
	@RequestMapping("/index")
	public String index( String psid, Model model, HttpServletRequest request, HttpSession session) {
		//获取所有板块
		List<Section> sections = sectionService.findAllByIdsWithLastReply(psid);
		model.addAttribute("sections", sections);
		//TODO 此处可能会初始化多次
		if(!hasStoredNaviMap) {
			storeAddr(sections, session);
		}
		//设置导航项
		@SuppressWarnings("unchecked")
		Map<Integer, AddrItem> addrMap = (Map<Integer, AddrItem>) session.getAttribute("addrMap");
		if(addrMap != null) {
			model.addAttribute("addr", addrMap.get(psid));
		}
		return "index";
	}
	
	/**
	 * 在session中保存导航条
	 * 因为此时(只有此时)查出了所有顶级板块以及子版块
	 * key : 子版块的id
	 * value : 对应的位置项
	 */
	private void storeAddr(List<Section> sections, HttpSession session) {
		Map<Integer, AddrItem> addrMap = new HashMap<Integer, AddrItem>();
		for(Section top : sections) {
			addrMap.put(top.getId(), new AddrItem(top.getId(), top.getName()));
			for(Section child : top.getChildren()) {
				addrMap.put(child.getId(), new AddrItem(child.getId(), top.getId(), child.getName(), top.getName()));
			}
		}
		session.setAttribute("addrMap", addrMap);
		hasStoredNaviMap = true;
	}
	
}
