package yogi.base.relationship.types.capacity.io;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import yogi.base.io.BaseFileReader;
import yogi.base.io.Reader;
import yogi.base.relationship.types.capacity.CapacityNode;
import yogi.base.util.logging.Logging;

public class CapacityReader implements Reader<Object>{
	private static Logger logger = Logging.getLogger(CapacityReader.class);
	public static String FileName = "*/config/capacities.dat";
	public static boolean RUN = true;
	MyReader reader;
	
	public CapacityReader() {
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
					
					String parentName = scanner.next();
					String className = scanner.next();
					int startIndex = Integer.parseInt(scanner.next());
					int initialCapacity = Integer.parseInt(scanner.next());
					CapacityNode parent = null;
					if(!parentName.equals("null")) parent = CapacityNode.getNode(Class.forName(parentName));
					new CapacityNode(parent, Class.forName(className), startIndex, initialCapacity);
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
