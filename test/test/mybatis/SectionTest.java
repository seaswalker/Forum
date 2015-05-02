package test.mybatis;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import forum.dao.SectionDao;
import forum.model.Section;

/**
 * 测试板块
 * @author skywalker
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SectionTest {

	@Resource(name = "sectionDao")
	private SectionDao sectionDao;
	
	@Test
	public void findAll() {
		List<Section> tops = sectionDao.findAllByIdsWithLastReply(null);
		System.out.println(tops.size());
	}
	
}
