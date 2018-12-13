package yogi.base.factory.capacity.io;

import yogi.base.Factory;
import yogi.base.app.ApplicationProperties;
import yogi.base.io.BaseHeaderFileWriter;
import yogi.base.io.Writer;

public class FactoryCapacityWriter implements Writer {
	public static boolean RUN = true;
	public static String FileName = "factoryCapacities.dat";
	MyWriter writer;
	
	public FactoryCapacityWriter() {
		super();
		this.writer = new MyWriter(ApplicationProperties.ConfigDirectoryLocation + "/" + FileName);
	}

	public void write() {
		writer.open();
		for(Factory<?> factory: Factory.getFactories())
		{
			writeFactoryCapacity(factory);
		}
		writer.close();

	}

	private void writeFactoryCapacity(Factory<?> factory) {
		StringBuilder sb = new StringBuilder();
		sb.append(factory.getClass().getName()).append(' ');
		sb.append(factory.size());
		writer.writeln(sb.toString());
	}


	public static class MyWriter extends BaseHeaderFileWriter
	{

		public MyWriter(String fileName) {
			super(fileName);
		}
		public void writeln(String record)
		{
			writer.println(record);
		}
	}

	public boolean isActivated() {
		return RUN;
	}
}
