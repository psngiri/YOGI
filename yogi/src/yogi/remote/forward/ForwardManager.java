package yogi.remote.forward;

import java.util.HashSet;
import java.util.Set;

public class ForwardManager {
	private static ForwardManager itsInstance = new ForwardManager();
	private static Set<String> forwardServerTypes = new HashSet<String>();
	
	public static ForwardManager get(){
		return itsInstance;
	}
	
	public static void setForwardServerType(String serverTypesComaSeparated){
		forwardServerTypes.clear();
		String[] split = serverTypesComaSeparated.split(",");
		for(String serverType: split){
			forwardServerTypes.add(serverType);
		}
	}
	
	public boolean forward(String serverType){
		if(forwardServerTypes.isEmpty()) return false;
		return forwardServerTypes.contains(serverType);
	}
}
