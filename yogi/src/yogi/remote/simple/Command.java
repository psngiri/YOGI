package yogi.remote.simple;

import java.io.Serializable;


public interface Command extends Serializable{
	Object execute();
	String getID();
}
