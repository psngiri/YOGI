package yogi.report.condition;

import java.util.Scanner;

public class InRangeInclusiveNumberCondition<C extends Number> extends ConditionBaseImpl<C> {
	private double lowerBound = Double.MIN_VALUE;
	private double upperBound = Double.MAX_VALUE;
	
	public InRangeInclusiveNumberCondition(String value) {
		this(value, '-');
	}
	
	public InRangeInclusiveNumberCondition(String value, char separator) {
		super(value);
		Scanner scanner = new Scanner(value);
		String StringSeparator = String.valueOf(separator);
		scanner.useDelimiter(StringSeparator);
		if(scanner.hasNext() && !value.startsWith(StringSeparator))
		{
			String trim = scanner.next().trim();
			if(trim.length() > 0) lowerBound = Double.parseDouble(trim);
		}
		if(scanner.hasNext() && !value.endsWith(StringSeparator))
		{
			String trim = scanner.next().trim();
			if(trim.length() > 0) upperBound = Double.parseDouble(trim);
		}
	}

	public boolean satisfied(C data) {
		if(data == null) return true;
		if(data.doubleValue() < lowerBound) return false;
		if(data.doubleValue() > upperBound) return false;
		return true;
	}

}
