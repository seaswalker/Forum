package test.other;

import org.junit.Test;

import forum.model.view.AddrItem;
import forum.util.json.JSONArray;
import forum.util.json.JSONObject;

public class Others {

	@Test
	public void testJson() {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		object.addElement("name", "skywalker").addElement("age", "90");
		array.addObject(object).addObject(object);
		System.out.println(array);
	}
	
	@Test
	public void testClone() throws CloneNotSupportedException {
		AddrItem a = new AddrItem(1, "skywalker");
		AddrItem b = (AddrItem) a.clone();
		System.out.println("id:" + b.getSid() + ",name:" + b.getName());
	}
	
}
