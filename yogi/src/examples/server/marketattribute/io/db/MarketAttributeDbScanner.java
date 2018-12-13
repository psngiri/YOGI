package examples.server.marketattribute.io.db;

import yogi.base.app.ErrorReporter;
import yogi.base.io.CreatorScanner;
import yogi.base.io.db.DbRecord;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.user.UserManager;

import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeCreator;


public class MarketAttributeDbScanner implements CreatorScanner<MarketAttribute, MarketAttributeCreator, DbRecord>{
	
	public MarketAttributeCreator scan(DbRecord dbRecord) {
		MarketAttributeCreator creator = new MarketAttributeCreator();
		try {
			creator.setAction(ActionAssistant.get().getAction(dbRecord.getInt(1)));
			creator.setTimeStamp(dbRecord.getLong(2));
			creator.setModifiedByUser(UserManager.get().getUser(dbRecord.getString(3)));
			creator.setMarket(dbRecord.getString(4));
			creator.setAttribute(dbRecord.getString(5));
		  } catch (Exception e) {
			ErrorReporter.get().error(dbRecord, e);
		}
		return creator;		
	}

}
