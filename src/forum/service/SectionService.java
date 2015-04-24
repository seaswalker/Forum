package forum.service;

import java.util.List;

import forum.model.Article;
import forum.model.Section;
import forum.service.base.BaseService;

public interface SectionService extends BaseService<Section> {

	/**
	 * 获取顶级板块
	 */
	public List<Section> getTopSections();
	
	/**
	 * 设置最后发表
	 * 同时帖子数量加一
	 * @param isSaveArticle如果为true代表保存帖子
	 * 所以帖子数量应该加一
	 */
	public void setLastReply(int sectionid, Article article, boolean isSaveArticle);
	
	/**
	 * 点击数量加一
	 */
	public void addClickCount(int sectionid);
	
	/**
	 * 获取顶级板块以及子版块
	 * @param id 顶级板块id
	 */
	public List<Section> findAllById(int id);
	
}
