package forum.model;

import java.io.Serializable;

import forum.util.json.JSON;
import forum.util.json.JSONObject;

/**
 * 帖子类别
 * @author skywalker
 *
 */
public class Category implements Serializable {

	private static final long serialVersionUID = -7532185703358157233L;
	
	private int id;
	private String name;
	private boolean visible = true;
	
	public Category() {}
	
	public Category(int id) {
		this.id = id;
	}

	public Category(String name) {
		this.name = name;
	}
	
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 产生一个json对象
	 */
	public JSON getJSON() {
		JSON json = new JSONObject();
		json.addElement("id", String.valueOf(id)).addElement("name", name);
		return json;
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
