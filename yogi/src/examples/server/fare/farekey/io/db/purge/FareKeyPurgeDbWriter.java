package examples.server.fare.farekey.io.db.purge;

import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;

import examples.server.fare.FareAssistant;
import examples.server.fare.farekey.FareKey;
import examples.server.fare.farekey.FareKeyFactory;

/**
 * @author Vikram Vadavala
 *
 */
public class FareKeyPurgeDbWriter extends PurgeDbORDbFileWriter<FareKey> {
		
	public FareKeyPurgeDbWriter(DbResource resource){
		super(resource, FareKeyFactory.get(), new FareKeyPurgeDbFormatter(), FareKey.class, FareAssistant.PurgeDeleteDir);
	}
	
}
