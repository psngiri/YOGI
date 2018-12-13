package examples.server.market;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;

public interface Market extends RelationshipObject ,Index<String>, Comparable<Market>{
	String getMarket();
}
