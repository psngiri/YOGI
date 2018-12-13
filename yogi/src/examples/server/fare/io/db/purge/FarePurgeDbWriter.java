package examples.server.fare.io.db.purge;

import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;

import examples.server.fare.Fare;
import examples.server.fare.FareAssistant;
import examples.server.fare.FareFactory;

/**
 * @author Vikram Vadavala
 *
 */
public class FarePurgeDbWriter extends PurgeDbORDbFileWriter<Fare> {
		
	public FarePurgeDbWriter(DbResource resource){
		super(resource, FareFactory.get(), new FarePurgeDbFormatter(), Fare.class, FareAssistant.PurgeDeleteDir);
	}
	
}
