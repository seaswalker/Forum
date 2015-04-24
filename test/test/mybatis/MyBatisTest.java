package test.mybatis;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.dao.ArticleDao;
import forum.dao.SectionDao;
import forum.dao.UserDao;
import forum.model.Article;
import forum.model.Section;
import forum.model.User;
import forum.model.view.QueryResult;
import forum.service.ArticleService;
import forum.service.SectionService;
import forum.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MyBatisTest {
	
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "userDao")
	private UserDao userDao;
	@Resource(name = "sectionService")
	private SectionService sectionService;
	@Resource(name = "sectionDao")
	private SectionDao sectionDao;
	@Resource(name = "articleService")
	private ArticleService articleService;
	@Resource(name = "articleDao")
	private ArticleDao articleDao;

	@Test
	public void test1() {
		System.out.println(userService.isUserExists("hehe"));
	}
	
	@Test
	public void testMap() {
		/*Map<?, ?> map = userService.checkAuthority("skywalker");
		System.out.println(map);*/
	}
	
	@Test
	public void testCheck() {
		User user = new User();
		user.setUsername("skywalker");
		user.setAdmin(false);
		user = userDao.find(user);
		System.out.println(user);
	}
	
	@Test
	public void testSection() {
		/*Section section = new Section();
		section.setPid(0);
		section.setName("小明");
		section.setManager(new User(0));
		section.setLastarticle(new Article(0));
		sectionService.save(section);
		System.out.println("插入成功");*/
		
		//查询
		/*Section section = sectionService.getById(3);
		System.out.println(section.getManager());
		System.out.println(section.getLastarticle());*/
		
		//修改
		/*Section section = new Section();
		section.setId(3);
		section.setManager("哈哈");
		section.setName("fuck");
		section.setPid(1);
		sectionService.update(section);
		System.out.println(section.getName());*/
		
		//删除
		//sectionService.delete(3);
		
		//带子版块查询
		/*Section section = sectionService.getById(3);
		System.out.println(section.getChildren().size());*/
		
		List<Section> sections = sectionService.findAll();
		System.out.println(sections.size());
	}
	
	@Test
	public void executeSql() {
		//Section section = sectionDao.test("select * from section where id = 3");
		//String sql = "update section set name = 'C语言' where id = 10";
		//sectionDao.executeSql(sql);
		System.out.println("执行成功");
	}
	
	/**
	 * 测试删除
	 */
	@Test
	public void testDelete() {
		sectionService.delete(3);
		System.out.println("删除成功");
	}
	
	/**
	 * 测试帖子
	 */
	@Test
	public void testArticle() {
		int begin = 3;
		int pageSize = 10;
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		where.put("visible", "1");
		where.put("sectionid", "12");
		
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");
		
		QueryResult<Article> queryResult = articleService.getScrollData(begin, pageSize, where, order);
		System.out.println(queryResult.getRecords().size());
	}
	
	@Test
	public void testSelect() {
		//String sql = "select * from article where visible = true";
		/*List<Article> articles = (List<Article>) articleDao.select(sql);
		System.out.println(articles.size());*/
	}
	
}
