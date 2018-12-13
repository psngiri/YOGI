package yogi.base.util;

public class PrecisionFloat implements Comparable<PrecisionFloat> {
	private Float value;
	private int decimal;
	public static PrecisionFloat BLANK = new PrecisionFloat(Float.MIN_VALUE, 2);
		
	public PrecisionFloat(float value, int decimal) {
		super();
		this.value = value;
		this.decimal = decimal;
	}
	public Float getValue() {
		return value;
	}
	public int getDecimal() {
		return decimal;
	}
	@Override
	public String toString() {
		return String.format("%."+decimal+"f", value);
	}
	
	@Override
	public int compareTo(PrecisionFloat o) {
		return Float.compare(this.value, o.getValue()); 
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj instanceof PrecisionFloat)
		{
			PrecisionFloat precisionFloat = (PrecisionFloat)obj;
			return this.getValue().equals(precisionFloat.getValue());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		 return value.hashCode();
	}
	
}
