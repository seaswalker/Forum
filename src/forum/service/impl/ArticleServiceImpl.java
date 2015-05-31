package forum.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import forum.dao.ArticleDao;
import forum.dao.base.BaseDao;
import forum.model.Article;
import forum.service.ArticleService;
import forum.service.base.BaseServiceImpl;

@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {
	
	private ArticleDao articleDao;

	@Resource(name = "articleDao")
	@Override
	protected void setBaseDao(BaseDao<Article> baseDao) {
		super.baseDao = baseDao;
		this.articleDao = (ArticleDao) baseDao;
	}

	@Override
	public void setLastPublish(int articleid, String name, Date date) {
		String time = new SimpleDateFormat("MM月dd日 HH:mm").format(date);
		String sql = "update article set lastreplyname = '" + name + "',lastreplytime = '" + time + "'"
				+ ",replycount = replycount + 1 where id = " + articleid; 
		articleDao.batchUpdate(sql);
	}

	@Override
	public void addReplyCount(int articleid) {
		String sql = "update article set replycount = replycount + 1 where id = " + articleid;
		articleDao.batchUpdate(sql);
	}

	@Override
	public void addClickCount(int articleid) {
		String sql = "update article set clickcount = clickcount + 1 where id = " + articleid;
		articleDao.batchUpdate(sql);
	}

	@Override
	public void changeType(int id, int type) {
		String sql = "update article set type = " + type + " where id = " + id;
		articleDao.batchUpdate(sql);
	}
	
	@Override
	public void delete(int id) {
		//删除此帖子下的回复
		String replySql = "update reply set visible = false where articleid = " + id;
		articleDao.batchUpdate(replySql);
		articleDao.batchUpdate("update article set visible = false where id = " + id);
	}
	
	@Override
	public void update(int articleid, String content) {
		StringBuilder sql = new StringBuilder("update article set content = '");
		sql.append(content).append("' where id = ").append(articleid);
		articleDao.batchUpdate(sql.toString());
	}

}
