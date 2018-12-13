package yogi.server.gui.report.key;

import yogi.server.gui.partition.Partition;
import yogi.server.gui.record.key.KeyImpl;
import yogi.server.gui.user.User;

public class ReportKeyImpl extends KeyImpl<ReportKey> implements ReportKey {

	protected ReportKeyImpl(User user, String idName, long createDate, Partition partition) {
		super(user, idName, createDate, partition);
	}
	
}
