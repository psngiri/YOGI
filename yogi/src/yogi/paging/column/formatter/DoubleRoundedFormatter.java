package yogi.paging.column.formatter;

import java.io.Serializable;
import java.text.DecimalFormat;

import yogi.base.io.Formatter;

public class DoubleRoundedFormatter implements Formatter<Double>, Serializable {
	
	private static final long serialVersionUID = 8672494108089072754L;

	public static int MAX_FRACTION_DIGITS = 340;

	private DecimalFormat df;
	
	public DoubleRoundedFormatter() {
		super();
		this.df = new DecimalFormat("0");
		this.df.setMaximumFractionDigits(MAX_FRACTION_DIGITS);
	}

	@Override
	public String format(Double object) {	
		if(object.isNaN()) return "NaN";
		String output = df.format(object);
		if(output.endsWith(".0")) {
			output = output.substring(0, output.length() - 2);
		}
		return output;
	}
	
}