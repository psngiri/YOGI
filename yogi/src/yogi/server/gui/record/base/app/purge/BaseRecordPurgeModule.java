package yogi.server.gui.record.base.app.purge;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.Manager;
import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.base.relationship.RelationshipObject;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordManager;


public abstract class BaseRecordPurgeModule<K extends RelationshipObject, T extends BaseRecord<K>> extends BaseModule {
	public static boolean RUN = true;
	private DbResource dbResource;
	private long timestamp;

	public BaseRecordPurgeModule(DbResource dbResource, long timestamp) {
		super();
		this.dbResource=dbResource;
		this.timestamp = timestamp;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	public void setup() {
		this.addProcessor(new BaseRecordMarkPurgeProcessor<K, T>(getKeyManager(), getRecordManager(), timestamp));//marks all the FBCMappings that can be purged
		this.addProcessor(new BaseRecordPurgeWriterModule<T>(dbResource, getWriter(dbResource)));//deletes the above marked FBCMappings from database
		this.addProcessor(new BaseRecordPurgeProcessor<K, T>(getRecordManager()));//deletes the above marked FBCMappings from memory
	}

	protected abstract PurgeDbORDbFileWriter<T> getWriter(DbResource dbResource);

	protected abstract BaseRecordManager<K, T> getRecordManager();
	
	protected abstract Manager<K> getKeyManager();

}
