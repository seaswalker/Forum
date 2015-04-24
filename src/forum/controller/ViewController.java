package forum.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.Article;
import forum.model.Reply;
import forum.model.view.AddrItem;
import forum.model.view.PageBean;
import forum.model.view.QueryResult;
import forum.service.ArticleService;
import forum.service.ReplyService;
import forum.util.DataUtil;

/**
 * 帖子浏览
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Resource(name = "articleService")
	private ArticleService articleService;
	@Resource(name = "replyService")
	private ReplyService replyService;

	/**
	 * 浏览帖子
	 * @param pid 帖子id
	 */
	@RequestMapping
	public String view(Integer pid, Integer sid, Integer pn, Model model, HttpServletRequest request) throws CloneNotSupportedException {
		if(!DataUtil.isValid(pid, sid)) {
			return "error";
		}
		int pageCode = (pn == null || pn < 1) ? 1 : pn;
		//加载帖子(带出作者和类别)
		Article article = articleService.getById(pid);
		model.addAttribute("article", article);
		model.addAttribute("pageBean", getPageBean(pid, pageCode, request));
		model.addAttribute("pid", pid);
		//点击数量加一
		articleService.addClickCount(pid);
		//设置地址项
		@SuppressWarnings("unchecked")
		Map<Integer, AddrItem> addrMap = (Map<Integer, AddrItem>) request.getSession().getAttribute("addrMap");
		if(addrMap != null) {
			AddrItem item = (AddrItem) addrMap.get(sid).clone();
			//设置帖子标题
			item.setTitle(article.getTitle());
			model.addAttribute("addr", item);
		}
		return "view";
	}
	
	/**
	 * 删除帖子
	 * 同时删除回复
	 */
	@RequestMapping("/delete")
	public String delete(Integer pid, Integer sid) {
		if(!DataUtil.isValid(pid, sid)) {
			return "error";
		}
		articleService.delete(pid);
		return "redirect:/forum.html?sid=" + sid;
	}
	
	/**
	 * 改变帖子类型
	 */
	@RequestMapping("/type")
	public String type(Integer pid, Integer sid, Integer type) {
		if(!DataUtil.isValid(pid, sid) || 
				(type != Constants.NORMAL_POST && type != Constants.ELITE_POST && type != Constants.TOP_POST)) {
			return "error";
		}
		articleService.changeType(pid, type);
		return "redirect:/view.html?pid=" + pid + "&sid=" + sid;
	}
	
	/**
	 * 转向帖子修改
	 * @param pid 帖子id
	 */
	@RequestMapping("/edit")
	public String edit(Integer pid, Model model, HttpSession session) {
		//检查登录
		if(session.getAttribute("user") == null) {
			model.addAttribute("tips", "请先登录");
			return "error";
		}
		//查出帖子
		Article article = articleService.getById(pid);
		model.addAttribute("article", article);
		return "edit";
	}
	
	/**
	 * 保存帖子修改
	 */
	@RequestMapping("/save")
	public String save(Integer pid, Integer sid, String content) {
		if(!DataUtil.isValid(pid, sid) || !DataUtil.isValid(content)) {
			return "error";
		}
		articleService.update(pid, content);
		return "redirect:/view.html?pid=" + pid + "&sid=" + sid;
	}
	
	/**
	 * 加载回复
	 */
	private PageBean<Reply> getPageBean(Integer pid, int pageCode, HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		int pageSize = Integer.parseInt((String) context.getAttribute(Constants.REPLY_PAGESIZE)); 
		int pageNumber = Integer.parseInt((String) context.getAttribute(Constants.REPLY_PAGENUMBER)); 
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		where.put("articleid", pid + "");
		String order = "createtime asc";
		QueryResult<Reply> queryResult = replyService.getScrollData(pageCode, pageSize, where, order);
		PageBean<Reply> pageBean = new PageBean<Reply>(queryResult.getRecords(), pageSize, 
				pageCode, queryResult.getRecordsCount(), pageNumber);
		return pageBean;
	}
	
}
