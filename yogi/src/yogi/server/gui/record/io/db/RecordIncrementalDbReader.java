package yogi.server.gui.record.io.db;

import java.util.Collection;

import yogi.base.Factory;
import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.CreatorScanner;
import yogi.base.io.db.DbRecord;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.record.RecordValidator;
import yogi.server.gui.record.base.io.db.BaseRecordIncrementalDbReader;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;


public class RecordIncrementalDbReader<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> extends BaseRecordIncrementalDbReader<K, T, C> {
	
	public static boolean RUN = true;
	
	public RecordIncrementalDbReader(Collection<DbRecord> dbRecords, TimeWindowPauseLoopChecker loopChecker,RecordManager<K,D,T> manager, KeyManager<K> keyManager, Factory<T> factory, CreatorScanner<T, C, DbRecord> scanner, RecordValidator<K,D,T> validator) {
		super(dbRecords, validator, scanner, factory, manager, new RecordSelector(), null, new RecordLinkGenerator<K, D, T, C>(keyManager), loopChecker);
	}

	public RecordIncrementalDbReader(DbResource resource, TimeWindowPauseLoopChecker loopChecker,RecordManager<K,D,T> manager, KeyManager<K> keyManager, Factory<T> factory, CreatorScanner<T, C, DbRecord> scanner, RecordValidator<K,D,T> validator) {
		super(resource, validator, scanner, factory, manager, new RecordSelector(), null, new RecordLinkGenerator<K, D, T, C>(keyManager), loopChecker);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	protected String getTableType() {
		return "TableType";
	}
	
}