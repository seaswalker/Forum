package forum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import forum.dao.CategoryDao;
import forum.dao.base.BaseDao;
import forum.model.Category;
import forum.service.CategoryService;
import forum.service.base.BaseServiceImpl;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	
	private CategoryDao categoryDao;

	@Resource(name = "categoryDao")
	@Override
	protected void setBaseDao(BaseDao<Category> baseDao) {
		super.baseDao = baseDao;
		this.categoryDao = (CategoryDao) baseDao;
	}
	
	/**
	 * 首先删除类别下的所有帖子
	 */
	@Override
	public void delete(int id) {
		String sql = "update article set visible = false where categoryid = " + id;
		categoryDao.batchUpdate(sql);
		categoryDao.delete(id);
	}

}
