package forum.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 回复
 * @author skywalker
 *
 */
public class Reply implements Serializable {

	private static final long serialVersionUID = 7685499112858591069L;
	
	private int id;
	private String content;
	//所属帖子
	private int articleid;
	private Date createtime;
	//是否可见
	private boolean visible = true;
	//作者
	private User author;
	
	public Reply(String content) {
		this.content = content;
	}
	
	public Reply() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
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
		Reply other = (Reply) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
