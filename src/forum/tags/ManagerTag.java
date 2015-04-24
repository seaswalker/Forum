package forum.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import forum.model.User;
import forum.util.DataUtil;

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
	
	@Override
	public int doStartTag() throws JspException {
		Object object = pageContext.getSession().getAttribute("user");
		//如果没有登录那么没有权限
		if(object != null) {
			User user = (User) object;
			if(user.getIsAdmin()) {
				return EVAL_BODY_INCLUDE;
			}
			//版主
			if(DataUtil.isValid(user.getSections())) {
				if(user.getSections().contains(sid)) {
					return EVAL_BODY_INCLUDE;
				}
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