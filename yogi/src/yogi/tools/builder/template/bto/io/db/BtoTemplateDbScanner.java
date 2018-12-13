package yogi.tools.builder.template.bto.io.db;

import yogi.base.io.Scanner;
import yogi.base.io.db.DbRecord;
import yogi.tools.builder.template.bto.BtoTemplate;
import yogi.tools.builder.template.bto.BtoTemplateCreator;


public class BtoTemplateDbScanner implements Scanner<BtoTemplate, DbRecord> {
	private BtoTemplateCreator creator = new BtoTemplateCreator();

	public BtoTemplateCreator scan(DbRecord dbRecord) {
		return creator;
	}
}
