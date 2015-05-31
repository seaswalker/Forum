package forum.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import forum.dao.base.BaseDao;
import forum.model.view.QueryResult;
import forum.util.DataUtil;

@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	protected BaseDao<T> baseDao;
	
	/**
	 * 需要子类重写
	 */
	protected abstract void setBaseDao(BaseDao<T> baseDao);
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public T getById(int id) {
		return baseDao.getById(id);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(int id) {
		baseDao.delete(id);
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}
	
	@Override
	public QueryResult<T> getScrollData(int pageCode, int pageSize,
			LinkedHashMap<String, String> where, LinkedHashMap<String, String> order) {
		return getScrollData(pageCode, pageSize, where, generateOrderBy(order));
	}
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	@Override
	public QueryResult<T> getScrollData(int pageCode, int pageSize,
			LinkedHashMap<String, String> where, String order) {
		int begin = (pageCode - 1) * pageSize;
		List<T> list = baseDao.getScrollData(begin, pageSize, where, order);
		int count = baseDao.recordsCount(where);
		QueryResult<T> queryResult = new QueryResult<T>(list, count);
		return queryResult;
	}
	
	/**
	 * 生成order by语句
	 */
	private String generateOrderBy(LinkedHashMap<String, String> order) {
		StringBuilder result = new StringBuilder(" ");
		if(DataUtil.isValid(order)) {
			for(String key : order.keySet()) {
				result.append(key).append(" ").append(order.get(key)).append(",");
			}
			result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}
	
}
