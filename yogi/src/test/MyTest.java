package test;

import junit.framework.TestCase;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.jar.*;

import yogi.base.util.node.Node;

public class MyTest extends TestCase {

	public void test()
	{

	}
	public void mytest()
	{
//		this.getClass().getResource("");
//		Package[] packages = Package.getPackages();
//		for(Package package1: packages)
//		{
//			System.out.println(package1.toString());
//		}
		Node<NodeData> rootNode = new Node<NodeData>(null, new NodeData("ROOT", NodeType.Root));
		Node<NodeData> currentNode = rootNode;
		String last = "";
		String[] lastTokens = {};
		try {
			String jarFileName = "C:/eclipse ws/osm/osm.jar";
			currentNode = new Node<NodeData>(currentNode, new NodeData(jarFileName, NodeType.Jar));
			JarFile myJarFile = new JarFile(jarFileName);
			Enumeration<JarEntry> entries = myJarFile.entries();
			while(entries.hasMoreElements())
			{
				JarEntry element = entries.nextElement();
				String entryString = element.toString();
				if(entryString.endsWith(".class"))
				{
					int lastIndexOf = entryString.lastIndexOf('/');
					String package1 = "";
					String class1 = "";
					if(lastIndexOf == -1) class1 = entryString;
					else if(lastIndexOf == 0) class1 = entryString.substring(1);
					else
					{
						package1 = entryString.substring(0, lastIndexOf);
						class1 = entryString.substring(lastIndexOf +1);
					}
					if(!package1.equals(last))
					{
						String[] tokens = package1.split("/");
						int i = 0;
						for(;i< lastTokens.length && i < tokens.length; i ++)
						{
							if(!tokens[i].equals(lastTokens[i])) break;
						}
						for(int j = 0; j < lastTokens.length -i; j++)
						{
							currentNode = currentNode.getParent();
						}
						for(int k = i; k < tokens.length; k ++)
						{
							currentNode = new Node<NodeData>(currentNode, new NodeData(tokens[k], NodeType.Package));
						}
						last = package1;
						lastTokens = tokens;
					}
					String className = entryString.substring(0, entryString.indexOf(".class")).replaceAll("/", ".");
					Class<?> class2 = Class.forName(className);
					Node<NodeData> node = null;

					Field[] fields = class2.getFields();
					for(Field field : fields)
					{
						int modifiers = field.getModifiers();
						if(!Modifier.isFinal(modifiers) && Modifier.isStatic(modifiers))
						{
							if(node == null) node = new Node<NodeData>(currentNode, new NodeData(class1, class2));
							new Node<NodeData>(node, new NodeData(field.getName(), field));
						}
					}
					if(node != null)
					{
						System.out.print(entryString + " ");
						write(node);
						System.out.print(" " + class2.getName());
						System.out.println();
						for(int i = 0; i < node.getChildCount(); i++)
						{
							System.out.println(node.getChildAt(i).getData());
						}
					}

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static class NodeData
	{
		String name;
		Class klass;
		Field field;
		NodeType nodeType;
		public NodeData(String name, NodeType nodeType) {
			this(name, null, null);
			this.nodeType = nodeType;
		}
		public NodeData(String name, Class klass) {
			this(name, klass, null);
			nodeType = NodeType.Class;
		}

		public NodeData(String name, Field field) {
			this(name, null, field);
			nodeType = NodeType.Field;
		}
		protected NodeData(String name, Class klass, Field field) {
			super();
			this.name = name;
			this.klass = klass;
			this.field = field;
		}

		public Class getKlass() {
			return klass;
		}
		public String getName() {
			return name;
		}
		public Field getField() {
			return field;
		}

		public NodeType getNodeType() {
			return nodeType;
		}
		@Override
		public String toString() {
			if(field !=  null)
			{
				try {
					Object value = field.get(null);
					return getName() + " = " + value;
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return getName();
		}

	}

	public enum NodeType {
		Root, Jar, Package, Class, Field, Method
	}

	private void write(Node<NodeData> node) {
		if(node.getParent() == null) return;
		write(node.getParent());
		System.out.print(node.getData());
		NodeType nodeType = node.getData().getNodeType();
		if( nodeType == NodeType.Jar) System.out.print('/');
		else if( nodeType == NodeType.Package) System.out.print('.');
	}
}
