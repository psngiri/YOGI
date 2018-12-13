package yogi.server.gui.record.base.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.base.BaseRecord;

public class BaseRecordPurgeWriterModule<T extends BaseRecord<?>> extends SingleConnectionDbModule {

	private DbResource dbResource;

	private PurgeDbORDbFileWriter<T> writer;
	
	public BaseRecordPurgeWriterModule(DbResource dbResource, PurgeDbORDbFileWriter<T> writer) {
		super(dbResource);
		this.dbResource = dbResource;
		this.writer = writer;
	}

	@Override
	public boolean isActivated() {
		return dbResource==null ? false : true;
	}

	public void setup() {
		this.addWriter(writer);
	}

}
