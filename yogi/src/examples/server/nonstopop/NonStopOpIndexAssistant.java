package examples.server.nonstopop;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.index.IndexAssistant;

public class NonStopOpIndexAssistant extends IndexAssistant {
	private static NonStopOpIndexAssistant itsInstance = new NonStopOpIndexAssistant(1589, new Range<Integer>(1, 9));

	public static NonStopOpIndexAssistant get() {
		return itsInstance;
	}

	protected NonStopOpIndexAssistant(int recordLength, Range<Integer> range) {
		super(recordLength, range);
	}

}
