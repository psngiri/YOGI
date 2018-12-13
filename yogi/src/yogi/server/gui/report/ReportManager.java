package yogi.server.gui.report;

import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;

public class ReportManager extends RecordManager<ReportKey,ReportData,Report> {
	private static ReportManager itsInstance = new ReportManager();
	private static OneToManyInverseRelationship<ReportKey, Report> keyReport = RelationshipTypeFactory.get().createOneToManyInverseRelationship(ReportKey.class, Report.class,"Reports");


	protected ReportManager() {
		super();
	}

	public static ReportManager get() {
		return itsInstance;
	}

	@Override
	protected OneToManyInverseRelationship<ReportKey, Report> getKeyToRecordRelationShip() {
		return keyReport;
	}

	public void purge() {
		super.purge(ReportKeyManager.get(), ReportFactory.get());
	}
	
	

}
