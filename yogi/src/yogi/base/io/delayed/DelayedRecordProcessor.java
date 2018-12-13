package yogi.base.io.delayed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.app.ErrorReporter;
import yogi.base.io.BaseRecordProcessor;
import yogi.base.io.CreatorScanner;
import yogi.base.io.Finder;
import yogi.base.validation.Validator;

public class DelayedRecordProcessor<T, C extends Creator<T>, R> extends BaseRecordProcessor<T, C, R> {
	protected List<C> creators = new ArrayList<C>();
	private Modifier<C> creatorModifier;
	private C lastProcessedCreator = null;
	private Comparator<C> creatorComparator;
	
	public DelayedRecordProcessor(Validator<T> validator, CreatorScanner<T, C, R> scanner, Factory<T> factory) {
		this(validator, scanner, factory, null, null);
	}
	
	public DelayedRecordProcessor(Validator<T> validator, CreatorScanner<T, C, R> scanner, Factory<T> factory,
			Selector<R> recordValidator, Finder<T, C> finder) {
		super(validator, scanner, factory, recordValidator, finder);
	}
	
	public T process(R record)
	{
		if(!valid(record)) return null;
		C processRecordCreator = processRecord(record);
		checkIfPropertCreatorIsUsed(processRecordCreator);
		lastProcessedCreator = processRecordCreator;
		return null;
	}

	private void checkIfPropertCreatorIsUsed(C processRecordCreator) {
		if(lastProcessedCreator != null && lastProcessedCreator == processRecordCreator)
		{
			ErrorReporter.get().error("Same Creator used for processing different records, Please use a different instance of Creator for each record in Scanner.");
		}
	}

	private C processRecord(R record) {
		C creator = scan(record);
		if(creator == null) return null;
		if(creators == null) creators = new ArrayList<C>();
		creators.add(creator);
		return creator;
	}

	public C getLastProcessedCreator() {
		return lastProcessedCreator;
	}
	
	@Override
	public List<T> getObjectsCreated() {
		if(creators != null) processCreatorModifiers();
		return super.getObjectsCreated();
	}

	protected void processCreatorModifiers() {
	   if(creatorModifier != null) creators = creatorModifier.modify(creators);
	   if(creatorComparator != null) Collections.sort(creators, creatorComparator);
	   for(C creator: creators)
	   {
		   if(creatorModifier == null)
		   {
			   addObject(create(creator));
		   }else
		   {
			   for(C myCreator: creatorModifier.modify(creator))
			   {
				   addObject(create(myCreator));
			   }
		   }
	   }
	   creators = null; 
	}

	public Modifier<C> getCreatorModifier() {
		return creatorModifier;
	}

	public void setCreatorModifier(Modifier<C> creatorModifier) {
		this.creatorModifier = creatorModifier;
	}

	public Comparator<C> getCreatorComparator() {
		return creatorComparator;
	}

	public void setCreatorComparator(Comparator<C> creatorComparator) {
		this.creatorComparator = creatorComparator;
	}

}
