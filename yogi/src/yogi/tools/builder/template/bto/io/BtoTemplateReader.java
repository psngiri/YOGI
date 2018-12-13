package yogi.tools.builder.template.bto.io;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.DefaultStringRecordReader;
import yogi.tools.builder.template.bto.BtoTemplate;
import yogi.tools.builder.template.bto.BtoTemplateFactory;
import yogi.tools.builder.template.bto.BtoTemplateValidator;


public class BtoTemplateReader extends DefaultStringRecordReader<BtoTemplate> {
	public static boolean RUN = true;
	public BtoTemplateReader(String fileName) {
		super(fileName);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<BtoTemplate, String>(new BtoTemplateValidator(), new BtoTemplateScanner(), BtoTemplateFactory.get(), new BtoTemplateRecordSelector()));
	}

	public BtoTemplateReader(Collection<String> btoTemplates) {
		super(btoTemplates);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
