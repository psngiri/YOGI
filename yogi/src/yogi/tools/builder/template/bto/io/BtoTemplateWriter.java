package yogi.tools.builder.template.bto.io;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.FileWriter;
import yogi.tools.builder.template.bto.BtoTemplate;
import yogi.tools.builder.template.bto.BtoTemplateFactory;

public class BtoTemplateWriter extends FactoryWriter<BtoTemplate> {
	public static boolean RUN = true;
	public BtoTemplateWriter(String fileName, Selector<? super BtoTemplate> selector) {
		super(BtoTemplateFactory.get(), new FileWriter<BtoTemplate>(fileName, new BtoTemplateFormatter()),
				selector);
	}

	public BtoTemplateWriter(String fileName) {
		this(fileName, null);
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}
}
