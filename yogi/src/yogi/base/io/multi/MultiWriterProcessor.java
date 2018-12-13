package yogi.base.io.multi;

import java.lang.reflect.Constructor;
import java.util.List;

import yogi.base.Util;
import yogi.base.app.BaseProcessor;
import yogi.base.app.multithread.Callable;
import yogi.base.app.multithread.MultiCaller;
import yogi.base.app.multithread.ThreadPoolExecutor;

public abstract class MultiWriterProcessor<T> extends BaseProcessor {
	public static String MarketListName = ":TD SAS INT";
	public static boolean RUN = true;
	private String fileName;
	private Class<? extends Writer<T>> klass;
	private ObjectClass<?>[] args;
	
	public MultiWriterProcessor(String fileName, Class<? extends Writer<T>> klass, ObjectClass<?>... args) {
		super();
		this.fileName = fileName;
		this.klass = klass;
		this.args = args;
	}

	public boolean isActivated() {
		return RUN;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		List<T> items = getItems();
		List<List<T>> mulItems = Util.getSubLists(items);
		Counter counter = new Counter();
		new MultiCaller<List<T>>().call(mulItems, WriterCallable.class, fileName, counter, klass.getCanonicalName(),args);
	}
	
	protected abstract List<T> getItems();

	public static class Counter{
		private int count = 1;
		public synchronized int getCount(){
			return count++;
		}
	}
	public static class WriterCallable<T> implements Callable<List<T>>
	{
		private Writer<T> writer;
		@SuppressWarnings("unchecked")
		public WriterCallable(Object fileName, Object counter, Object klass, Object args) {
			super();
			String fileName2 = getFilename(fileName, counter);
			ObjectClass<?>[] myArgs = (ObjectClass<?>[]) args;
			try {
				Class<?>[] klasses = new Class<?>[myArgs.length+1];
				Object[] objects = new Object[myArgs.length+1];
				klasses[0] = String.class;
				objects[0] = fileName2;
				for(int i = 0; i < myArgs.length; i++)
				{
					klasses[i+1] = myArgs[i].getKlass();
					objects[i+1] = myArgs[i].getObject();				}
				Constructor<?> constructor = Class.forName((String)klass).getConstructor(klasses);
				writer = (Writer<T>) constructor.newInstance(objects);
			} catch (Exception ex) {
				throw new RuntimeException("Exception While Executing " + this.getClass().getName() + ": "+ ex);
			}
		}

		protected String getFilename(Object fileName, Object counter) {
			String filename1 = (String)fileName;
			if(ThreadPoolExecutor.NumberOfThreads == 1) return filename1;
			int lastIndexOf = filename1.lastIndexOf(".");
			String myFileName = filename1.substring(0, lastIndexOf);
			String myFileExtn = "";
			if(lastIndexOf > 0) myFileExtn = filename1.substring(lastIndexOf);
			return myFileName + "-"+ ((MultiWriterProcessor.Counter)counter).getCount()+ "-"+ myFileExtn;
		}

		public boolean open() {
			return true;
		}

		public boolean close() {
			return true;
		}

		@Override
		public void call(List<T> item) {
			try {
				writer.write(item);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
	}
}
