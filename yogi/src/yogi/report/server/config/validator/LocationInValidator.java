package yogi.report.server.config.validator;

import yogi.report.server.config.BaseInValidator;

public class LocationInValidator extends BaseInValidator{

	
	private static final long serialVersionUID = -6433247597447073653L;

	public LocationInValidator() {
		super("^(([Aa]:\\d{1})|([NnSs]:[A-Za-z]{2})|([Cc]:[A-Za-z]{3})|([Zz]:\\d{1,3})|([Uu]:\\d{1,5})|(\\w{1,5}))(,(([Aa]:\\d{1})|([NnSs]:[A-Za-z]{2})|([Cc]:[A-Za-z]{3})|([Zz]:\\d{1,3})|([Uu]:\\d{1,5})|(\\w{1,5})))*$",
				"must be comma seperated locations. ABC or X:ABC");
	}

}
