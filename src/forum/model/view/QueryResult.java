package forum.model.view;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询结果
 * @author skywalker
 *
 */
public class QueryResult<T> implements Serializable {

	private static final long serialVersionUID = 9147984048979174961L;
	
	private List<T> records;
	private int recordsCount;
	
	public QueryResult(List<T> records, int recordsCount) {
		this.records = records;
		this.recordsCount = recordsCount;
	}
	
	public QueryResult() {}
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getRecordsCount() {
		return recordsCount;
	}
	public void setRecordsCount(int recordsCount) {
		this.recordsCount = recordsCount;
	}

}
