package yogi.base.app;

import java.io.Serializable;


public interface Command<R> extends Serializable{
	R execute();
	String getID();
	String getUserId();
}
