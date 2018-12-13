package yogi.property.parameter.io;

import java.util.Collection;

import yogi.base.io.DefaultStringRecordReader;
import yogi.property.Property;
import yogi.property.io.PropertyRecordProcessor;
import yogi.property.parameter.ParameterValidator;

public class ParameterReader extends DefaultStringRecordReader<Property> {
    public static boolean RUN = true;
	@Override
	public boolean isActivated() {
		return RUN;
	}

	public ParameterReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new PropertyRecordProcessor(new ParameterValidator(), new ParameterScanner()));
	}

	public ParameterReader(Collection<String> parameters) {
		super(parameters);
		setup();
	}
}