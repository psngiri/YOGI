package examples.server.marketattribute.io.db.purge;

import yogi.base.app.ApplicationProperties;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;

import examples.server.marketattribute.MarketAttribute;
import examples.server.marketattribute.MarketAttributeFactory;

public class MarketAttributePurgeDbWriter extends PurgeDbORDbFileWriter<MarketAttribute> {
	
	public MarketAttributePurgeDbWriter(DbResource resource){
		super(resource, MarketAttributeFactory.get(), new MarketAttributePurgeDbFormatter(), MarketAttribute.class, ApplicationProperties.PurgeDeleteDir);
	}
	
}
