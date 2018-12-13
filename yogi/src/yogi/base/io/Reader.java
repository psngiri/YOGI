package yogi.base.io;

import java.util.List;

import yogi.base.app.ActivationState;

public interface Reader<T>  extends ActivationState{
	List<T> read();
}
