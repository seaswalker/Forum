package forum.service.base;

import java.util.LinkedHashMap;
import java.util.List;

import forum.model.view.QueryResult;

public interface BaseService<T> {

	public List<T> findAll();
	
	public T getById(int id);
	
	public void update(T entity);
	
	public void delete(int id);
	
	public void save(T entity);
	
	/**
	 * 获取分页数据
	 */
	public QueryResult<T> getScrollData(int pageCode, int pageSize, LinkedHashMap<String, String> where, LinkedHashMap<String, String> order);
	
	/**
	 * 重载
	 * 直接获得String类型order by 语句
	 */
	public QueryResult<T> getScrollData(int pageCode, int pageSize, LinkedHashMap<String, String> where, String order);
}
