package yogi.optimize.sm.io;

import java.util.Collection;
import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.io.DefaultStringRecordReader;
import yogi.base.io.link.LinkRecordProcessor;
import yogi.optimize.sm.Sm;
import yogi.optimize.sm.SmFactory;
import yogi.optimize.sm.SmValidator;

public class SmReader extends DefaultStringRecordReader<Sm> {
	private LinkRecordProcessor<Sm, SMVariableCreator, String> linkRecordProcessor;

	public SmReader(String ipSolutionFileName) {
		super(ipSolutionFileName, 1);
		setup();
	}

	private void setup() {
		linkRecordProcessor = new LinkRecordProcessor<Sm, SMVariableCreator, String>(new SmValidator(), new SmScanner(), SmFactory.get(), null, null);
		linkRecordProcessor.addLinkGenerator(new SmLinkGenerator());
		this.addRecordProcessor(linkRecordProcessor);
	}

	public SmReader(Collection<String> ipSolutionItems) {
		super(ipSolutionItems);
		setup();
	}

	@Override
	public List<Sm> read() {
		List<Sm> rtnValue = super.read();
		if(linkRecordProcessor.getValidRecordCount() == 0) ErrorReporter.get().error("Empty " + this.getRecordReader().getResource().getName());
		return rtnValue;
	}
	
}
