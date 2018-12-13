package yogi.base.util;

public class FractionDouble implements Comparable<FractionDouble> {
	private double numerator;
	private double denominator;
	public static FractionDouble BLANK = new FractionDouble(0, 1);
	
	public FractionDouble(double numerator, double denominator) {
		super();
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public double getNumerator() {
		return numerator;
	}
	public double getDenominator() {
		return denominator;
	}
	
	@Override
	public String toString() {
		if(this == BLANK) return "";
		return String.valueOf(getValue());
	}
	
	public Double getValue(){
		return numerator/denominator;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.getValue().hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FractionDouble other = (FractionDouble) obj;
		return other.getValue().equals(this.getValue());
	}

	@Override
	public int compareTo(FractionDouble o) {
		double temp = getValue()-o.getValue();
		int rtn = (int) (temp);
		if(getValue().isNaN())
			return 1;
		if(temp > 0)
		{
			if(rtn>0)
				return rtn;
			else
				return 1;
		}
		if(temp < 0)
		{
			if(rtn<0)
				return rtn;
			else
				return -1;
		}		
			return 0;
	}
		
}