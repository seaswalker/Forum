package forum.service;

import java.util.List;

import forum.model.Article;
import forum.model.Section;
import forum.service.base.BaseService;
import forum.util.json.JSON;

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
	 * 获取顶级板块以及子版块， 不含最后发表
	 * @param ids 顶级板块id 格式1,2,3
	 */
	public List<Section> findAllByIds(String ids);
	
	/**
	 * 含最后发表
	 */
	public List<Section> findAllByIdsWithLastReply(String ids);
	
	/**
	 * 删除版主
	 * @param id 板块id
	 * @param managers 留下的版主，可以是多个(逗号分割)
	 * @param removeManagers 删除的版主
	 */
	public void deleteManager(int id, String removeManagers, String managers);
	
	/**
	 * 增加版主
	 * @param id 板块id
	 * @param name 版主name
	 */
	public void addManager(int id, String name);
	
	/**
	 * 检查用户是否存在
	 * 是否已经是所选板块的版主
	 * @param id板块id
	 * @param name 用户名
	 * @return json 直接返回校验的json数据
	 * TODO 父板块的版主应该算子版块的版主?
	 */
	public JSON checkUser(int id, String name);
	
}
