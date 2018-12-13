package yogi.cluster;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.Factory;
import yogi.base.Implementation;
import yogi.base.network.ServerCommandExectuorManager;
import yogi.remote.server.CommandServerImpl;

public class ClusterManager {
	private static boolean Cluster = false;
	private boolean isPrimary = false;
	private static Map<Class<?>,ClusterFactory<?>> clusteredFactories = new HashMap<Class<?>,ClusterFactory<?>>();
	private static Map<ClusterFactory<?>, ClusterFactoryListener<?>> clusteredFactoryListeners = new HashMap<ClusterFactory<?>, ClusterFactoryListener<?>>();
	private static Map<Class<?>,Constructor<?>> clusteredFactoryCommands = new HashMap<Class<?>,Constructor<?>>();
	private static Map<Class<?>,Constructor<?>> clusteredFactoryListCommands = new HashMap<Class<?>,Constructor<?>>();
	private static ClusterController clusterController;
	private static ClusterManager itsInstance = new ClusterManager();
	
	public ClusterManager() {
		super();
		clusterController = new DelayedClusterController(ServerCommandExectuorManager.get().getServerCommandExecutors(), 5);
	}

	public static boolean isCluster() {
		return Cluster;
	}

	public static void setPrimary(String isPrimary){
		Boolean valueOf = Boolean.valueOf(isPrimary);
		if(ClusterManager.get().isPrimary) {
			ClusterManager.get().process(new SwitchPrimaryCommand(false));
		}
		ClusterManager.get().flush();
		ClusterManager.get().isPrimary = valueOf;
	}
	
	public void flush(){
		clusterController.flush();
	}
	
	public static void cluster(String cluster){
		Cluster = Boolean.valueOf(cluster);
		if(Cluster){
			clusterController.start();
		}else{
			clusterController.stop();
		}
	}
	
	public static void clusterEverySeconds(String clusterEveryseconds)
	{
		clusterController.stop();
		int value = Integer.parseInt(clusterEveryseconds);
		if(value == 0){
			clusterController = new ImmediateClusterController(ServerCommandExectuorManager.get().getServerCommandExecutors());
		}else{
			clusterController = new DelayedClusterController(ServerCommandExectuorManager.get().getServerCommandExecutors(), value);
		}
		clusterController.start();
	}
	
	public static void removeServersFromCluster(String hostColonPortNumbers){
		ServerCommandExectuorManager.removeServersFromCluster(hostColonPortNumbers);
	}
	
	public static void addServersToCluster(String hostColonPortNumbers){
		ServerCommandExectuorManager.addServersToCluster(hostColonPortNumbers);
	}
	
	public static void resendCommands(String value){
		if(clusterController != null){
			ClusterCommand command = null;
			clusterController.process(command);
		}
	}
	
	public static ClusterManager get(){
		return itsInstance;
	}
			
	public static void removeFactoryClassNamesFromClustering(String value){
		if(value.trim().equalsIgnoreCase("All")){
			clusteredFactories.clear();
			clusteredFactoryListeners.clear();
		}else{
			String[] split = value.split(",");
			for(String token: split)
			{
				Factory<?> factory = clusteredFactories.remove(token);
				clusteredFactoryListeners.remove(factory);
			}
		}
	}
	
