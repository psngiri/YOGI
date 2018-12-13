package examples.airport;

import yogi.base.indexing.Index;
import yogi.base.relationship.RelationshipObject;

public interface Airport extends RelationshipObject, Index<String>,Comparable<Airport>{
  String getCode();
}
