package forum.dao.base;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 基本dao
 * @author skywalker
 *
 */
public interface BaseDao<T> {

	public List<T> findAll();
	
	public T getById(int id);
	
	public void update(T entity);
	
	public void delete(int id);
	
	public void save(T entity);
	
	/**
	 * 批量更新
	 */
	public void batchUpdate(@Param("sql") String sql);
	
	/**
	 * 查询数量
	 */
	public int recordsCount(@Param("where") LinkedHashMap<String, String> where);
	
	/**
	 * 获取分页数据
	 */
	public List<T> getScrollData(@Param("begin") int begin, @Param("pageSize") int pageSize, 
			@Param("where") LinkedHashMap<String, String> where, 
			@Param("order") String order);
	
}
