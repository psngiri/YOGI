package yogi.base.app.multithread.rdc;

import java.rmi.Remote;
import java.rmi.RemoteException;

import yogi.base.util.collections.IndexItem;

public interface RDCListIterator<T, R>  extends Remote{
	IndexItem<T> getNextIndexItem(IndexItem<T> indexItem, R rtnValue) throws RemoteException;
}