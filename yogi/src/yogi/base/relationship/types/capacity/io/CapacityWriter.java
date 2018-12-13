package yogi.base.relationship.types.capacity.io;

import java.util.List;

import yogi.base.app.ApplicationProperties;
import yogi.base.io.BaseHeaderFileWriter;
import yogi.base.io.Writer;
import yogi.base.relationship.types.capacity.CapacityNode;
import yogi.base.relationship.types.capacity.Capacity;
import yogi.base.util.node.Node;

public class CapacityWriter implements Writer {
	public static boolean RUN = true;
	public static String FileName = "capacities.dat";
	MyWriter writer;
	
	public CapacityWriter() {
		super();
		this.writer = new MyWriter(ApplicationProperties.ConfigDirectoryLocation + "/" + FileName);
	}

	public void write() {
		writer.open();
		List<Node<Capacity>> decendents = CapacityNode.getRootNode().getDecendents();
		if(!decendents.isEmpty()) writeNode(CapacityNode.getRootNode());
		for(Node<Capacity> node: decendents)
		{
			writeNode(node);
		}
		writer.close();

	}

	private void writeNode(Node<Capacity> node) {
		Capacity capacity= node.getData();
		StringBuilder sb = new StringBuilder();
		String parentClassName = "null";
		if(node.getParent() != null)parentClassName = node.getParent().getData().getKlass().getName();
		sb.append(parentClassName).append(' ');
		sb.append(capacity.getKlass().getName()).append(' ');
		sb.append(capacity.getStartIndex()).append(' ');
		sb.append(capacity.getCapacity());
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
