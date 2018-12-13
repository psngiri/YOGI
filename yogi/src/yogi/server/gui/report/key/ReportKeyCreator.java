package yogi.server.gui.report.key;

import yogi.server.gui.record.key.KeyCreator;

public class ReportKeyCreator extends KeyCreator<ReportKey> {

	@Override
	public ReportKey create() {
		return new ReportKeyImpl(getUser(),getIdName(), getCreateDate(),getPartition());
	}
		
}
