package yogi.report.condition.timestamp;

public class TimestampSqlFormatter extends TimestampFormatter {

	private static final long serialVersionUID = 1L;

	public String format(Long timeInMillis) {
		String timestamp = super.format(timeInMillis);
		String[] date;
		if(timestamp.contains(" ")){
			date = timestamp.substring(0,timestamp.indexOf(" ")).split("/");
		}
		else{
			date = timestamp.split("/");
		}
		int year = Integer.parseInt(date[2]);
		int day = Integer.parseInt(date[0]);
		int month;
		switch(date[1]){
			 case "JAN":month=1;break;
			 case "FEB":month=2;break;
			 case "MAR":month=3;break;
			 case "APR":month=4;break;
			 case "MAY":month=5;break;
			 case "JUN":month=6;break;
			 case "JUL":month=7;break;
			 case "AUG":month=8;break;
			 case "SEP":month=9;break;
			case "OCT":month=10;break;
			case "NOV":month=11;break;
			case "DEC":month=12;break;
			default:month=Integer.parseInt(date[1]);
		}
		if(timestamp.contains(" "))
		{
			String time = timestamp.substring(timestamp.indexOf(" ")+1);
			return String.format("%04d-%02d-%02d",year,month,day) + " " + time;
		}
		return String.format("%04d-%02d-%02d",year,month,day);
	}

}
