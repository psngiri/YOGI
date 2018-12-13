package yogi.tools.builder.template.bto;

import yogi.base.relationship.RelationshipAssistant;

public class BtoTemplateAssistant extends RelationshipAssistant<BtoTemplate> {
	private static BtoTemplateAssistant itsInstance = new BtoTemplateAssistant();

	public static BtoTemplateAssistant get() {
		return itsInstance;
	}
}
