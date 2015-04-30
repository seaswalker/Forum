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
import forum.util.json.JSON;
import forum.util.json.JSONObject;

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
	
	@Override
	public JSON checkUser(int id, String name) {
		JSONObject json = new JSONObject();
		String sql = "select count(id) from user where username = '" + name + "'";
		int userCount = sectionDao.queryCount(sql);
		if(userCount < 1) {
			json.addElement("result", "0").addElement("message", "此用户不存在");
			return json;
		}
		sql = "select count(id) from user_section where sid = " + id
				+ " and uid = (select id from user where username = '" + name + "')";
		boolean isManager = (sectionDao.queryCount(sql) > 0);
		if(isManager) {
			json.addElement("result", "0").addElement("message", "此用户已是版主");
		}else {
			json.addElement("result", "1");
		}
		return json;
	}
	
	/**
	 * 重写save
	 * 需要更新关联表
	 */
	@Override
	public void save(Section entity) {
		super.save(entity);
		updateUser_Section(entity.getId(), entity.getManager());
	}
	
	@Override
	public void addManager(int id, String name) {
		String sql = "update section set manager = concat_ws(' ' , manager, '" + name + "') where id = " + id;
		sectionDao.batchUpdate(sql);
		updateUser_Section(id, name);
	}
	
	/**
	 * 更新user-section关联关系
	 */
	private void updateUser_Section(int id, String name) {
		String sql = "insert into user_section values(null, (select id from user where username = '" + name 
				+ "'), " + id + ")";
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
