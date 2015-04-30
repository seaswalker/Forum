package forum.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import forum.model.User;

/**
 * 检查管理员和版主权限
 * @author skywalker
 *
 */
public class ManagerTag extends TagSupport {
	
	private static final long serialVersionUID = -75126364099431955L;
	
	/**
	 * 当前的板块，这个对应页面上相应属性传来的参数
	 */
	private int sid;
	
	/**
	 * 此方法没有考虑被封禁用户也是此板块版主或者是总版主的情况
	 * 如果考虑的话需要回复数量 + 1次查询，势必会造成很大的性能浪费，所以把这部分工作提交封禁请求后去做
	 */
	@Override
	public int doStartTag() throws JspException {
		Object object = pageContext.getSession().getAttribute("user");
		if(object == null) {
			pageContext.setAttribute("isManager", false);
		}else {
			User user = (User) object;
			if(user.getIsAdmin()) {
				pageContext.setAttribute("isManager", true);
			}else if(user.isManagerOfSection(sid)) {
				pageContext.setAttribute("isManager", true);
			}else {
				pageContext.setAttribute("isManager", false);
			}
		}
		return SKIP_BODY;
	}
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
}
