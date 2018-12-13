package yogi.paging.column.formatter;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.base.util.PrecisionFloat;

public class PrecisionFloatFormatter implements Formatter<PrecisionFloat>, Serializable {

	private static final long serialVersionUID = 901717657530047664L;
	
	public String format(PrecisionFloat obj) {
		if(obj == PrecisionFloat.BLANK) return "";
		return obj.toString();
	}
	
	public PrecisionFloatFormatter() {
		super();
	}

}

