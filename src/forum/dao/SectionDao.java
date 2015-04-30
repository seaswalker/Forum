package forum.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import forum.dao.base.BaseDao;
import forum.model.Section;

/**
 * 板块操作
 * @author skywalker
 *
 */
@Repository
public interface SectionDao extends BaseDao<Section> {

	/**
	 * 获取一个板块的子版块
	 */
	public List<Section> getChildren(int id);
	
	/**
	 * 获取一个板块的子版块
	 * 带最后发表
	 */
	public List<Section> getChildrenWithArticle(int id);
	
	/**
	 * 获取特定板块下的所有板块
	 * @param id 顶级板块id，如果不需要此参数，传负值
	 */
	public List<Section> findAllById(@Param("id") int id);
	
}
