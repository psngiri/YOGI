package examples.server.marketattribute.command;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Creator;
import yogi.server.action.ActionAssistant;
import yogi.server.gui.record.base.BaseRecordFactory;
import yogi.server.gui.record.base.command.BaseRecordClusterCommand;
import yogi.server.gui.user.UserManager;

import examples.server.market.Market;
import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeCreator;
import examples.server.marketattribute.MarketAttributeFactory;
import examples.server.marketattribute.MarketAttributeRecord;

public class MarketAttributeClusterCommand extends BaseRecordClusterCommand<Market, MarketAttribute> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MarketAttributeRecord> records;
	private MarketAttributeRecord record;
	
	public MarketAttributeClusterCommand(MarketAttribute object) {
		super(object);
		record = new MarketAttributeRecord(object);
	}

	public MarketAttributeClusterCommand(List<MarketAttribute> objects) {
		super(objects);
		records = new ArrayList<MarketAttributeRecord>();
		for(MarketAttribute object: objects){
			MarketAttributeRecord record = new MarketAttributeRecord(object);
			records.add(record);
		}
	}

	protected Creator<MarketAttribute> getCreator(MarketAttributeRecord record) {
		MarketAttributeCreator creator = new MarketAttributeCreator();
		creator.setAction(ActionAssistant.get().getAction(record.getAction()));
		creator.setTimeStamp(record.getTimeStamp());
		creator.setModifiedByUser(UserManager.get().getUser(record.getModifiedByUserId()));
		creator.setMarket(record.getMarket());
		creator.setAttribute(record.getAttribute());
		return creator;
	}

	@Override
	protected BaseRecordFactory<Market, MarketAttribute> getFactory() {
		return MarketAttributeFactory.get();
	}

	@Override
	protected void process(MarketAttribute object) {
		
	}

	@Override
	protected List<Creator<MarketAttribute>> getCreators() {
		if(records == null) return null;
		List<Creator<MarketAttribute>> rtnValue = new ArrayList<Creator<MarketAttribute>>();
		for(MarketAttributeRecord record: records){
			rtnValue.add(getCreator(record));
		}
		return rtnValue;
	}

	@Override
	protected Creator<MarketAttribute> getCreator() {
		if(record == null) return null;
		return getCreator(record);
	}

}
