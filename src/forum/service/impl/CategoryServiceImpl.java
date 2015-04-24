package forum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import forum.dao.base.BaseDao;
import forum.model.Category;
import forum.service.CategoryService;
import forum.service.base.BaseServiceImpl;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

	@Resource(name = "categoryDao")
	@Override
	protected void setBaseDao(BaseDao<Category> baseDao) {
		super.baseDao = baseDao;
	}

}
