package yogi.server.gui.util;

import yogi.period.date.Date;
import yogi.period.date.io.YYMMDDDateFormatter;
import yogi.period.date.io.YYMMDDDateScanner;
/**
 * @author Vikram Vadavala
 *
 */
public class GuiAssistant {
	private static GuiAssistant itsInstance = new GuiAssistant();
	private static YYMMDDDateScanner dateScanner = new YYMMDDDateScanner();
	private static YYMMDDDateFormatter dateFormatter = new YYMMDDDateFormatter();
	public static String 	PurgeDeleteDir="";
		
	public static GuiAssistant get() {
		return itsInstance;
	}
		
	public Date getDate(String date) {
		if(null==date) return null;
		date=date.trim();
		if(date.isEmpty()) return null;
		return dateScanner.scan(date).create();
	}
	
	public String getFormattedDate(Date date){
		if(null==date) return null;
		return dateFormatter.format(date);
	}
}
