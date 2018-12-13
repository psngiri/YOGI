package yogi.server.gui.record.base.io.db;

import java.util.Collection;
import java.util.Comparator;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.io.CreatorScanner;
import yogi.base.io.Finder;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.db.QueryReader;
import yogi.base.io.link.LinkGeneratorImpl;
import yogi.base.io.link.LinkRecordProcessor;
import yogi.base.io.resource.db.DbResource;
import yogi.base.relationship.RelationshipObject;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordCreator;
import yogi.server.gui.record.base.BaseRecordValidator;


public abstract class BaseRecordDbReader<K extends RelationshipObject, T extends BaseRecord<K>, C extends BaseRecordCreator<K, T>> extends DefaultDbRecordReader<T> {
	
	public static boolean RUN = true;
	private LinkRecordProcessor<T, C, DbRecord> recordProcessor;
	
	public BaseRecordDbReader(DbResource resource, BaseRecordValidator<K, T> validator, CreatorScanner<T,  C, DbRecord> scanner, Factory<T> factory, Selector<DbRecord> recordSelector, Finder<T, C> finder, LinkGeneratorImpl<C, K> linkGenerator) {
		super(resource);
		setup(validator, scanner, factory, recordSelector, finder, linkGenerator);
	}

	public BaseRecordDbReader(Collection<DbRecord> dbRecords, BaseRecordValidator<K, T> validator, CreatorScanner<T,  C, DbRecord> scanner, Factory<T> factory, Selector<DbRecord> recordSelector, Finder<T, C> finder, LinkGeneratorImpl<C, K> linkGenerator) {
		super(dbRecords);
		setup(validator, scanner, factory, recordSelector, finder, linkGenerator);
	}
	
	protected LinkRecordProcessor<T, C, DbRecord> getRecordProcessor() {
		return recordProcessor;
	}

	private void setup(BaseRecordValidator<K, T> validator, CreatorScanner<T,  C, DbRecord> scanner, Factory<T> factory, Selector<DbRecord> recordSelector, Finder<T, C> finder, LinkGeneratorImpl<C, K> linkGenerator) {
		recordProcessor = new LinkRecordProcessor<T, C, DbRecord>(validator, scanner, factory, recordSelector, finder);
		recordProcessor.addLinkGenerator(linkGenerator);
		recordProcessor.setCreatorComparator(new BaseRecordCreatorComparator<K, T, C>());
		this.addRecordProcessor(recordProcessor);		
	}
	
	private static final class BaseRecordCreatorComparator<K extends RelationshipObject, T extends BaseRecord<K>, C extends BaseRecordCreator<K, T>> implements Comparator<C> {
		public int compare(C from1, C from2) {
			Long timeStamp1 = from1.getTimeStamp();
			Long timeStamp2 = from2.getTimeStamp();
			return timeStamp1.compareTo(timeStamp2);
		}
	}
	
	
	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("tableType", getTableType());
		return queryReader;
	}

	protected abstract String getTableType();
}