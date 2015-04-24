package forum.dao;

import org.springframework.stereotype.Repository;

import forum.dao.base.BaseDao;
import forum.model.Article;

/**
 * 帖子操作
 * @author skywalker
 *
 */
@Repository
public interface ArticleDao extends BaseDao<Article> {

}
