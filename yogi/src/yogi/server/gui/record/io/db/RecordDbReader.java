package yogi.server.gui.record.io.db;

import java.util.Collection;
import java.util.Comparator;

import yogi.base.Factory;
import yogi.base.io.CreatorScanner;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.link.LinkRecordProcessor;
import yogi.base.io.resource.db.DbResource;
import yogi.base.validation.Validator;
import yogi.server.gui.record.Record;
import yogi.server.gui.record.RecordCreator;
import yogi.server.gui.record.data.RecordData;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;

/**
 * @author Vikram Vadavala
 *
 */
public abstract class RecordDbReader<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> extends DefaultDbRecordReader<T> {
	
	public static boolean RUN = true;
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	public RecordDbReader(DbResource resource, KeyManager<K> keyManager, Factory<T> factory, CreatorScanner<T, C, DbRecord> scanner, Validator<T> validator) {
		super(resource);
		setup(keyManager, factory, scanner, validator);
	}
	
	public RecordDbReader(Collection<DbRecord> records, KeyManager<K> keyManager, Factory<T> factory, CreatorScanner<T, C, DbRecord> scanner, Validator<T> validator) {
		super(records);
		setup(keyManager, factory, scanner, validator);
	}
	
	private void setup(KeyManager<K> keyManager, Factory<T> factory, CreatorScanner<T, C, DbRecord> scanner, Validator<T> validator) {
		LinkRecordProcessor<T, C, DbRecord> recordProcessor = new LinkRecordProcessor<T, C, DbRecord>(validator, scanner, factory, new RecordSelector(), null);
		this.addRecordProcessor(recordProcessor);
		recordProcessor.addLinkGenerator(new RecordLinkGenerator<K, D, T, C>(keyManager));
		setup(recordProcessor);
		recordProcessor.setCreatorComparator(new RecordCreatorComparator<K, D, T, C>());
	}
		
	protected void setup(LinkRecordProcessor<T, C, DbRecord> recordProcessor) {
	}

	private static final class RecordCreatorComparator<K extends Key, D extends RecordData, T extends Record<K, D>, C extends RecordCreator<K, D, T>> implements Comparator<C> {
		@Override
		public int compare(C from1, C from2) {
			int result = from1.getUser().compareTo(from2.getUser());
			if(result != 0) return result;
			result = from1.getIdName().compareTo(from2.getIdName());
			if(result != 0) return result;
			result = from1.getPartition().compareTo(from2.getPartition());
			if(result != 0) return result;
			Long timeStamp1 = from1.getTimeStamp();
			Long timeStamp2 = from2.getTimeStamp();
			return timeStamp1.compareTo(timeStamp2);
		}
	}

}
