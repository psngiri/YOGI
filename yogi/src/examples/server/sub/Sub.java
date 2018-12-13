package examples.server.sub;

import yogi.base.indexing.Index;
import yogi.server.base.version.IVersion;

public interface Sub extends IVersion,Index<Integer>, Comparable<Sub>{
	int getSubNumber();
}
