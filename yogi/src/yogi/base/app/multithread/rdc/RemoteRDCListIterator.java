package yogi.base.app.multithread.rdc;

import java.rmi.RemoteException;

import yogi.base.util.collections.IndexItem;
import yogi.remote.UnicastRemoteObject;

public class RemoteRDCListIterator<T, R> extends UnicastRemoteObject implements RDCListIterator<T, R> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9059239094445714357L;
	private RDCListIterator<T, R> iterator;
	
	public RemoteRDCListIterator(RDCListIterator<T, R> iterator) throws RemoteException {
		super();
		this.iterator = iterator;
	}

	@Override
	public IndexItem<T> getNextIndexItem(IndexItem<T> indexItem, R rtnValue) throws RemoteException {
		return iterator.getNextIndexItem(indexItem, rtnValue);
	}

}
