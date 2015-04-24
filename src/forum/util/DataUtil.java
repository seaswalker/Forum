package forum.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import forum.model.User;
import forum.util.json.JSON;

/**
 * 数据工具
 * @author skywalker
 *
 */
public class DataUtil {
	
	/**
	 * 支持的图片扩展名
	 * jpg jpeg png gif bmp
	 */
	private static List<String> extensions;
	/**
	 * 支持的图片content-type
	 * image/jpeg image/png image/gif image/bmp
	 */
	private static List<String> contentTypes;

	public static boolean isValid(String...strs) {
		for(String str : strs) {
			if(str == null || str.trim().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断List的有效性
	 */
	public static boolean isValid(List<?> list) {
		return ! (list == null || list.size() == 0);
	}
	
	/**
	 * 判断Map有效性
	 */
	public static boolean isValid(Map<?, ?> map) {
		return ! (map == null || map.size() == 0);
	}
	
	
	/**
	 * 判断Integer类型有效性
	 * 此处小于1即认为无效
	 */
	public static boolean isValid(Integer ...nums) {
		for(Integer i : nums) {
			if(i == null || i < 1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 利用cookie字符串生成User对象
	 * cookie格式如下:
	 * username-password
	 */
	public static User generateUserFromCookie(String cookie) {
		if(!isValid(cookie)) {
			throw new NullPointerException("cookie字符串不能为空");
		}
		String []values = cookie.split("-");
		return new User(values[0], values[1], null);
	}
	
	/**
	 * 获取文件的扩展名
	 */
	public static String getExtend(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	}
	
	/**
	 * 初始化扩展名、content-type
	 */
	private static void init() {
		//TODO 硬编码?
		extensions = new ArrayList<String>(Arrays.asList("jpg", "jpeg", "png", "gif", "bmp")) ;
		contentTypes = new ArrayList<String>(Arrays.asList("image/jpeg", "image/png", "image/gif", "image/bmp"));
	}
	
	/**
	 * 验证是否是图片类型
	 */
	public static boolean isImage(String extension, String contentType) {
		if(extensions == null || contentTypes == null) {
			init();
		}
		return extension.contains(extension) && contentTypes.contains(contentType);
	}
	
	/**
	 * 向客户端发送json格式数据
	 * @param useJSONStyle true --返回类型为application/json;charset=utf-8
	 * 否则 text/html
	 * 为了解决json格式跨域无法传送的问题
	 */
	public static void writeJSON(JSON json, HttpServletResponse response, boolean useJSONStyle) {
		if(response == null) {
			throw new NullPointerException("response为空");
		}
		PrintWriter out = null;
		try {
			response.setContentType(useJSONStyle ? "application/json;charset=utf-8" : "text/html;charset=utf-8");
			out = response.getWriter();
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	public static void writeJSON(JSON json, HttpServletResponse response) {
		writeJSON(json, response, true);
	}
	
}
