package yogi.tools.builder.template.bto;

import yogi.base.Creator;

public class BtoTemplateCreator implements Creator<BtoTemplate> {
	public BtoTemplate create() {
		return new BtoTemplateImpl();
	}
}
