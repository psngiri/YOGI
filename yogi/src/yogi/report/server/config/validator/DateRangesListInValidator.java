package yogi.report.server.config.validator;

import yogi.report.server.config.BaseValidator;

public class DateRangesListInValidator extends BaseValidator{

	private static final long serialVersionUID = 1L;

	public DateRangesListInValidator() {
		this("must be comma seperated values of the format DDMMMYY-DDMMMYY^DDMMMYY-DDMMMYY");
	}
	
	public DateRangesListInValidator(String message) {
		super("^(((0?[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2})-(0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2}))(\\^((0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2})-(0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2})))*)(,(((0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2})-(0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2}))(\\^((0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2})-(0[1-9]|[1,2][0-9]|[3][0,1])((J|j)(A|a)(N|n)|(F|f)(E|e)(B|b)|(M|m)(A|a)(R|r)|(A|a)(P|p)(R|r)|(M|m)(A|a)(Y|y)|(J|j)(U|u)(N|n)|(J|j)(U|u)(L|l)|(A|a)(U|u)(G|g)|(S|s)(E|e)(P|p)|(O|o)(C|c)(T|t)|(N|n)(O|o)(V|v)|(D|d)(E|e)(C|c))([0-9]{2})))*))*$", 
				message);
	}
	
}