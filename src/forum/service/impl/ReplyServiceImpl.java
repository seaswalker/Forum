package forum.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import forum.dao.base.BaseDao;
import forum.model.Reply;
import forum.service.ReplyService;
import forum.service.base.BaseServiceImpl;

@Service("replyService")
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {
	
	@Resource(name = "replyDao")
	@Override
	protected void setBaseDao(BaseDao<Reply> baseDao) {
		super.baseDao = baseDao;
	}
	
}
