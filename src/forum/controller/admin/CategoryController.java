package forum.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import forum.model.Category;
import forum.service.CategoryService;
import forum.util.DataUtil;
import forum.util.json.JSONArray;
import forum.util.json.JSONObject;

/**
 * 后台帖子类别管理
 * @author skywalker
 *
 */
@Controller("adminCategoryController")
@RequestMapping("/admin/category")
@ResponseBody
public class CategoryController {
	
	@Resource(name = "categoryService")
	private CategoryService categoryService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public void list(HttpServletResponse response) {
		List<Category> categories = categoryService.findAll();
		JSONArray array = new JSONArray();
		for(Category category : categories) {
			array.addObject(category.getJSON());
		}
		DataUtil.writeJSON(array, response);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public void delete(Integer id, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(DataUtil.isValid(id)) {
			categoryService.delete(id);
			json.addElement("result", "1").addElement("message", "类别删除成功");
		}else {
			json.addElement("result", "0").addElement("message", "类别删除失败");
		}
		DataUtil.writeJSON(json, response);
	}
	
	/**
	 * 保存
	 * 如果id < 1添加否则修改
	 */
	@RequestMapping("/save")
	public void save(Integer id, String name, HttpServletResponse response) {
		JSONObject json = new JSONObject();
		if(DataUtil.isValid(name)) {
			if(DataUtil.isValid(id)) {
				categoryService.update(new Category(id, name));
			}else {
				categoryService.save(new Category(name));
			}
			json.addElement("result", "1").addElement("message", "类别保存成功");
		}else {
			json.addElement("result", "0").addElement("message", "类别保存失败");
		}
		DataUtil.writeJSON(json, response);
	}
}
