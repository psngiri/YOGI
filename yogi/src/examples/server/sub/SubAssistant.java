package examples.server.sub;

import yogi.base.relationship.RelationshipAssistant;

public class SubAssistant extends RelationshipAssistant<Sub> {
	private static SubAssistant itsInstance = new SubAssistant();

	public static SubAssistant get() {
		return itsInstance;
	}
}
