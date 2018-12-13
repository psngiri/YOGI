package yogi.server.gui.applicationinfo.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.applicationinfo.ApplicationInfo;
import yogi.server.gui.applicationinfo.ApplicationInfoFactory;
import yogi.server.gui.applicationinfo.ApplicationInfoValidator;


public class ApplicationInfoDbReader extends DefaultDbRecordReader<ApplicationInfo> {
	public static boolean RUN = true;
	public ApplicationInfoDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<ApplicationInfo, DbRecord>(new ApplicationInfoValidator(), new ApplicationInfoDbScanner(), ApplicationInfoFactory.get(), new ApplicationInfoDbRecordSelector()));
	}

	public ApplicationInfoDbReader(Collection<DbRecord> applicationInfos) {
		super(applicationInfos);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
