package yogi.property.alias.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;
import yogi.property.alias.PropertyAlias;
import yogi.property.alias.PropertyAliasFactory;
import yogi.property.alias.PropertyAliasValidator;

public class PropertyAliasReader extends DefaultStringRecordReader<PropertyAlias> {
	public static String FileName = "*/config/propertyAlias.dat";
	public static boolean RUN = true;
	
	public PropertyAliasReader() {
		this(FileName);
	}
	
	public PropertyAliasReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<PropertyAlias, String>(new PropertyAliasValidator(), new PropertyAliasScanner(), PropertyAliasFactory.get(), new PropertyAliasSelector()));
	}

	public PropertyAliasReader(Collection<String> propertyAliases) {
		super(propertyAliases);
		setup();
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

}
