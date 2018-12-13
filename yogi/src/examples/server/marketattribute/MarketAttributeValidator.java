package examples.server.marketattribute;

import yogi.server.gui.record.base.BaseRecordValidator;

import examples.server.market.Market;

public class MarketAttributeValidator extends BaseRecordValidator<Market, MarketAttribute> {

	public static int STRING_COLUMN_MAX_LENGTH = 20;
	public static double NUMBER_COLUMN_MIN_VALUE = -99999999.99999;
	public static double NUMBER_COLUMN_MAX_VALUE = 99999999.99999;
	
	@Override
	public boolean validate(MarketAttribute record) {
		boolean rtnVal = super.validate(record);
		if(!rtnVal) return false;
		if(record.getAttribute() == null) return false;
		return true;
	}
	
}