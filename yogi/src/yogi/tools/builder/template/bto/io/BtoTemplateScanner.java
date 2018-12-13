package yogi.tools.builder.template.bto.io;

import yogi.tools.builder.template.bto.BtoTemplate;
import yogi.tools.builder.template.bto.BtoTemplateCreator;


public class BtoTemplateScanner implements yogi.base.io.Scanner<BtoTemplate, String> {
	private BtoTemplateCreator creator = new BtoTemplateCreator();

	public BtoTemplateCreator scan(String record) {
		return creator;
	}
}
