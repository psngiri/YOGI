package yogi.base.io.link;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Creator;
import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.io.CreatorScanner;
import yogi.base.io.Finder;
import yogi.base.io.delayed.DelayedRecordProcessor;
import yogi.base.validation.Validator;

public class LinkRecordProcessor<T, C extends Creator<T>, R> extends DelayedRecordProcessor<T, C, R> {
	private List<LinkGenerator<C>> linkGenerators = new ArrayList<LinkGenerator<C>>();
	public LinkRecordProcessor(Validator<T> validator, CreatorScanner<T, C, R> scanner, Factory<T> factory) {
		this(validator, scanner, factory, null, null);
	}
	
	public LinkRecordProcessor(Validator<T> validator, CreatorScanner<T, C, R> scanner, Factory<T> factory,
			Selector<R> recordValidator, Finder<T, C> finder) {
		super(validator, scanner, factory, recordValidator, finder);
	}
	
	public void addLinkGenerator(LinkGenerator<C> linkGenerator)
	{
		linkGenerators.add(linkGenerator);
	}
	

	protected void processCreatorModifiers() {
	   processLinking();
	   super.processCreatorModifiers();
	}

	private void processLinking() {
		for(LinkGenerator<C> linkGenerator: linkGenerators)
		   {
			   linkGenerator.generateLinks(creators);
		   }
	}
}
