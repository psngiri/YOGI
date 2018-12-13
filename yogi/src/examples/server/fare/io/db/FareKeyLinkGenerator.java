package examples.server.fare.io.db;

import java.util.List;

import yogi.base.io.link.LinkGeneratorImpl;

import examples.server.fare.Fare;
import examples.server.fare.FareCreator;
import examples.server.fare.farekey.FareKey;
import examples.server.fare.farekey.FareKeyManager;

public class FareKeyLinkGenerator extends LinkGeneratorImpl<FareCreator, FareKey>{

	public FareKeyLinkGenerator() {
		super(new FareLinkComparator<FareKey, Fare, FareCreator>());
	}

	@Override
	protected void buildLink(FareCreator from, FareKey to) {
		from.setKey(to);
	}

	@Override
	public List<FareKey> getToObjects() {
			return FareKeyManager.get().findAll();
	}

}
