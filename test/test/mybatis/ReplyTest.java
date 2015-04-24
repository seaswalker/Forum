package test.mybatis;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.junit.Test;

import forum.model.Reply;
import forum.model.view.QueryResult;
import forum.service.ReplyService;

public class ReplyTest extends TestBase {
	
	@Resource(name = "replyService")
	private ReplyService replyeService;

	@Test
	public void getScrollData() {
		LinkedHashMap<String, String> where = new LinkedHashMap<String, String>();
		where.put("visible", "1");
		where.put("articleid", "36");
		
		LinkedHashMap<String, String> order = new LinkedHashMap<String, String>();
		order.put("createtime", "desc");
		
		QueryResult<Reply> queryResult = replyeService.getScrollData(1, 10, where, order);
		System.out.println(queryResult.getRecords().size());
	}
	
}
