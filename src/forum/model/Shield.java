package forum.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 一次封禁
 * @author skywalker
 *
 */
public class Shield implements Serializable {

	private static final long serialVersionUID = -2132077983221871568L;
	
	//被哪个板块封禁
	private int sectionId;
	//解封时间
	private Date endTime;
	
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
