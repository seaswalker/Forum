package forum.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import forum.model.User;

/**
 * 只有管理员才有的权限
 * @author skywalker
 *
 */
public class AdminTag extends TagSupport {
	
	private static final long serialVersionUID = -3694909549853477513L;

	@Override
	public int doStartTag() throws JspException {
		Object object = pageContext.getSession().getAttribute("user");
		if(object != null) {
			User user = (User) object;
			if(user.getIsAdmin()) {
				pageContext.setAttribute("isAdmin", true);
			}
		}
		pageContext.setAttribute("isAdmin", false);
		return EVAL_BODY_INCLUDE;
	}
	
}
