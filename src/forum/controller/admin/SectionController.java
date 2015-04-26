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
		JSONObject object = new JSONObject();
		Section section = new Section(name, psid, manager);
		sectionService.save(section);
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
	 * 删除版主
	 * @param managers 删除后剩下的/新的版主
	 * @param removeManagers 需要删除的版主
	 * 多个版主用逗号分隔
	 */
	@RequestMapping("/manager/delete")
	public void manager(Integer id, String removeManagers, String managers, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(DataUtil.isValid(id)) {
			sectionService.deleteManager(id, removeManagers, managers);
			json.addElement("result", "1").addElement("message", "版主删除成功");
		}else {
			json.addElement("result", "0").addElement("message", "版主删除失败");
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 添加版主
	 * @param id 板块id
	 * @param name 版主id
	 */
	@RequestMapping("/manager/add")
	public void addManager(Integer id, String name, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(DataUtil.isValid(id) && DataUtil.isValid(name)) {
			sectionService.addManager(id, name);
			json.addElement("result", "1").addElement("message", "版主添加成功");
		}else {
			json.addElement("result", "0").addElement("message", "添加失败");
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 检查输入的版主id是否存在
	 * 是否已经版主
	 */
	@RequestMapping("/manager/check")
	public void check(Integer id, String name, HttpServletResponse response) {
		JSON json = null;
		if(DataUtil.isValid(id) && DataUtil.isValid(name)) {
			json = sectionService.checkUser(id, name);
		}else {
			json = new JSONObject();
			json.addElement("reuslt", "0").addElement("message", "参数错误");
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
