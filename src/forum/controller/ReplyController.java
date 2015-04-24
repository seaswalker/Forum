package forum.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import forum.model.Article;
import forum.model.Reply;
import forum.model.User;
import forum.service.ArticleService;
import forum.service.ReplyService;
import forum.service.SectionService;
import forum.util.DataUtil;

/**
 * 回复控制器
 * @author skywalker
 *
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {

	@Resource(name = "replyService")
	private ReplyService replyService;
	@Resource(name = "articleService")
	private ArticleService articleService;
	@Resource(name = "sectionService")
	private SectionService sectionService;
	
	/**
	 * 发表回复
	 */
	@RequestMapping
	public String reply(Integer sid, Integer pid, String content, HttpSession session, Model model) {
		if(!DataUtil.isValid(sid, pid) || !DataUtil.isValid(content)) {
			return "error";
		}
		Object object = session.getAttribute("user");
		if(object == null) {
			model.addAttribute("tips", "请先登录");
			return "error";
		}
		User user = (User) object;
		Date date = new Date();
		Reply reply = new Reply(content);
		reply.setCreatetime(date);
		reply.setArticleid(pid);
		reply.setAuthor(user);
		replyService.save(reply);
		//更新帖子最后发表，并且回复数量加一
		articleService.setLastPublish(pid, user.getUsername(), date);
		//设置板块最后发表为本回复所属帖子
		sectionService.setLastReply(sid, new Article(pid), false);
		return "redirect:view.html?pid=" + pid + "&sid=" + sid;
	}
	
	/**
	 * 回复删除
	 * @param rid 回复id
	 * @param pid 帖子id
	 */
	@RequestMapping("/delete")
	public String delete(Integer rid, Integer pid, Integer sid) {
		if(!DataUtil.isValid(rid, pid, sid)) {
			return "error";
		}
		replyService.delete(rid);
		return "redirect:/view.html?pid=" + pid + "&sid=" + sid;
	}
	
}
