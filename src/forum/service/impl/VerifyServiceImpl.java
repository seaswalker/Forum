package forum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import forum.dao.VerifyDao;
import forum.dao.base.BaseDao;
import forum.model.Verify;
import forum.service.VerifyService;
import forum.service.base.BaseServiceImpl;

@Service("verifyService")
public class VerifyServiceImpl extends BaseServiceImpl<Verify> implements VerifyService {
	
	private VerifyDao verifyDao;

	@Resource(name = "verifyDao")
	@Override
	protected void setBaseDao(BaseDao<Verify> baseDao) {
		this.verifyDao = (VerifyDao) baseDao;
		super.baseDao = baseDao;
	}

	@Override
	public Verify find(Verify verify) {
		return verifyDao.find(verify);
	}

	@Override
	public void delete(String id) {
		verifyDao.delete(id);
	}
	
}
