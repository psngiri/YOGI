package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class IntervalValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	private final static String dateSingle = "(0?[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{4})";
	private final static String frequency = "(|[MTWQFJS]+)";
	
	public IntervalValidator() {
		super(dateSingle + " " + dateSingle+ " " + frequency, 
				"must be DDMMMYYYY DDMMMYYYY MTWQFJS Format");
	}
	
}