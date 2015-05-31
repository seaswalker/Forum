package forum.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串工具
 * @author skywalker
 *
 */
public class StringUtil {
	
	/**
	 * 密码找回url
	 */
	private static String url = "http://localhost:8080/Forum/user/cp.html?id=";

	/**
	 * md5加密
	 */
	public static String md5(String str) {
		StringBuilder result = new StringBuilder("");
		if(DataUtil.isValid(str)) {
			char []chars = {'0', '1', '2','3','4','5','6','7',
					'8','9','a','b','c','d','e', 'f'};
			byte []origin = str.getBytes();
			MessageDigest md = null;;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			byte []after = md.digest(origin);
			for(byte b : after) {
				result.append(chars[(b >> 4) & 0xF]);
				result.append(chars[b & 0xF]);
			}
		}
		return result.toString();
	}
	
	/**
	 * 返回邮件内容
	 * @param id ==>> 找回密码验证的id
	 */
	public static String getMailContent(String id) {
		id = (id == null) ? "" : id;
		StringBuilder content = new StringBuilder();
		content.append("<html><head></head><body><div>请务必在3天之内完成修改:</div><div><a href=\"")
			.append(url).append(id).append("\">").append(url).append(id).append("</a></div></body></html>");
		return content.toString();
	}
	
}
