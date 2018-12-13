package yogi.base.io;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Implementation;
import yogi.base.Selector;
import yogi.base.app.ErrorReporter;
import yogi.base.util.logging.Logging;
import yogi.base.validation.Validator;

public abstract class BaseRecordProcessor<T, C extends Creator<T>, R>  implements RecordProcessor<T, R>{
	private static Logger logger = Logging.getLogger(BaseRecordProcessor.class);
	private Validator<T> validator;
	private Selector<R> recordSelector;
	private CreatorScanner<T, C, R> scanner;
	private Finder<T, C> finder;
	private Factory<T> factory;
	private int validRecordCount = 0;
	private List<T> objectsCreated;
	private boolean append = false;
	private List<CreatorObjectProcessor<? super T, ? super C>> creatorObjectProcessors = new ArrayList<CreatorObjectProcessor<? super T, ? super C>>();
	private T lastProcessedObject = null;
	private boolean clearFactoryBeforeReading=false;
	
	public BaseRecordProcessor(Validator<T> validator, CreatorScanner<T, C, R> scanner, Factory<T> factory, Selector<R> recordSelector, Finder<T, C> finder) {
		super();
		this.validator = validator;
		this.scanner = scanner;
		this.factory = factory;
		this.finder = finder;
		this.recordSelector = recordSelector;
	}

	public void addCreatorObjectProcessor(CreatorObjectProcessor<? super T, ? super C> creatorObjectProcessor) {
		creatorObjectProcessors.add(creatorObjectProcessor);
	}

	public boolean removeCreatorObjectProcessor(CreatorObjectProcessor<? super T, ? super C> creatorObjectProcessor) {
		return creatorObjectProcessors.remove(creatorObjectProcessor);
	}

	public boolean open()
	{
		if(clearFactoryBeforeReading){
			for (Factory<?> relatedFactory : getRelatedFactories()) {//clear other related factories
				relatedFactory.clear();
			}
			if(factory!=null) factory.clear();
		}
		if(!isAppend() && factory!= null && !factory.isEmpty()) throw new RuntimeException(factory.toString() + " is not empty, so did not read");
		objectsCreated = new ArrayList<T>();
		return true;
	}
	
	public boolean close()
	{
		Validator<T> myValidator = getValidator();
		if(myValidator != null) myValidator.validate();
		String objectName = "Object";
		if(!objectsCreated.isEmpty()) objectName = Implementation.getInterface(objectsCreated.get(0)).getSimpleName();
		if(this.getScanner()!=null) logger.info(String.format("Read %1$s valid records and created %2$s %3$s(s) using %4$s ", validRecordCount, objectsCreated.size(), objectName, this.getScanner().getClass().getSimpleName()));
		objectsCreated = null;
		validRecordCount = 0;
		return true;
	}
	
	public abstract T process(R record);

	protected boolean valid(R record)
	{
		Selector<R> myRecordSelector = getRecordSelector();
		if(myRecordSelector != null && !myRecordSelector.select(record)) return false;
		validRecordCount++;
		return true;
	}
	
	protected T create(C creator) {
		T rtnValue = null;
		if(finder != null)
		{
			rtnValue = finder.find(creator, getLastCreatedObject());
			if(rtnValue != null){
				ErrorReporter.get().fine("Ignoring "+creator.getClass().getSimpleName()+" Record ", creator.toString()+ " because of " + getLastCreatedObject());
				processCreatorObjectProcessors(creator, rtnValue, false);
				return null;
			}
		}
		if(factory == null){
			T object = creator.create();
			if(object == null) return null;
			if (validator == null || validator.validate(object))
			{
				return object;
			}
		}
		rtnValue = factory.create(creator, validator);
		if(rtnValue != null) processCreatorObjectProcessors(creator, rtnValue, true);
		return rtnValue;
	}

	private void processCreatorObjectProcessors(C creator, T object, boolean objectNewlyCreated) {
		for(CreatorObjectProcessor<? super T, ? super C> creatorObjectProcessor: creatorObjectProcessors)
		{
			creatorObjectProcessor.process(creator, object, objectNewlyCreated);
		}
		lastProcessedObject = object;
	}
	
	public Factory<T> getFactory() {
		return factory;
	}
	
	public T getLastCreatedObject() {
		int size = objectsCreated.size();
		if(size == 0) return null;
		return objectsCreated.get(size - 1);
	}
	
	protected T addObject(T object)
	{
		if(object != null) objectsCreated.add(object);
		return object;
	}
	
	public List<T> getObjectsCreated()
	{
		return objectsCreated;
	}
	
	public CreatorScanner<T, C, R> getScanner() {
		return scanner;
	}
	public Validator<T> getValidator() {
		return validator;
	}

	public void setFinder(Finder<T,C> finder) {
		this.finder = finder;
	}

	public void setRecordSelector(Selector<R> recordSelector) {
		this.recordSelector = recordSelector;
	}

	public void setScanner(CreatorScanner<T, C, R> scanner) {
		this.scanner = scanner;
	}

	public void setValidator(Validator<T> validator) {
		this.validator = validator;
	}

	public Selector<R> getRecordSelector() {
		return recordSelector;
	}
	public Finder<T, C> getFinder() {
		return finder;
	}
	
	public int getValidRecordCount() {
		return validRecordCount;
	}

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}
	
	public T getLastProcessedObject() {
		return lastProcessedObject;
	}
	
	protected C scan(R record) {
		try {
			C creator = getScanner().scan(record);
			return creator;
		} catch (RuntimeException e) {
			ErrorReporter.get().error((Object)("Error scanning record: " + record), e);
			throw e;
		}
	}

	public boolean isClearFactoryBeforeReading() {
		return clearFactoryBeforeReading;
	}

	public void setClearFactoryBeforeReading(boolean clearFactoryBeforeReading) {
		this.clearFactoryBeforeReading = clearFactoryBeforeReading;
	}

	protected List<Factory<?>> getRelatedFactories(){
		return new ArrayList<Factory<?>>(0);
	}
	
}
