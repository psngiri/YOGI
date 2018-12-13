package yogi.base.app.multithread.rdc.command;

import java.rmi.RemoteException;

import yogi.base.app.multithread.rdc.RDCCallable;
import yogi.base.app.multithread.rdc.RDCCaller;
import yogi.base.app.multithread.rdc.RDCListIterator;
import yogi.base.app.multithread.rdc.RemoteRDCListIterator;
import yogi.remote.client.app.CommandAdapter;

public class RDCCallerCommand<T, R, C extends RDCCallable<T, R>> extends CommandAdapter<Boolean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1354005723849675710L;
	public static String userId = null;
	private RDCListIterator<T, R> iterator;
	private Class<C> klass;
	private Object[] parameters;
	private boolean distribute = true;
	
	public RDCCallerCommand(String userId, RDCListIterator<T, R> iterator, Class<C> klass, Object... parameters) {
		super(userId);
		try {
			this.iterator = new RemoteRDCListIterator<T,R>(iterator);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.klass = klass;
		this.parameters = parameters;
	}

	@Override
	public Boolean execute() {
		RDCCaller<T, R, C> rMultiCaller = new RDCCaller<T, R, C>();
		rMultiCaller.execute(iterator, klass, parameters);
		if(distribute){
			distribute = false;
			rMultiCaller.distribute(this);
		}
		return true;
	}

}
