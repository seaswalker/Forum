package forum.service;

import java.util.Date;

import forum.model.Article;
import forum.service.base.BaseService;

public interface ArticleService extends BaseService<Article> {

	/**
	 * 设置最后发表
	 * 同时回复数量加一
	 */
	public void setLastPublish(int articleid, String name, Date date);
	
	/**
	 * 回复数量加一
	 */
	public void addReplyCount(int articleid);
	
	/**
	 * 点击数量加一
	 */
	public void addClickCount(int articleid);
	
	/**
	 * 转换帖子类型
	 * @param id 帖子id
	 * @param type 需要改变到的类型
	 */
	public void changeType(int id, int type);
	
	/**
	 * 修改
	 * 需要修改帖子内容
	 * TODO 可能需要加上最后编辑字段
	 * 此处需要修改最后编辑
	 */
	public void update(int articleid, String content);
	
}
