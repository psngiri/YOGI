package yogi.paging;

public class Utilities {
	
	public static int compareInt(int a, int b)
	{
		if(a < b) {
			return -1 ;
		} else if (a > b) {
			return 1;
		} else {
			return 0;					
		}
	}
		
	public static int compareDouble(double a, double b)
	{
		if(a < b) {
			return -1 ;
		} else if (a > b) {
			return 1;
		} else {
			return 0;					
		}
	}
	
	public static int compareNumericStrings(String str1, String str2) {
		
		boolean inputNumber1 = isNumeric(str1);
		boolean inputNumber2 = isNumeric(str2);

		if(inputNumber1 && inputNumber2) {
			return compareInt(Integer.parseInt(str1), Integer.parseInt(str2));
		}		
		if(!inputNumber1 && !inputNumber2) {
			return str1.compareTo(str2);
		}		
		if(!inputNumber1) {		
			return 1;	
		} else {	
			return -1;	
		}
	}
	
	public static boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    }
	
	public static void main(String[] args) {		
		System.out.println("Comparing..." + Utilities.compareNumericStrings("$$$$" , "123"));
	}
}