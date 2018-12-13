package yogi.server.gui.record.base.io.db;

import java.util.Collection;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.app.TimeWindowPauseLoopChecker;
import yogi.base.io.CreatorScanner;
import yogi.base.io.Finder;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.QueryReader;
import yogi.base.io.link.LinkGeneratorImpl;
import yogi.base.io.resource.db.DbResource;
import yogi.base.relationship.RelationshipObject;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.gui.record.base.BaseRecord;
import yogi.server.gui.record.base.BaseRecordCreator;
import yogi.server.gui.record.base.BaseRecordManager;
import yogi.server.gui.record.base.BaseRecordValidator;


public abstract class BaseRecordIncrementalDbReader<K extends RelationshipObject, T extends BaseRecord<K>, C extends BaseRecordCreator<K, T>> extends BaseRecordDbReader<K, T, C> {
	
	public static boolean RUN = true;
	private TimeWindowPauseLoopChecker loopChecker;
	private BaseRecordManager<K, T> manager;
	
	public BaseRecordIncrementalDbReader(DbResource resource, BaseRecordValidator<K, T> validator, CreatorScanner<T,  C, DbRecord> scanner, Factory<T> factory, BaseRecordManager<K, T> manager, Selector<DbRecord> recordSelector, Finder<T, C> finder, LinkGeneratorImpl<C, K> linkGenerator, TimeWindowPauseLoopChecker loopChecker) {
		super(resource, validator, scanner, factory, recordSelector, finder, linkGenerator);
		setup(loopChecker, manager);
	}

	public BaseRecordIncrementalDbReader(Collection<DbRecord> dbRecords, BaseRecordValidator<K, T> validator, CreatorScanner<T,  C, DbRecord> scanner, Factory<T> factory, BaseRecordManager<K, T> manager, Selector<DbRecord> recordSelector, Finder<T, C> finder, LinkGeneratorImpl<C, K> linkGenerator, TimeWindowPauseLoopChecker loopChecker) {
		super(dbRecords, validator, scanner, factory, recordSelector, finder, linkGenerator);
		setup(loopChecker, manager);
	}

	private void setup(TimeWindowPauseLoopChecker loopChecker, BaseRecordManager<K, T> manager) {
		this.loopChecker = loopChecker;
		getRecordProcessor().setAppend(true);
		this.manager = manager;
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
	
	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("tableType", getTableType());
		ImmutableList<T> findAll = manager.findAll();
		long lastExecutionTime = 0;
		if(!findAll.isEmpty()) lastExecutionTime = findAll.get(findAll.size() -1).getTimeStamp() + 1;
		queryReader.addVariable("lastExecutionTime", String.valueOf(lastExecutionTime));
		queryReader.addVariable("endExecutionTime", String.valueOf(loopChecker.getEndExecutionTime()));
		return queryReader;
	}
}