package yogi.base.io.delayed;

import java.util.List;

public interface Modifier<C> {
	List<C> modify(C object);
	List<C> modify(List<C> objects);
}
