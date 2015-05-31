package forum.service;

import forum.model.Verify;
import forum.service.base.BaseService;

public interface VerifyService extends BaseService<Verify> {

	/**
	 * 根据邮箱或者id查找
	 */
	public Verify find(Verify verify);
	
	public void delete(String id);
	
}
