package forum.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/***
 * 找回密码验证
 * @author skywalker
 *
 */
public class Verify implements Serializable {

	private static final long serialVersionUID = -107587038080659022L;
	
	private static Random random = new Random();
	
	/**
	 * id
	 * 由三位字母 + 三位数字组成，重复概率17576000分之一，忽略不计
	 */
	private String id;
	/**
	 * 邮箱对应的用户名
	 */
	private String username;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 过期时间
	 */
	private Date expire;
	private int userid;
	
	public Verify(String id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public Verify(String id, String username, String email, Date expire, int userid) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.expire = expire;
		this.userid = userid;
	}

	public Verify() {}
	
	/**
	 * 判断是否过期
	 */
	public boolean isExpired() {
		return new Date().after(expire);
	}
	
	/**
	 * 获得过期时间
	 * 3天
	 */
	public static Date computeExpire() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);
		return calendar.getTime();
	}
	
	/**
	 * 生成一个id
	 */
	public static String generateId() {
		int c = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < 3; ++i) {
			c = 96 + random.nextInt(27);
			sb.append((char) c);
		}
		for(int i = 0;i < 3;++ i) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	
}
