package yogi.report.condition;

import yogi.base.io.Formatter;
import yogi.base.io.ObjectFormatter;

public abstract class ConditionBaseImpl<C> implements Condition<C> {
	private Formatter<? super C> formatter;
	private String value;
	private String userId;
	
	public ConditionBaseImpl(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public Formatter<? super C> getFormatter() {
	    	if(formatter == null){
	    	    formatter = new ObjectFormatter();
	    	}
	    	return formatter;
	}

	public void setFormatter(Formatter<? super C> formatter) {
		if(formatter != null)
		{
			this.formatter = formatter;
		}else{
			this.formatter = new ObjectFormatter();
		}
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	@Override
	public String getSqlValue(String columnName) {
		StringBuilder sb = new StringBuilder();
		sb.append(' ');
		sb.append(columnName);
		sb.append(' ');
		sb.append(getSqlCondition());
		sb.append(' ');
		sb.append(getValue());
		sb.append(' ');
		return sb.toString();
	}

	protected String getSqlCondition() {
		return "=";
	}
}
