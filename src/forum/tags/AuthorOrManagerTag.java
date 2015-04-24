package forum.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import forum.model.User;
import forum.util.DataUtil;

/**
 * 作者或版主或管理员
 * @author skywalker
 *
 */
public class AuthorOrManagerTag extends TagSupport {

	private static final long serialVersionUID = -1349261409553317328L;
	
	private int sid;
	private String name;
	
	@Override
	public int doStartTag() throws JspException {
		Object object = pageContext.getSession().getAttribute("user");
		if(object != null) {
			User user = (User) object;
			if(user.getIsAdmin() || user.getUsername().equals(name)) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
