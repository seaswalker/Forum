package forum.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import forum.dao.SectionDao;
import forum.dao.base.BaseDao;
import forum.model.Article;
import forum.model.Section;
import forum.service.SectionService;
import forum.service.base.BaseServiceImpl;
import forum.util.DataUtil;

@Service("sectionService")
public class SectionServiceImpl extends BaseServiceImpl<Section> implements SectionService {
	
	private SectionDao sectionDao;

	@Resource(name = "sectionDao")
	@Override
	protected void setBaseDao(BaseDao<Section> baseDao) {
		this.sectionDao = (SectionDao) baseDao;
		super.baseDao = baseDao;
	}
	
	@Override
	public List<Section> getTopSections() {
		return sectionDao.getChildren(0);
	}
	
	@Override
	public void delete(int id) {
		//首先获得子版块
		List<Section> children = sectionDao.getChildren(id);
		String sql = null;
		if(DataUtil.isValid(children)) {
			String ids = getIds(children);
			sql = "update article set visible = false "
					+ "where sectionid in (" + ids + ")";
			sectionDao.batchUpdate(sql);
			//删除此板块以及子版块
			ids += ("," + id);
			sql = "update section set visible = false where id in (" + ids + ")";
			sectionDao.batchUpdate(sql);
		}else {
			sectionDao.delete(id);
		}
	}
	
	@Override
	public void setLastReply(int sectionid, Article article, boolean isSaveArticle) {
		StringBuilder sql = new StringBuilder("update section set lastreplyid = ");
		sql.append(article.getId());
		if(isSaveArticle) {
			sql.append(", articlecount = articlecount + 1");
		}
		sql.append(" where id = ").append(sectionid);
		sectionDao.batchUpdate(sql.toString());
	}
	
	@Override
	public void addClickCount(int sectionid) {
		String sql = "update section set clickcount = clickcount + 1 where id = " + sectionid;
		sectionDao.batchUpdate(sql);
	}
	
	@Override
	public List<Section> findAllById(int id) {
		return sectionDao.findAllById(id);
	}
	
	@Override
	public List<Section> findAll() {
		return findAllById(-1);
	}
	
	@Override
	public void deleteManager(int id, String removeManagers, String managers) {
		//更新板块的manager字段
		Section section = new Section(id, null, managers);
		update(section);
		String sql = "delete from user_section where "
				+ " sid = " + id
				+ " and uid in (select id from user where username in (" + removeManagers + "))";
		sectionDao.batchUpdate(sql);
	}
	
	/**
	 * 拼揍id集合，in关键字使用
	 */
	private String getIds(List<Section> children) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < children.size();i ++) {
			sb.append(children.get(i).getId()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
}
