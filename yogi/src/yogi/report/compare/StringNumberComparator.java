package yogi.report.compare;

import java.util.Comparator;

public class StringNumberComparator implements Comparator<String> {

	/*  
	  Sorting order single character alphabets, single digit numbers, double character alphanumerics, double digit numbers... 
	  Example : [A, N, W, 1, 2, 9, AA, A9, FL, F9, 56, 99]
	  */
	
	@Override
	public int compare(String o1, String o2) {
		int rtnValue = o1.length()-o2.length();
		if(rtnValue != 0) return rtnValue;
		int length = Math.min(o1.length(), o2.length());
		for(int i =0; i < length; i ++){
			char c1 = o1.charAt(i);
			char c2 = o2.charAt(i);
			int d1, d2;
			if(Character.isDigit(c1)) d1 = c1 + 128; else d1 = c1;
			if(Character.isDigit(c2)) d2 = c2 + 128; else d2 = c2;			
			rtnValue = d1-d2;	
			if(rtnValue != 0) return rtnValue;			
		}
		return 0;
	}

}
