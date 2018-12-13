package yogi.tools.builder.template.bto;

import yogi.base.relationship.RelationshipManager;

public class BtoTemplateManager extends RelationshipManager<BtoTemplate> {
	private static BtoTemplateManager itsInstance = new BtoTemplateManager();

	protected BtoTemplateManager() {
		super();
	}

	public static BtoTemplateManager get() {
		return itsInstance;
	}

	@Override
	protected void buildRelationships(BtoTemplate btoTemplate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteRelationships(BtoTemplate btoTemplate) {
		// TODO Auto-generated method stub
		
	}
}
