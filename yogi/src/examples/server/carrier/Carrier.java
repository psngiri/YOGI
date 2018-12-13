package examples.server.carrier;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;

public interface Carrier extends RelationshipObject, Index<String>, Comparable<Carrier>
{
	String getCarrierCode();
}