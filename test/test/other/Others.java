package test.other;

import org.junit.Test;

import forum.model.view.AddrItem;
import forum.util.json.JSONObject;

public class Others {

	@Test
	public void testJson() {
		JSONObject object = new JSONObject();
		object.addElement("name", "skywalker").addElement("age", "90");
		JSONObject shit = new JSONObject();
		shit.addElement("name", "shit").addElement("age", "1000");
		object.addElement("friend", shit);
		System.out.println(object);
	}
	
	@Test
	public void testClone() throws CloneNotSupportedException {
		AddrItem a = new AddrItem(1, "skywalker");
		AddrItem b = (AddrItem) a.clone();
		System.out.println("id:" + b.getSid() + ",name:" + b.getName());
	}
	
}
