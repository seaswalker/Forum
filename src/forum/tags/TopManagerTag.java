package forum.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import forum.model.User;

/**
 * 判断是否是顶级板块的版主
 * @author skywalker
 *
 */
public class TopManagerTag extends TagSupport {

	private static final long serialVersionUID = -3891042388754550477L;

	@Override
	public int doStartTag() throws JspException {
		Object object = pageContext.getSession().getAttribute("user");
		if(object != null) {
			User user = (User) object;
			if(user.getTopSections().size() > 0) {
				pageContext.setAttribute("isTopManager", true);
				return EVAL_BODY_INCLUDE;
			}
		}
		pageContext.setAttribute("isTopManager", false);
		return EVAL_BODY_INCLUDE;
	}
	
}
