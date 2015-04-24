package forum.dao;

import org.springframework.stereotype.Repository;

import forum.dao.base.BaseDao;
import forum.model.Reply;

/**
 * 回复操作
 * @author skywalker
 *
 */
@Repository
public interface ReplyDao extends BaseDao<Reply> {

}
