package forum.util.json;

/**
 * 生成json数据工具
 * @author skywalker
 *
 */
public class JSONObject extends JSON {
	
	/**
	 * 添加一个元素
	 */
	@Override
	public JSONObject addElement(String key, String value) {
		if(key != null && value != null) {
			json.append("\"").append(key).append("\":\"").append(value).append("\",");
		}
		return this;
	}

	@Override
	protected char getBrace() {
		return '{';
	}

}
