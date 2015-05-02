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
	 * 获取一组板块下的所有板块，不需要最后发表
	 * @param ids 顶级板块id 格式1,2,3
	 * ids如果为空，那么查询所有
	 */
	public List<Section> findAllByIds(@Param("ids") String ids);
	
	/**
	 * 子版块查出最后发表
	 */
	public List<Section> findAllByIdsWithLastReply(@Param("ids") String ids);
	
}
