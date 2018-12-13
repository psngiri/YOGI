package yogi.base.relationship.types.io;

import yogi.base.io.Formatter;
import yogi.base.relationship.types.RelationshipType;

public class RelationshipTypeFormatter implements Formatter<RelationshipType> {

	public String format(RelationshipType relationship) {
		StringBuilder sb = new StringBuilder();
		sb.append(relationship.getIndex()).append(' ');
		sb.append(relationship.getClass()).append(' ');
		sb.append(relationship.getFrom()).append(' ');
		sb.append(relationship.getTo()).append(' ');
		sb.append(relationship.getName()).append(' ');
		sb.append(relationship.isInverse()).append(' ');
		sb.append(relationship.isOneToOne()).append(' ');
		sb.append(relationship.getOtherRelationship());
		return sb.toString();
	}

}
