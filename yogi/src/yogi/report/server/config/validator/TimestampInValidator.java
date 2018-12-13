package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class TimestampInValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;
	private final static String timestampRange =  "(0?[1-9]|[1,2][0-9]|[3][0,1])[/]((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)((R|r)|(L|l))|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c)|0?[1-9]|1[0-2])[/]([0-9]{4})([ ](0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9]))?[ ]?[-]([ ]?0?[1-9]|[1,2][0-9]|[3][0,1])[/]((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)((R|r)|(L|l))|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c)|0?[1-9]|1[0-2])[/]([0-9]{4})([ ](0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9]))?";
	private final static String timestampSingle = "(0?[1-9]|[1,2][0-9]|[3][0,1])[/]((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)((R|r)|(L|l))|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c)|0?[1-9]|1[0-2])[/]([0-9]{4})([ ](0?[0-9]|1[0-9]|2[0-3]):(0[0-9]|[1,2,3,4,5][0-9]):(0[0-9]|[1,2,3,4,5][0-9]))?";

	
	public TimestampInValidator() {
		super("^(" + timestampRange + "|" + timestampSingle + "[ ]?)(,[ ]?(" + timestampRange + "|" + timestampSingle + ")[ ]?)*$",
			"must be DD/MMM/YYYY [HH:MM:SS] format (any number of single timestamps or timestamp ranges seperated by commas");
	}
	
}