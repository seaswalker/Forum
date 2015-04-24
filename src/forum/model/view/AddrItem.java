package forum.model.view;

import java.io.Serializable;

/**
 * 位置项
 * @author skywalker
 *
 */
public class AddrItem implements Serializable, Cloneable {

	private static final long serialVersionUID = 5323706873806565698L;
	
	//板块id
	private int sid;
	//父板块id，如果没有，为-1
	private int psid;
	private String name;
	//父板块名称
	private String pname;
	//帖子名称，如果此属性不为空，那么上面两个板块均为链接
	private String title;
	
	public AddrItem(int sid, int psid, String name, String pname) {
		this.sid = sid;
		this.psid = psid;
		this.name = name;
		this.pname = pname;
	}
	
	public AddrItem(int sid, String name) {
		this(sid, -1, name, null);
	}

	public AddrItem() {}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPsid() {
		return psid;
	}
	public void setPsid(int psid) {
		this.psid = psid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
