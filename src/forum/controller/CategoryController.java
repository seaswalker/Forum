package forum.controller;

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
 * 前台帖子类别
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/cg")
public class CategoryController {
	
	@Resource(name = "categoryService")
	private CategoryService categoryService;

	/*
	 * 返回json格式的全部类别数据
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(HttpServletResponse response) {
		List<Category> categories = categoryService.findAll();
		//转为json
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		for(Category category : categories) {
			object.clear();
			object.addElement("id", category.getId() + "")
				.addElement("name", category.getName());
			array.addObject(object);
		}
		DataUtil.writeJSON(array, response, true);
	}
	
}
