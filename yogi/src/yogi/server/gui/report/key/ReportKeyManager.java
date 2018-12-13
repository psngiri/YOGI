package yogi.server.gui.report.key;

import yogi.base.Factory;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.record.key.KeyCreator;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.record.key.KeyValidator;
import yogi.server.gui.userpartition.UserPartition;

public class ReportKeyManager extends KeyManager<ReportKey> {
	
	private static ReportKeyManager itsInstance = new ReportKeyManager();
	private static OneToManyInverseRelationship<UserPartition, ReportKey> userReportKey = RelationshipTypeFactory.get().createOneToManyInverseRelationship(UserPartition.class, ReportKey.class,"ReportKeys");
	private static ReportKeyCreator reportKeyCreator = new ReportKeyCreator();

	protected ReportKeyManager() {
		super();
	}

	public static ReportKeyManager get() {
		return itsInstance;
	}

	@Override
	protected OneToManyInverseRelationship<UserPartition, ReportKey> getUserPartionToKeyRelationShip() {
		return userReportKey;
	}

	@Override
	public Factory<ReportKey> getFactory() {
		return ReportKeyFactory.get();
	}

	@Override
	public KeyCreator<ReportKey> getCreator() {
		return reportKeyCreator;
	}

	@Override
	protected KeyValidator<? super ReportKey> getValidator() {
		return new ReportKeyValidator();
	}
	
}
