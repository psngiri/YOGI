package yogi.base.app.multithread.rdc.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import yogi.base.app.multithread.rdc.RDCCallable;
import yogi.base.app.multithread.rdc.RDCCaller;
import yogi.base.app.multithread.rdc.RDCList;
import yogi.base.app.multithread.rdc.RDCListIterator;
import yogi.base.app.multithread.rdc.command.RDCCallerCommand;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.network.ServerCommandExectuorManager;
import yogi.property.PropertyManager;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;

import junit.framework.TestCase;

public class RDCCallerTestServer extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}

	public void testSingleThread(){
		
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++){
			items.add(i);
		}
		Class<MyRDCCallable> class1 = MyRDCCallable.class;
		RDCCaller.NumberOfThreads = 1;
		RDCCaller<Integer, String, MyRDCCallable> rdcCaller = new RDCCaller<Integer, String, MyRDCCallable>();
		Collection<String> returnValues = rdcCaller.call(items, class1, "Name");
		System.out.println(returnValues);
	}
	
	public void testMultiThread(){
		
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++){
			items.add(i);
		}
		Class<MyRDCCallable> class1 = MyRDCCallable.class;
		RDCCaller.NumberOfThreads = 2;
		RDCCaller<Integer, String, MyRDCCallable> rdcCaller = new RDCCaller<Integer, String, MyRDCCallable>();
		Collection<String> returnValues = rdcCaller.call(items, class1, "Name");
		System.out.println(returnValues);
	}
	
	public void testMultiThreadError(){
		
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++){
			items.add(i);
		}
		Class<MyErrorRDCCallable> class1 = MyErrorRDCCallable.class;
		RDCCaller.NumberOfThreads = 2;
		RDCCaller<Integer, String, MyErrorRDCCallable> rdcCaller = new RDCCaller<Integer, String, MyErrorRDCCallable>();
		Collection<String> returnValues = rdcCaller.call(items, class1, "Name");
		System.out.println(returnValues);
	}
	
	public void testDistributedCaller(){
		ServerCommandExectuorManager.addServersToCluster("localhost:6080,localhost:6081,localhost:6082");
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 50; i ++){
			items.add(i);
		}
		Class<MyRDCCallable> class1 = MyRDCCallable.class;
		RDCCaller.NumberOfThreads = 0;
		RDCCaller<Integer, String, MyRDCCallable> rdcCaller = new RDCCaller<Integer, String, MyRDCCallable>();
		RDCList<Integer, String> rlist = rdcCaller.call("test", items, class1, "Name");
		System.out.println(rlist.getReturnValues());
	}
	
	public void testDistributedIteratorCaller(){
		ServerCommandExectuorManager.addServersToCluster("localhost:6080,localhost:6081,localhost:6082");
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 50; i ++){
			items.add(i);
		}
		Class<MyRDCCallable> class1 = MyRDCCallable.class;
		RDCCaller.NumberOfThreads = 2;
		RDCCaller<Integer, String, MyRDCCallable> rdcCaller = new RDCCaller<Integer, String, MyRDCCallable>();
		RDCList<Integer, String> rlist = rdcCaller.call("test", items, class1, "Name");
		Iterator<String> iterator = rlist.getReturnValuesIterator();
		while(iterator .hasNext()){
			System.out.println(iterator.next());
			System.out.println(rlist.getCompleted()+"/"+rlist.size());
		}
	}
	
	public void testDistributedIteratorCallerError(){
//		ServerCommandExectuorManager.addServersToCluster("localhost:6080,localhost:6081,localhost:6082");
		ArrayList<Integer> items = new ArrayList<Integer>();
		for(int i = 0; i < 10; i ++){
			items.add(i);
		}
		Class<MyErrorRDCCallable> class1 = MyErrorRDCCallable.class;
		RDCCaller.NumberOfThreads = 2;
		RDCCaller<Integer, String, MyErrorRDCCallable> rdcCaller = new RDCCaller<Integer, String, MyErrorRDCCallable>(4);
		RDCList<Integer, String> rlist = rdcCaller.call("test", items, class1, "Name");
		Iterator<String> iterator = rlist.getReturnValuesIterator();
		try {
			while(iterator .hasNext()){
				System.out.println(iterator.next());
				System.out.println(rlist.getCompleted()+"/"+rlist.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		public void testDistributed() throws CommandException{
			ArrayList<Integer> items = new ArrayList<Integer>();
			for(int i = 0; i < 50; i ++){
				items.add(i);
			}
			MyRDCList<Integer, String> rlist = new MyRDCList<Integer, String>(items);
			Class<MyRDCCallable> class1 = MyRDCCallable.class;
			RDCCallerCommand<Integer, String, MyRDCCallable> command = new RDCCallerCommand<Integer, String, MyRDCCallable>("test", rlist.getRDCListIterator(), class1, "Name");			
			String serverAddress = "localhost:6080";
			CommandExecutor commandExecutor = new CommandExecutor(serverAddress, serverAddress);
			commandExecutor.execute(command);
			ArrayList<String> returnValues = rlist.getReturnValues();
			System.out.println(returnValues);
		}
		
		public void testDistributedIterator() throws CommandException{
			ServerCommandExectuorManager.addServersToCluster("localhost:6080");
			ArrayList<Integer> items = new ArrayList<Integer>();
			for(int i = 0; i < 50; i ++){
				items.add(i);
			}
			MyRDCList<Integer, String> rlist = new MyRDCList<Integer, String>(items);
			Class<MyRDCCallable> class1 = MyRDCCallable.class;
			RDCCallerCommand<Integer, String, MyRDCCallable> command = new RDCCallerCommand<Integer, String, MyRDCCallable>("test", rlist.getRDCListIterator(), class1, "Name");
			String serverAddress = "localhost:6080";
			CommandExecutor commandExecutor = new CommandExecutor(serverAddress, serverAddress);
			commandExecutor.execute(command);
			Iterator<String> iterator = rlist.getReturnValuesIterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
				System.out.println(rlist.getCompleted()+"/"+rlist.size());
			}
		}
		
		public void testKill() throws CommandException{
			ServerCommandExectuorManager.addServersToCluster("localhost:6080");
			ArrayList<Integer> items = new ArrayList<Integer>();
			for(int i = 0; i < 50; i ++){
				items.add(i);
			}
			MyRDCList<Integer, String> rlist = new MyRDCList<Integer, String>(items);
			Class<MyRDCCallable> class1 = MyRDCCallable.class;
			RDCCallerCommand<Integer, String, MyRDCCallable> command = new RDCCallerCommand<Integer, String, MyRDCCallable>("test", rlist.getRDCListIterator(), class1, "Name");
			String serverAddress = "localhost:6080";
			CommandExecutor commandExecutor = new CommandExecutor(serverAddress, serverAddress);
			commandExecutor.execute(command);
			Iterator<String> iterator = rlist.getReturnValuesIterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next());
				System.out.println(rlist.getCompleted()+"/"+rlist.size());
				if(rlist.getCompleted() == 20){
					rlist.kill();
				}
			}
		}
		
		public static class MyRDCCallable implements RDCCallable<Integer, String>{
			private String name;
			public MyRDCCallable(Object name) {
				super();
				this.name = (String) name;
			}

			@Override
			public boolean open() {
				return true;
			}

			@Override
			public String call(Integer item) {
				System.out.println("working on " + name + " :"+ item);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return String.valueOf(item*2)+ "-"+PropertyManager.getPropertyValue("yogi.remote.server.app.CommandServerProcessor:RegistryPort");
			}

			@Override
			public boolean close() {
				return true;
			}
			
		}
		public static class MyErrorRDCCallable implements RDCCallable<Integer, String>{
			private String name;
			public MyErrorRDCCallable(Object name) {
				super();
				this.name = (String) name;
			}

			@Override
			public boolean open() {
//				throw new RuntimeException("Error in open");
				return true;
			}

			@Override
			public String call(Integer item) {
				System.out.println("working on " + name + " :"+ item);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(item == 5) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
					throw new RuntimeException("Error check");
				}
				return String.valueOf(item*2)+ "-"+PropertyManager.getPropertyValue("yogi.remote.server.app.CommandServerProcessor:RegistryPort");
			}

			@Override
			public boolean close() {
				return true;
			}
			
		}
		
		public static class MyRDCList<T, R> extends RDCList<T, R>{

			public MyRDCList() {
				super();
			}

			public MyRDCList(Collection<T> items) {
				super(items);
			}

			@Override
			public RDCListIterator<T, R> getRDCListIterator() {
				return super.getRDCListIterator();
			}
			
		}
}