	public static void addFactoryClassNamesForClustering(String value){
		String[] split = value.split(",");
		for(String token: split)
		{
			try {
				Class<?> c = Class.forName(token);
				Class<?>[] parameterTypes = new Class[] {};
				Method method = c.getMethod("get", parameterTypes);
				Object[] arguments = new Object[] {};
				ClusterFactory<?> factory = (ClusterFactory<?>) method.invoke(null, arguments);
				ClusterManager.get().addFactoryForClusteing(factory);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public <T> void addFactoryForClusteing(ClusterFactory<T> clusterFactory) {
		@SuppressWarnings("unchecked")
		Class<ClusterFactory<T>> klass = (Class<ClusterFactory<T>>) clusterFactory.getClass();
		if(ClusterManager.clusteredFactories.get(klass) == null){
			ClusterFactoryListener<T> factoryListener = new MyClusterFactoryListener<T>(klass);
			clusterFactory.addClusterFactoryListener(factoryListener);
			ClusterManager.clusteredFactories.put(klass, clusterFactory);
			clusteredFactoryListeners.put(clusterFactory, factoryListener);
		}
	}

	private static Constructor<?> getCommandConstructor(Class<?> klass){
		String className = klass.getSimpleName().replaceAll("Factory", "ClusterCommand");
		String factoryClusterCommandClassName = klass.getPackage().getName()+".command."+ className;
		try {
			Class<?> commandClass = Class.forName(factoryClusterCommandClassName);
			Class<?>[] parameterTypes = new Class[] {List.class};
			Constructor<?> constructor = commandClass.getConstructor(parameterTypes);
			clusteredFactoryListCommands.put(klass, constructor);
			return constructor;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("The factory : " + klass.getName() + " being clustered does not have : " + factoryClusterCommandClassName + " defined.",e);
		} catch (Exception e) {
			throw new RuntimeException(factoryClusterCommandClassName + " doesn't have constructor that takes a list of objects : " + " defined.",e);
		}
	}
	
	private static Constructor<?> getCommandConstructor(Class<?> klass, Object object){
		String className = klass.getSimpleName().replaceAll("Factory", "ClusterCommand");
		String factoryClusterCommandClassName = klass.getPackage().getName()+".command."+ className;
		try {
			Class<?> commandClass = Class.forName(factoryClusterCommandClassName);
			Class<?>[] parameterTypes = new Class[] {Implementation.getInterface(object)};
			Constructor<?> constructor;
			constructor = commandClass.getConstructor(parameterTypes);
			clusteredFactoryCommands.put(klass, constructor);
			return constructor;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("The factory : " + klass.getName() + " being clustered does not have : " + factoryClusterCommandClassName + " defined.",e);
		} catch (Exception e) {
			throw new RuntimeException(factoryClusterCommandClassName + " doesn't have constructor that takes the object : " + object.getClass() + " defined.",e);
		}
	}

	private <T> void process(Class<ClusterFactory<T>> klass , List<T> objects){
		if(isClusterable()){
			Constructor<?> constructor = clusteredFactoryListCommands.get(klass);
			if(constructor == null){
				constructor = ClusterManager.getCommandConstructor(klass);
			}
			Object[] arguments = new Object[] {objects};
			try {
				ClusterCommand command = (ClusterCommand) constructor.newInstance(arguments);
				process(command);
			} catch (Exception e) {
				throw new RuntimeException("Could not create a new instatnce of ClusterCommand for : "+ objects.getClass());
			}
		}
	}

	private <T> void process(Class<ClusterFactory<T>> klass , T object){
		if(isClusterable()){
			Constructor<?> constructor = clusteredFactoryCommands.get(klass);
			if(constructor == null){
				constructor = ClusterManager.getCommandConstructor(klass, object);
			}
			Object[] arguments = new Object[] {object};
			try {
				ClusterCommand command = (ClusterCommand) constructor.newInstance(arguments);
				process(command);
			} catch (Exception e) {
				throw new RuntimeException("Could not create a new instatnce of ClusterCommand for : "+ object.getClass());
			}
		}
	}
	
	public void process(ClusterCommand command) {
		if(isClusterable()){
			clusterController.process(command);
		}
	}

	private boolean isClusterable() {
		if(!CommandServerImpl.isServerAvailable()) return false;
		return Cluster;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> Factory<T> getFactory(Class<?> klass)
	{
		return (Factory<T>) clusteredFactories.get(klass);
	}
	
	private static class MyClusterFactoryListener<T> implements ClusterFactoryListener<T>{
		private Class<ClusterFactory<T>> klass;
		
		public MyClusterFactoryListener(Class<ClusterFactory<T>> klass) {
			super();
			this.klass = klass;
		}

		@Override
		public void add(List<T> objects) {
			if(ClusterManager.get().isPrimary){
				ClusterManager.get().process(klass, objects);
			}
		}

		@Override
		public void add(T object) {
			if(ClusterManager.get().isPrimary){
				ClusterManager.get().process(klass, object);
			}
		}
		
	}
}
