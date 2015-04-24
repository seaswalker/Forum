package forum.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 帖子
 * @author skywalker
 *
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 6836601414351426489L;
	
	/**
	 * 最后发表日期格式化
	 * 格式为: 4月10日 11:02
	 */
	public static final SimpleDateFormat LASTREPLYDATEFORMATTER = new SimpleDateFormat("MM月dd日 HH:mm");
	
	private int id;
	private String title;
	private String content;
	private Date createtime = new Date();
	//点击量
	private int clickcount;
	//回复量
	private int replycount;
	//是否被删除
	private boolean visible = true;
	//板块id
	private int sectionid;
	//作者
	private User author;
	//所属类别
	private Category category;
	/**
	 * 帖子类型
	 * 1代表普通贴，2代表精品帖，3代表置顶帖
	 * 初始都为普通贴，只有版主和管理员可以修改
	 */
	private int type = 1;
	//最后发表人
	private String lastreplyname;
	//最后发表时间
	private String lastreplytime;
	//所有回复
	private List<Reply> replies;
	
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	public Article() {}
	
	
	public Article(int id) {
		this.id = id;
	}
	
	public List<Reply> getReplies() {
		return replies;
	}
	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}
	public String getLastreplyname() {
		return lastreplyname;
	}
	public void setLastreplyname(String lastreplyname) {
		this.lastreplyname = lastreplyname;
	}
	public String getLastreplytime() {
		return lastreplytime;
	}
	public void setLastreplytime(String lastreplytime) {
		this.lastreplytime = lastreplytime;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public int getSectionid() {
		return sectionid;
	}
	public void setSectionid(int sectionid) {
		this.sectionid = sectionid;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public int getClickcount() {
		return clickcount;
	}
	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
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
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
