package yogi.base.io.link;

import java.util.List;

public interface LinkGenerator<T> {
	void generateLinks(List<T> fromObjects);
}
