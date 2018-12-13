package yogi.server.gui.record.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.db.SingleConnectionDbModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.Record;

public class RecordPurgeWriterModule<T extends Record<?,?>> extends SingleConnectionDbModule {

	private DbResource dbResource;

	private PurgeDbORDbFileWriter<T> writer;
	
	public RecordPurgeWriterModule(DbResource dbResource, PurgeDbORDbFileWriter<T> writer) {
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
