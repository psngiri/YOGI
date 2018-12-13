package yogi.property.alias;
import yogi.base.indexing.Index;
import yogi.property.Property;

public interface PropertyAlias extends Property, Index<String>{
	String getAlias();
}
