package forum.model;

import java.io.Serializable;

import forum.util.json.JSON;
import forum.util.json.JSONAble;
import forum.util.json.JSONObject;

/**
 * 小黑屋管理，封装搜索结果
 * @author skywalker
 *
 */
public class ShieldSearchResult implements Serializable, JSONAble {

	private static final long serialVersionUID = 585525745117649386L;
	
	//用户
	private String username;
	//板块id
	private Integer sid;
	private String endtime;
	//板块名称
	private String name;
	
	@Override
	public JSON getJSON() {
		JSONObject json = new JSONObject();
		json.addElement("username", username).addElement("name", name)
			.addElement("endtime", endtime).addElement("sid", String.valueOf(sid));
		return json;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endTime) {
		this.endtime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
