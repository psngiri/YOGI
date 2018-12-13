package yogi.tools.builder.template.bto.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.tools.builder.template.bto.BtoTemplate;
import yogi.tools.builder.template.bto.BtoTemplateFactory;
import yogi.tools.builder.template.bto.BtoTemplateValidator;


public class BtoTemplateDbReader extends DefaultDbRecordReader<BtoTemplate> {
	public static boolean RUN = true;
	public BtoTemplateDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<BtoTemplate, DbRecord>(new BtoTemplateValidator(), new BtoTemplateDbScanner(), BtoTemplateFactory.get(), new BtoTemplateDbRecordSelector()));
	}

	public BtoTemplateDbReader(Collection<DbRecord> btoTemplates) {
		super(btoTemplates);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
