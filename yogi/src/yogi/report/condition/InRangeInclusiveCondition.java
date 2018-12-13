package yogi.report.condition;

import java.util.Scanner;

public class InRangeInclusiveCondition<C> extends ConditionBaseImpl<C> {
	private String lowerBound = "";
	private String upperBound = "";
	
	public InRangeInclusiveCondition(String value) {
		this(value, '-');
	}
	
	public InRangeInclusiveCondition(String value, char separator) {
		super(value);
		Scanner scanner = new Scanner(value);
		String StringSeparator = String.valueOf(separator);
		scanner.useDelimiter(StringSeparator);
		if(scanner.hasNext() && !value.startsWith(StringSeparator))
		{
			lowerBound = scanner.next().trim();
		}
		if(scanner.hasNext() && !value.endsWith(StringSeparator))
		{
			upperBound = scanner.next().trim();
		}
	}

	public boolean satisfied(C data) {
		if(data == null) return true;
		String format = getFormatter().format(data);
		int compareTo = lowerBound.compareTo(format);
		if(compareTo < 0) return false;
		compareTo = upperBound.compareTo(format);
		if(compareTo > 0) return false;
		return true;
	}

}
