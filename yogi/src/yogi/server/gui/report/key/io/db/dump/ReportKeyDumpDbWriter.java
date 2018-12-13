package yogi.server.gui.report.key.io.db.dump;

import yogi.base.Selector;
import yogi.base.app.ApplicationProperties;
import yogi.base.io.db.BaseIncrementalWriter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.db.dump.DumpProperties;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.report.key.ReportKey;
import yogi.server.gui.report.key.ReportKeyManager;
import yogi.server.gui.report.key.io.db.ReportKeyDbReader;

public class ReportKeyDumpDbWriter extends BaseIncrementalWriter<ReportKey>
{	
		public ReportKeyDumpDbWriter(DbResource resource, Selector<? super ReportKey> selector){		
			super(new DbWriter<ReportKey>(resource, new ReportKeyDumpDbFormatter(), ApplicationProperties.InputDataLocation + DumpProperties.getDumpLocation()+ReportKeyDbReader.class.getName()+".dump"), ReportKeyManager.get(), selector);
		}
		
		public ReportKeyDumpDbWriter(DbResource resource) {
			this(resource, null);
		}
}

