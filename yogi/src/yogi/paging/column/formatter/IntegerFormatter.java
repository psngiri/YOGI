package yogi.paging.column.formatter;

import java.io.Serializable;

import yogi.base.io.Formatter;

public class IntegerFormatter implements Formatter<Integer>, Serializable {

	private static final long serialVersionUID = 2234415639100269629L;

	private int position;	

	public IntegerFormatter(int position) {
		super();
		this.position = position;
	}

	public String format(Integer object) {
		return  String.format("%0" + position + "d", object).substring(0, position);
	}

}
