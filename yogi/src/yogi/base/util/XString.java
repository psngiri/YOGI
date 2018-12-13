package yogi.base.util;

public class XString
{
	/**
	 * This method is implemented by invoking the version that accepts four
	 * arguments and defaults the two arguments zeroOrMore chars to '*' and
	 * anyChar to '?' . Creation date: (8/16/00 4:33:00 PM)
	 * 
	 * @return boolean
	 * @param compareString
	 *            java.lang.String
	 * @param patternString
	 *            java.lang.String
	 */
	public static boolean isLike(String compareString, String patternString)
	{
		if (patternString.startsWith("~")) {
			return !isLike(compareString, patternString.substring(1), '%', '?');
		}
		return isLike(compareString, patternString, '%', '?');
	}

	/**
	 * This method contains the logic to determine is a pattern is present in a
	 * string. Creation date: (8/16/00 4:33:00 PM)
	 * 
	 * @return boolean
	 * @param compareString
	 *            java.lang.String
	 * @param patternString
	 *            java.lang.String
	 * @param zeroOrMore
	 *            java.lang.Char
	 * @param anyChar
	 *            java.lang.Char
	 */
	public static boolean isLike(String compareString, String patternString, char zeroOrMore, char anyChar)
	{
		// if(!isLikeCacheEnabled())
		return XString.isLikeImpl(compareString, patternString, zeroOrMore, anyChar);

		/*
		 * Object key = new Integer(compareString.hashCode()*3 +
		 * patternString.hashCode()*2 + zeroOrMore + anyChar); Boolean ret =
		 * (Boolean)cacheIsLike.get(key); if(ret==null) { boolean b =
		 * XString.isLikeImpl( compareString, patternString, zeroOrMore, anyChar
		 * ); ret = b?Boolean.TRUE:Boolean.FALSE; cacheIsLike.put(key, ret); }
		 * return ret.booleanValue();
		 */
	}

	private static boolean isLikeImpl(String compareString, String patternString, char zeroOrMore, char anyChar)
	{
		int patternCounter = 0;
		/** Counter that keeps a pointer to the pattern. */
		int textCounter = 0;
		/** Counter that keeps a pointer to the comparable string. */
		int lastStar = 0;
		/** Records the position of a 'zeroOrMore' character. */
		int matched = 0;
		/** Record of matches. */
		int compareStringLength = compareString.length();
		/** Length of the comparable string. */
		int patternStringLength = patternString.length();
		/** Length of the pattern string. */
		/**
		 * If both the comparable string equals the pattern string then return
		 * true.
		 */
		if (compareString.equals(patternString))
			return true;
		else
			/**
			 * If the comparable string and the pattern string are not equal
			 * then begin looping through.
			 */
			while (patternCounter < patternStringLength) {
				char p = patternString.charAt(patternCounter++);
				/** Pick up a character from the pattern string. */
				if (p == zeroOrMore) {
					/**
					 * We have reached a * in the pattern. If at the end, then
					 * the receiver has matched it (since the last * matches the
					 * rest of the text). Otherwise, the scan now starts here
					 * (just beyond the *) since the previous portion of the
					 * pattern has been matched.
					 * 
					 */
					if (patternCounter >= patternStringLength)
						return true;
					else
						lastStar = patternCounter;
				} else {
					/**
					 * Not a *. If we are at the end of the text, then the
					 * result is false (since there is no character to match the
					 * next pattern character).
					 */
					if (textCounter >= compareStringLength) {
						return false;
					} else {
						char t = compareString.charAt(textCounter++);
						if ((p == anyChar) || (p == t)) {
							/**
							 * Characters match. If we had a * somewhere, are at
							 * the end of the pattern, and not at the end of the
							 * text, then we need to restart the scan from the
							 * last * while using it to swallow one more
							 * character of the text.
							 * 
							 * This situation arises in cases like the
							 * following: pattern = *abc text = 123abcabc
							 * 
							 * If these conditions aren't met, then we simply go
							 * back to the top of the while loop and process the
							 * next pattern character.
							 */
							if ((lastStar >= 1) && (patternCounter >= patternStringLength) && (textCounter < compareStringLength)) {
							} else {
								continue;
							}
						} else {
							/**
							 * Characters don't match. If we haven't seen a
							 * "zeroOrMore" character in the pattern, then we've
							 * failed to match it.
							 */
							if (lastStar == 0)
								return false;
						}
						/**
						 * We get here if we need to "reset" the scan to the
						 * last "zeroOrMore" wildcard in the pattern. First,
						 * calculate how many characters were matched after the
						 * last such wildcard in the pattern:
						 */
						matched = patternCounter - lastStar - 1;
						patternCounter = lastStar;
						/**
						 * Back up in the text stream to rescan all but the
						 * first matched character:
						 */
						textCounter -= matched;
					}
				}
			}
		/**
		 * At end of pattern. If also at the end of the text, then they match,
		 * otherwise, they don't:
		 */
		return (textCounter >= compareStringLength);
	}

	/**
	 * @author Name	 : Raja Roy 
	 * @author Email : raja.roy@aa.com
	 * 
	 * @param subject
	 * @param textToLookFor
	 * @return
	 */
	public static int occurrencesOf(String subject, String textToLookFor)
	{
		int index = 0;
		int count = -1;
		if (subject == null)
			return 0;

		while (index >= 0 && index != subject.length()) {
			index = subject.indexOf(textToLookFor, index + 1);
			count++;
		}

		return count;
	}

	/**
	 * @author Name	 : Raja Roy 
	 * @author Email : raja.roy@aa.com
	 * 
	 * @param subjectText
	 * @param length
	 * @param paddingCharacter
	 * @return
	 */
	public static String rpad(String subjectText, int length, char paddingCharacter)
	{
		if (subjectText == null) subjectText = "";
		if (subjectText.length() == length){
			return subjectText;
		}
		StringBuffer sb = new StringBuffer(subjectText);
		while (sb.length() < length) {
			sb.append(paddingCharacter);
		}
		return sb.toString();
	}

	/**
	 * @author Name	 : Raja Roy 
	 * @author Email : raja.roy@aa.com
	 * 
	 * @param subjectText
	 * @param length
	 * @param paddingCharacter
	 * @return
	 */
	public static String lpad(String subjectText, int length, char paddingCharacter)
	{
		if (subjectText == null) subjectText = "";
		if (subjectText.length() == length) {
			return subjectText;
		}
		StringBuffer sb = new StringBuffer(subjectText);
		while (sb.length() < length) {
			sb.insert(0, paddingCharacter);
		}
		return sb.toString();
	}
}