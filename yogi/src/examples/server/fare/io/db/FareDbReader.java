package examples.server.fare.io.db;

import java.util.Collection;

import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.link.LinkRecordProcessor;
import yogi.base.io.resource.db.DbResource;

import examples.server.fare.Fare;
import examples.server.fare.FareCreator;
import examples.server.fare.FareFactory;
import examples.server.fare.FareValidator;

public class FareDbReader extends DefaultDbRecordReader<Fare> {
	public static boolean RUN = true;
	protected LinkRecordProcessor<Fare, FareCreator, DbRecord> recordProcessor;
	
	public FareDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	private void setup() {
		recordProcessor = new LinkRecordProcessor<Fare, FareCreator, DbRecord>(new FareValidator(), new FareDbScanner(), FareFactory.get(), new FareDbRecordSelector(), null);
		this.addRecordProcessor(recordProcessor);
		recordProcessor.addLinkGenerator(new FareKeyLinkGenerator());	
	}

	public FareDbReader(Collection<DbRecord> fares) {
		super(fares);
		setup();
	}
	
}
