package examples.server.marketattribute;

import yogi.base.relationship.RelationshipAssistant;

public class MarketAttributeAssistant extends RelationshipAssistant<MarketAttribute> {
	
	private static MarketAttributeAssistant itsInstance = new MarketAttributeAssistant();

	public static MarketAttributeAssistant get() {
		return itsInstance;
	}
		
}
