package forum.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
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
@ResponseBody
public class SectionController {
	
	@Resource(name = "sectionService")
	private SectionService sectionService;
	
	/**
	 * 使用ajax异步请求，返回json格式板块列表
	 */
	@RequestMapping("/list")
	public void list(HttpServletResponse response) {
		//获取所有板块
		List<Section> sections = sectionService.findAll();
		DataUtil.writeJSON(generateJSON(sections), response, true);
	}
	
	/**
	 * 添加子版块
	 */
	@RequestMapping("/save")
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
	 * 更新板块名称
	 */
	@RequestMapping("/update")
	public void update(Integer id, String name, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(DataUtil.isValid(id) && DataUtil.isValid(name)) {
			Section section = new Section(id, name, null);
			sectionService.update(section);
			json.addElement("result", "1").addElement("message", "更新成功");
		}else {
			json.addElement("result", "0").addElement("message", "更新失败");
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(Integer id, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(DataUtil.isValid(id)) {
			sectionService.delete(id);
			json.addElement("result", "1").addElement("message", "板块删除成功");
		}else {
			json.addElement("result", "0").addElement("message", "删除失败");
		}
		DataUtil.writeJSON(json, response);
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
