package yogi.report.server.tuple.evaluators.binary;

public class Util {
	 public static int toInt( byte[] bytes ,int offset) {
		    return 	((bytes[offset]   & 0xff) << 24) |
		        ((bytes[offset+1] & 0xff) << 16) |
		        ((bytes[offset+2] & 0xff) << 8) |
		         (bytes[offset+3] & 0xff);
	}
	 
	 public static int toInt( byte[] bytes ) {
		 switch(bytes.length){
		 case 1:
			 return (bytes[3] & 0xff);
		 case 2:
			 return ((bytes[2] & 0xff) << 8) |
			         (bytes[3] & 0xff);
		 case 3:
			 return ((bytes[1] & 0xff) << 16) |
				        ((bytes[2] & 0xff) << 8) |
				         (bytes[3] & 0xff);
		 case 4:
			 return ((bytes[0]   & 0xff) << 24) |
				        ((bytes[1] & 0xff) << 16) |
				        ((bytes[2] & 0xff) << 8) |
				         (bytes[3] & 0xff);
		 }
		 return 0;
	}
	 
	 public static float toFloat( byte[] bytes ,int offset) {
		 return Float.intBitsToFloat(toInt(bytes,offset));
	 }		

}
