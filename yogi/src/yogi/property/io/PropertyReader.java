package yogi.property.io;

import java.util.Collection;

import yogi.base.io.DefaultStringRecordReader;
import yogi.property.Property;

public class PropertyReader extends DefaultStringRecordReader<Property> {
	public PropertyReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new PropertyRecordProcessor());
	}

	public PropertyReader(Collection<String> properties) {
		super(properties);
		setup();
	}
}