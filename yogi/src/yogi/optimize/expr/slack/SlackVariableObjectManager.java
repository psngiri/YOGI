package yogi.optimize.expr.slack;

import yogi.base.relationship.RelationshipManager;

public class SlackVariableObjectManager extends RelationshipManager<SlackVariableObject> {
	private static SlackVariableObjectManager itsInstance = new SlackVariableObjectManager();

	protected SlackVariableObjectManager() {
		super();
	}

	public static SlackVariableObjectManager get() {
		return itsInstance;
	}

	@Override
	protected void buildRelationships(SlackVariableObject slackVariableObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteRelationships(SlackVariableObject slackVariableObject) {
		// TODO Auto-generated method stub
		
	}
}
