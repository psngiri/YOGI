package yogi.paging.column.formatter;

import java.io.Serializable;

import yogi.base.io.Formatter;

public class DoubleFormatter implements Formatter<Double>, Serializable {

	private static final long serialVersionUID = 3882518041675953824L;

	private int position;	
	private int decimal;
	
	public String format(Double object) {
		String format = String.format("%0" + position + "." + decimal + "f", object);
		return format;
	}
	
	public DoubleFormatter(int position, int decimal) {
		super();
		this.position = position;
		this.decimal = decimal;
	}

}

