package yogi.base.factory.capacity.io;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import yogi.base.Factory;
import yogi.base.io.BaseFileReader;
import yogi.base.io.Reader;
import yogi.base.util.logging.Logging;

public class FactoryCapacityReader implements Reader<Object>{
	private static Logger logger = Logging.getLogger(FactoryCapacityReader.class);
	public static String FileName = "*/config/factoryCapacities.dat";
	public static boolean RUN = true;
	MyReader reader;
	
	public FactoryCapacityReader() {
		super();
		reader = new MyReader(FileName);
	}

	public List<Object> read() {
		try {
			reader.open();
		} catch (RuntimeException e) {
			logger.warning(e.getMessage());
			return null;
		}
		reader.read();
		reader.close();
		return null;
	}

	public static class MyReader extends BaseFileReader
	{

		public MyReader(String fileName) {
			super(fileName, 0);
		}
		
		public void read()
		{
			String line;
			try {
				while((line = reader.readLine()) != null)
				{
					Scanner scanner = new Scanner(line);
					
					String className = scanner.next();
					int initialCapacity = Integer.parseInt(scanner.next());
					Class<?> classForName = Class.forName(className);
					Factory.setInitialCapacity(classForName, initialCapacity);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isActivated() {
		return RUN;
	}
}
