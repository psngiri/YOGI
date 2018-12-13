package yogi.report.server.command;

import java.io.Serializable;

public class ImportReturn implements Serializable{
private String msg;

public ImportReturn(String msg) {
	super();
	this.msg = msg;
};

}
