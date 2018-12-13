package yogi.tools.builder.template.bto;

import yogi.base.Factory;

public class BtoTemplateFactory extends Factory<BtoTemplate> {
	private static BtoTemplateFactory itsInstance = new BtoTemplateFactory(BtoTemplateManager.get());

	protected BtoTemplateFactory(BtoTemplateManager manager) {
		super(manager);
	}

	public static BtoTemplateFactory get() {
		return itsInstance;
	}
}
