package yogi.base.app.multithread.rdc;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import yogi.base.app.ErrorReporter;
import yogi.base.util.collections.IndexItem;

public class RDCList<T, R>{

	public static long SleepTime = 0;
	public static int RetryAttempts = 2;
	public static boolean ClearReferencesWhileIterating=false;
	private ArrayList<T> items;
	private ArrayList<R> returnValues;
	private boolean workingOnFailures = false;
	private int index = 0;
	private int completed = 0;
	private boolean done = false;
	private boolean haveFailures = false;
	private Map<Integer, Integer> retrys = new HashMap<Integer, Integer>();
	private LinkedList<IndexItem<T>> failedItems = new LinkedList<IndexItem<T>>();
	public RDCList() {
		super();
	}

	public RDCList(Collection<T> items) {
		this.items = new ArrayList<T>(items);
		returnValues = new ArrayList<R>(items.size());
		for(int i = 0; i< items.size(); i ++){
			returnValues.add(null);
		}
	}

	public ArrayList<R> getReturnValues() {
		while(!done){
			if(!ErrorReporter.get().getErrors().isEmpty()){
				done = true;
				haveFailures = true;
				throw new RuntimeException("Errors in execution:"+ErrorReporter.get().getErrors());
			}
			try {
				Thread.sleep(SleepTime);
			} catch (InterruptedException e) {
				done = true;
				haveFailures = true;
				throw new RuntimeException(e.getMessage(),e);
			}
		}
		if(haveFailures){
			done = true;
			throw new RuntimeException("Errors in execution:"+ErrorReporter.get().getErrors());
		}
		return returnValues;
	}
	
	public Iterator<R> getReturnValuesIterator(){
		return new BlockingIterator<R>(this);
	}

	protected RDCListIterator<T, R> getRDCListIterator(){
		return new MyRDCListIterator<T, R>(this);
	}
	 
	private synchronized IndexItem<T> getNextIndexItem(IndexItem<T> indexItem, R rtnValue)
	{
		if(done) return null;
		if(indexItem != null){
			if(rtnValue != null && returnValues.get(indexItem.getIndex()) == null){
				returnValues.set(indexItem.getIndex(), rtnValue);
				completed++;
			}
			if(rtnValue == null){
				failedItems.add(indexItem);
				int index = indexItem.getIndex();
				Integer retry = retrys.get(index);
				if(retry == null) retry = 0;
				if(retry >= RetryAttempts-1){
					done = true;
					haveFailures = true;
				}
				retrys.put(index, retry+1);
			}
		}
		if(!failedItems.isEmpty()){
			return failedItems.remove();
		}
		if(workingOnFailures){
			return getNextFailedItem();
		}
		if(index >= items.size()){
			workingOnFailures = true;
			index = 0;
			return getNextFailedItem();
		}
		return new IndexItem<T>(items.get(index), index++);
	}
	
	public int getCompleted() {
		return completed;
	}

	private IndexItem<T> getNextFailedItem() {
		for(int i = index ; i < returnValues.size(); i ++){
			if(returnValues.get(i) == null){
				index = i;
				return new IndexItem<T>(items.get(i), i);
			}
		}
		done = true;
		return null;
	}

	public static class MyRDCListIterator<I, O> implements RDCListIterator<I, O>{
		private RDCList<I,O> rdcList;
		
		public MyRDCListIterator(RDCList<I, O> rdcList) {
			super();
			this.rdcList = rdcList;
		}

		@Override
		public IndexItem<I> getNextIndexItem(IndexItem<I> indexItem, O rtnValue) throws RemoteException {
			return rdcList.getNextIndexItem(indexItem, rtnValue);
		}
		
	}
	
	public static class BlockingIterator<E> implements Iterator<E> {
		private List<E> list;
		private RDCList<?, E> rdcList;
		private int index = 0;
		
		public BlockingIterator(RDCList<?, E> rdcList) {
			super();
			this.rdcList = rdcList;
			this.list = rdcList.returnValues;
		}

		@Override
		public boolean hasNext() {
			return index < list.size();
		}

		@Override
		public E next() {
			E rtnValue = list.get(index);
			if(!rdcList.done){
				while(rtnValue == null && !rdcList.haveFailures){
					if(!ErrorReporter.get().getErrors().isEmpty()){
						rdcList.done = true;
						rdcList.haveFailures = true;
						throw new RuntimeException("Errors in execution:"+ErrorReporter.get().getErrors());
					}
					try {
						Thread.sleep(SleepTime);
						rtnValue = list.get(index);
					} catch (InterruptedException e) {
						throw new RuntimeException(e.getMessage(),e);
					}
				}
			}
			if(ClearReferencesWhileIterating && index !=0){
				list.set(index, list.get(0));
			}
			index++;
			if(rdcList.haveFailures) {
				throw new RuntimeException("Errors in execution failed item=retrys:"+rdcList.retrys);
			}
			return rtnValue;
		}

		@Override
		public void remove() {
			throw new RuntimeException("Note implement for this class");
		}

	}

	public void kill(){
		done = true;
	}

	public int size() {
		return items.size();
	}
}
