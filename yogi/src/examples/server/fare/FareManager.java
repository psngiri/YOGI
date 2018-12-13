package examples.server.fare;

import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.base.record.IRecordManager;

import examples.server.fare.farekey.FareKey;
import examples.server.fare.farekey.FareKeyManager;
import examples.server.sub.Sub;
import examples.server.sub.SubManager;

public class FareManager extends IRecordManager<FareKey,Sub,Fare> {
	private static FareManager itsInstance = new FareManager();
	private static OneToManyInverseRelationship<FareKey, Fare> fareKeyfareRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(FareKey.class,Fare.class, "Fares");
	private static OneToManyInverseRelationship<Sub, Fare> subFareRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Sub.class,Fare.class, "FaresInSub");
	
	protected FareManager() {
		super();
	}

	public static FareManager get() {
		return itsInstance;
	}

	@Override
	protected OneToManyInverseRelationship<FareKey, Fare> getKeyToRecordRelationShip() {
		return fareKeyfareRel;
	}

	@Override
	protected OneToManyInverseRelationship<Sub, Fare> getVersionToRecordRelationShip() {
		return subFareRel;
	}

	@Override
	protected ImmutableList<Sub> getAllVersions() {
		return SubManager.get().findAll();
	}

	public void purge() {
		super.purge(FareKeyManager.get(), FareFactory.get());
	}
		
}
