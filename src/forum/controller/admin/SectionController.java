package forum.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import forum.model.Section;
import forum.service.SectionService;
import forum.util.DataUtil;
import forum.util.json.JSON;
import forum.util.json.JSONArray;
import forum.util.json.JSONObject;

/**
 * 后台板块管理
 * @author skywalker
 *
 */
@Controller("adminSectionCOntroller")
@RequestMapping("/admin/section")
public class SectionController {
	
	@Resource(name = "sectionService")
	private SectionService sectionService;
	
	/**
	 * 转向列表
	 * 使用ajax异步请求，返回json格式
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(HttpServletResponse response) {
		//获取所有板块
		List<Section> sections = sectionService.findAll();
		DataUtil.writeJSON(generateJSON(sections), response, true);
	}
	
	/**
	 * 转向增加
	 */
	@RequestMapping("/add")
	public String addUI(Integer pid, Model model) {
		pid = (pid == null) ? 0 : pid;
		//设置父板块id
		model.addAttribute("pid", pid);
		//顶级板块
		List<Section> sections = sectionService.getTopSections();
		model.addAttribute("sections", sections);
		return "admin/section/saveUI";
	}
	
	/**
	 * 添加子版块
	 */
	@RequestMapping("/sa")
	@ResponseBody
	public void add(Integer psid, String name, String manager, HttpServletResponse response) {
		if(!DataUtil.isValid(psid) || !DataUtil.isValid(name)) {
			return;
		}
		Section section = new Section(name, psid, manager);
		sectionService.save(section);
		JSONObject object = new JSONObject();
		object.addElement("result", "1").addElement("message", "保存成功");
		DataUtil.writeJSON(object, response);
	}
	
	/**
	 * 转向更新
	 */
	@RequestMapping("/update")
	public String saveUI(Integer id, Model model) {
		if(id != null && id > 0) {
			Section section = sectionService.getById(id);
			model.addAttribute("section", section);
			model.addAttribute("id", id);
			//顶级板块
			List<Section> sections = sectionService.getTopSections();
			model.addAttribute("sections", sections);
		}
		return "admin/section/saveUI";
	}
	
	/**
	 * 更新
	 */
	@RequestMapping("/su")
	public String update(Integer id, Integer pid, String name, String manager, Model model) {
		if(!DataUtil.isValid(name)) {
			model.addAttribute("error", "请输入板块名称");
			return "forward:update.html";
		}
		Section section = new Section(name, pid, manager);
		section.setId(id);
		sectionService.update(section);
		model.addAttribute("message", "板块修改成功");
		return "admin/message";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public String delete(Integer id, Model model) {
		if(id != null && id > 0) {
			sectionService.delete(id);
			model.addAttribute("message", "删除成功");
		}else {
			model.addAttribute("message", "删除失败");
		}
		return "admin/message";
	}
	
	/**
	 * 根据板块列表生成json数据
	 */
	private JSON generateJSON(List<Section> sections) {
		JSONArray array = new JSONArray();
		for(Section section : sections) {
			array.addObject(section.getJSONObject());
			//遍历添加子版块
			for(Section child : section.getChildren()) {
				array.addObject(child.getJSONObject());
			}
		}
		return array;
	}
	
}
