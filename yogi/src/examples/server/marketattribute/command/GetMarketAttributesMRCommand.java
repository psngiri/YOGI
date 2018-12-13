package examples.server.marketattribute.command;

import yogi.mapreduce.command.ListMapReduceCommand;

import examples.server.marketattribute.MarketAttributeRecord;

public class GetMarketAttributesMRCommand extends ListMapReduceCommand<MarketAttributeRecord, GetMarketAttributesCommand> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -300332933514720754L;

	public GetMarketAttributesMRCommand(GetMarketAttributesCommand command) {
		super(command);
	}

}
