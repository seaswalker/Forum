package forum.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import forum.service.UserService;

/**
 * 用户
 * @author skywalker
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 3042370756553127524L;
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String avatar;
	//是否是管理员
	private boolean isAdmin = false;
	//是否是版主，共jsp页面使用，因为jstl无法调用对象方法
	private boolean isManager = false;
	//哪些顶级板块板块的版主
	private List<Integer> topSections = new ArrayList<Integer>();
	//二级板块的版主，含有顶级板块的id
	private List<Integer> secondSections = new ArrayList<Integer>();
	//被哪些板块封禁
	private List<Shield> shieldSections = new ArrayList<Shield>();
	//顶级板块id字符串1,2,3
	private String topSectionsStr;
	//可见，false就关小黑屋
	private boolean visible = true;
	
	public User(int id) {
		this.id = id;
	}
	
	public User(int id, String password) {
		this.id = id;
		this.password = password;
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User() {}
	
	/**
	 * 拼接cookie字符串
	 * username-password
	 */
	public String getCookieStr() {
		StringBuffer sb = new StringBuffer();
		sb.append(username).append("-").append(password);
		return sb.toString();
	}
	
	/**
	 * 是否是sectionid板块的版主
	 */
	public boolean isManagerOfSection(int sectionId) {
		return sectionId > 0 && secondSections.contains(sectionId);
	}
	
	/**
	 * 是否被此板块封禁
	 * 如果被封禁，那么返回此Shield对象，客户端可以利用返回是否为空进行判断
	 */
	public Shield hasShieldedBySection(int sectionId) {
		if(sectionId > 0) {
			for(Shield shield : shieldSections) {
				if(shield.getSectionId() == sectionId) {
					return shield;
				}
			}
		}
		return null;
	}
	
	public boolean getIsManager() {
		return isManager;
	}
	
	/**
	 * 设置版主的板块id
	 */
	public void initSections(UserService userService) {
		topSections.addAll(userService.getTopSectionIds(id));
		secondSections.addAll(userService.getSectionIds(id));
		isManager = secondSections.size() > 0;
	}
	
	/**
	 * 初始化被封禁的板块
	 */
	public void initShieldSections(UserService userService) {
		shieldSections.addAll(userService.getShieldSections(id));
	}
	
	public String getTopSectionsStr() {
		if(topSectionsStr == null) {
			if(topSections.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for(Integer id : topSections) {
					sb.append(id).append(",");
				}
				sb.deleteCharAt(sb.length() - 1);
				topSectionsStr = sb.toString();
			}
		}
		return topSectionsStr;
	}
	public List<Integer> getSecondSections() {
		return Collections.unmodifiableList(secondSections);
	}
	public List<Integer> getTopSections() {
		return Collections.unmodifiableList(topSections);
	}
	public List<Shield> getShieldSections() {
		return shieldSections;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
