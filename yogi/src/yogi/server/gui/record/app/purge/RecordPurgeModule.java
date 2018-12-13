package yogi.server.gui.record.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;


public abstract class RecordPurgeModule<K extends Key,D extends RecordData, T extends Record<K,D>> extends BaseModule {
	public static boolean RUN = true;
	private DbResource dbResource;
	private long timestamp;

	public RecordPurgeModule(DbResource dbResource, long timestamp) {
		super();
		this.dbResource=dbResource;
		this.timestamp = timestamp;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		this.addProcessor(new RecordMarkPurgeProcessor<K, D, T>(getKeyManager(), getRecordManager(), timestamp));//marks all the FBCMappings that can be purged
		this.addProcessor(new RecordPurgeWriterModule<T>(dbResource, getWriter(dbResource)));//deletes the above marked FBCMappings from database
		this.addProcessor(new RecordPurgeProcessor<K, D, T>(getKeyManager(), getRecordManager()));//deletes the above marked FBCMappings from memory
	}

	protected abstract PurgeDbORDbFileWriter<T> getWriter(DbResource dbResource);

	protected abstract RecordManager<K, D, T> getRecordManager();
	
	protected abstract KeyManager<K> getKeyManager();

}
