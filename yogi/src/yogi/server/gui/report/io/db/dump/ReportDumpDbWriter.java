package yogi.server.gui.report.io.db.dump;

import yogi.base.Selector;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.io.db.RecordDbWriter;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportData;
import yogi.server.gui.report.ReportManager;
import yogi.server.gui.report.io.db.ReportDbReader;
import yogi.server.gui.report.key.ReportKey;

public class ReportDumpDbWriter extends RecordDbWriter<ReportKey,ReportData,Report>
{	
		public ReportDumpDbWriter(DbResource resource, Selector<? super Report> selector){		
			super(resource, selector, new ReportDumpDbFormatter(), ReportManager.get(),ReportDbReader.class);
		}
		
		public ReportDumpDbWriter(DbResource resource) {
			this(resource, null);
		}
}

