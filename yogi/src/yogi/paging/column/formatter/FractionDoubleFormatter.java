package yogi.paging.column.formatter;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.base.util.FractionDouble;

public class FractionDoubleFormatter implements Formatter<FractionDouble>, Serializable {

	private static final long serialVersionUID = 3882518041675953824L;

	private int position;	
	private int decimal;
	
	public String format(FractionDouble object) {
		if(object == FractionDouble.BLANK){ return "";}
		if(object.getDenominator() == 0){ return "NaN";}
		String format = String.format("%0" + position + "." + decimal + "f", object.getValue());
		return format;
	}
	
	public FractionDoubleFormatter(int position, int decimal) {
		super();
		this.position = position;
		this.decimal = decimal;
	}

}

