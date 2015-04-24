package forum.controller;

import java.util.Date;
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
import forum.model.Category;
import forum.model.User;
import forum.model.view.AddrItem;
import forum.model.view.PageBean;
import forum.model.view.QueryResult;
import forum.service.ArticleService;
import forum.service.SectionService;
import forum.util.DataUtil;

/**
 * 论坛
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/forum")
public class ForumController {
	
	@Resource(name = "articleService")
	private ArticleService articleService;
	@Resource(name = "sectionService")
	private SectionService sectionService;

	/**
	 * 一个板块主页
	 * @param cg 帖子category id
	 * @param type 表示排序规则
	 * 默认new--最新
	 * hot--热门 按回复数量倒序，不显示置顶帖
	 * elite--精品帖，仅显示精品帖
	 */
	@RequestMapping
	public String forum(Integer pn, Integer cg, String type, int sid, HttpServletRequest request, Model model, HttpSession session) {
		//板块点击数量加一
		sectionService.addClickCount(sid);
		ServletContext context = request.getServletContext();
		int pageCode = (pn == null || pn < 1) ? 1 : pn;
		//获取分页大小
		int pageSize = Integer.parseInt((String) context.getAttribute(Constants.FORUM_PAGESIZE));
		//获取页码数量
		int pageNumber = Integer.parseInt((String) context.getAttribute(Constants.FORUM_PAGENUMBER));
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		where.put("visible", "1");
		where.put("sectionid", String.valueOf(sid));
		if(cg != null && cg >= 1) {
			where.put("categoryid", cg + "");
		}
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		//确定排序规则
		if(!DataUtil.isValid(type) || "new".equals(type)) {
			order.put("ceil(type / 2)", "desc");
			order.put("createtime", "desc");
		}else if("hot".equals(type)) {
			order.put("replycount", "desc");
		}else if("elite".equals(type)) {
			order.put("createtime", "desc");
			//修改where语句
			where.put("type", Constants.ELITE_POST + "");
		}
		QueryResult<Article> queryResult = articleService
				.getScrollData(pageCode, pageSize, where, order);
		PageBean<Article> pageBean = new PageBean<Article>(queryResult.getRecords(), pageSize, pageCode,
				queryResult.getRecordsCount(), pageNumber);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("sid", sid);
		model.addAttribute("type", type);
		model.addAttribute("cg", cg);
		//设置地址项
		@SuppressWarnings("unchecked")
		Map<Integer, AddrItem> addrMap = (Map<Integer, AddrItem>) session.getAttribute("addrMap");
		if(addrMap != null) {
			model.addAttribute("addr", addrMap.get(sid));
		}
		return "forum";
	}
	
	/**
	 * 发帖
	 */
	@RequestMapping("/post")
	public String post(int sid, int cg, String title, String content, HttpSession session, Model model) {
		//检查user
		Object object = session.getAttribute("user");
		if(object == null) {
			model.addAttribute("tips", "请先登录再发帖");
			return "error";
		}
		//出现错误，直接转向错误页面
		if(sid == 0 || !DataUtil.isValid(title) || !DataUtil.isValid(content)) {
			return "error";
		}
		Article article = new Article(title, content);
		article.setCategory(new Category(cg));
		article.setSectionid(sid);
		User user = (User) object;
		article.setAuthor(user);
		Date date = new Date();
		article.setCreatetime(date);
		article.setLastreplyname(user.getUsername());
		article.setLastreplytime(Article.LASTREPLYDATEFORMATTER.format(date));
		articleService.save(article);
		//更新板块的最后发表
		sectionService.setLastReply(sid, article, true);
		return "redirect:/forum.html?sid=" + sid;
	}
	
}
