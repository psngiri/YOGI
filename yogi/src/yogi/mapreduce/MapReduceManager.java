package yogi.mapreduce;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import yogi.base.network.ServerCommandExectuorManager;
import yogi.remote.client.app.CommandAdapter;

public class MapReduceManager {
	private static Mapper mapper = new Mapper(ServerCommandExectuorManager.get().getServerCommandExecutors());
	private static MapReduceManager itsInstance = new MapReduceManager();
	private static Map<String, Object> reducers = new HashMap<String, Object>();
	private static Map<String, String> commandReducers = new HashMap<String, String>();
	
	public static MapReduceManager get(){
		return itsInstance;
	}
	
	public static void removeServersFromCluster(String hostColonPortNumbers){
		ServerCommandExectuorManager.removeServersFromCluster(hostColonPortNumbers);
	}
	
	public static void addServersToCluster(String hostColonPortNumbers){
		ServerCommandExectuorManager.addServersToCluster(hostColonPortNumbers);
	}
	
	public static void setCommandReducerMap(String commandColonReducer){
		String[] split = commandColonReducer.split(",");
		for(String token: split)
		{
			String[] split2 = token.split(":");
			commandReducers.put(split2[0], split2[1]);
		}
		
	}

	public <R, C extends CommandAdapter<R>> R execute(C command, Reducer<R> reducer)
	{
		return mapper.execute(command, reducer);
	}
	
	public static boolean isMapReduceCommand(String className){
		return reducers.containsKey(className);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Reducer<T> getReducer(String className){
		Object rtnValue = reducers.get(className);
		if(rtnValue != null) return (Reducer<T>) rtnValue;
		try {
			String reducerClassName = commandReducers.get(className);
			if(reducerClassName == null){
				throw new RuntimeException("Reducer Class name is not mapped for for given Command ClassName" + className + " please set property yogi.mapreduce.MapReduceManager;setCommandReducerMap=Command:Reducer");
			}
			Class<?> reducerClass = Class.forName(reducerClassName);
			Class<?>[] parameterTypes = new Class[] {};
			Constructor<?> constructor = reducerClass.getConstructor(parameterTypes);
			rtnValue = constructor.newInstance(new Object[] {});
			reducers.put(className, rtnValue);
			return (Reducer<T>) rtnValue;
		} catch (Exception e) {
			throw new RuntimeException("Could not create Reducer for given ClassName" + className,e);
		}
	}
}
