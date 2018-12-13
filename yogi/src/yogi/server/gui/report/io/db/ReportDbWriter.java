package yogi.server.gui.report.io.db;

import yogi.base.Selector;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.io.db.RecordDbWriter;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.key.ReportKey;

public class ReportDbWriter extends RecordDbWriter<ReportKey,ReportData,Report>
{	
		public ReportDbWriter(DbResource resource, Selector<? super Report> selector){		
			super(resource, selector, new ReportDbFormatter(), ReportManager.get());
		}
		
		public ReportDbWriter(DbResource resource) {
			this(resource, null);
		}
}

