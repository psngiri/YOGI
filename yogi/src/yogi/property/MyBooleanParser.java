package yogi.property;

public class MyBooleanParser {
	public static Boolean valueOf(String s)
	{
		try {
			int value = Integer.parseInt(s);
			return new Boolean(value != 0);
		} catch (NumberFormatException e) {
		}
		return Boolean.valueOf(s);
	}
}
