package forum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import forum.util.json.JSON;
import forum.util.json.JSONAble;
import forum.util.json.JSONObject;

/**
 * 板块
 * @author skywalker
 *
 */
public class Section implements Serializable, JSONAble {

	private static final long serialVersionUID = -5975517377515709235L;
	
	private int id;
	private String name;
	//父板块id
	private int pid;
	//版主，像是一个冗余字段，不过可以减少对数据库的访问
	private String manager;
	//可见性
	private boolean visible = true;
	private int clickcount = 0;
	private int articlecount = 0;
	//子版块
	private List<Section> children = new ArrayList<Section>();
	//最后发表的帖子
	private Article lastreply;
	
	public Section(int id, String name, String manager) {
		this.id = id;
		this.name = name;
		this.manager = manager;
	}
	
	public Section() {}
	
	public Section(String name, Integer psid, String manager) {
		this.name = name;
		this.pid = psid;
		this.manager = manager;
	}

	/**
	 * 利用id、name、pid、manager属性产生一个jsonObject
	 */
	@Override
	public JSON getJSON() {
		JSONObject object = new JSONObject();
		object.addElement("id", String.valueOf(this.id)).addElement("name", this.name)
			.addElement("pid", String.valueOf(this.pid)).addElement("manager", this.manager);
		return object;
	}
	
	public Article getLastreply() {
		return lastreply;
	}
	public void setLastreply(Article lastreply) {
		this.lastreply = lastreply;
	}
	public int getClickcount() {
		return clickcount;
	}
	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}
	public int getArticlecount() {
		return articlecount;
	}
	public void setArticlecount(int articlecount) {
		this.articlecount = articlecount;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public List<Section> getChildren() {
		return children;
	}
	public void setChildren(List<Section> children) {
		this.children = children;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
