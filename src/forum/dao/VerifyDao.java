package forum.dao;

import org.springframework.stereotype.Repository;

import forum.dao.base.BaseDao;
import forum.model.Verify;

/**
 * 找回密码验证关系
 * @author skywalker
 *
 */
@Repository("verifyDao")
public interface VerifyDao extends BaseDao<Verify> {

	/**
	 * 根据邮箱或者id查找
	 */
	public Verify find(Verify verify);
	
	public void delete(String id);
	
}
