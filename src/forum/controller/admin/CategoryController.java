package forum.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.Category;
import forum.service.CategoryService;
import forum.util.DataUtil;

/**
 * 后台帖子类别管理
 * @author skywalker
 *
 */
@Controller("adminCategoryController")
@RequestMapping("/admin/cg")
public class CategoryController {
	
	@Resource(name = "categoryService")
	private CategoryService categoryService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "admin/category/list";
	}
	
	/**
	 * 删
	 */
	@RequestMapping("/delete")
	public String delete(int id, Model model) {
		categoryService.delete(id);
		model.addAttribute("message", "类别删除成功");
		return "admin/message";
	}
	
	/**
	 * 转向修改
	 */
	@RequestMapping("/update")
	public String update(int id, Model model) {
		Category category = categoryService.getById(id);
		model.addAttribute("category", category);
		model.addAttribute("id", id);
		return "admin/category/saveUI";
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/su")
	public String su(Integer id, String name, Model model) {
		if(!DataUtil.isValid(name)) {
			model.addAttribute("error", "请输入类别名称");
			return "admin/category/saveUI";
		}
		categoryService.update(new Category(id, name));
		model.addAttribute("message", "类别修改成功");
		return "admin/message";
	}
	
	/**
	 * 转向添加
	 */
	@RequestMapping("/add")
	public String add() {
		return "admin/category/saveUI";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/sa")
	public String sa(String name, Model model) {
		if(!DataUtil.isValid(name)) {
			model.addAttribute("error", "请输入类别名称");
			return "admin/category/saveUI";
		}
		categoryService.save(new Category(name));
		model.addAttribute("message", "类别添加成功");
		return "admin/message";
	}
}
