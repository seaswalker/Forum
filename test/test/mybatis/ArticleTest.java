package test.mybatis;

import javax.annotation.Resource;

import org.junit.Test;

import forum.model.Article;
import forum.service.ArticleService;

public class ArticleTest extends TestBase {
	
	@Resource(name = "articleService")
	private ArticleService articleService;

	@Test
	public void getById() {
		Article article = articleService.getById(36);
		System.out.println(article.getReplies().size());
	}
	
	/**
	 * 测试获取生成的主键id
	 */
	@Test
	public void getId() {
		Article article = new Article("返回id", "测试");
		articleService.save(article);
		System.out.println(article.getId());
	}
	
}
