package examples.server.fare.farekey;

import yogi.base.relationship.RelationshipAssistant;
/**
 * @author Vikram Vadavala
 *
 */
public class FareKeyAssistant extends RelationshipAssistant<FareKey> {
	private static FareKeyAssistant itsInstance = new FareKeyAssistant();

	public static FareKeyAssistant get() {
		return itsInstance;
	}
}
