package yogi.paging.column.formatter;

import java.io.Serializable;

import yogi.base.io.Formatter;

public class FloatFormatter implements Formatter<Float>, Serializable {

	private static final long serialVersionUID = 901717657530047664L;

	private int position;	
	private int decimal;
	
	public String format(Float object) {
		String format = String.format("%0" + position + "." + decimal + "f", object);
		return format;
	}
	
	public FloatFormatter(int position, int decimal) {
		super();
		this.position = position;
		this.decimal = decimal;
	}

}

