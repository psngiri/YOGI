package yogi.tools.viewers.property;

/*
 * %W% %E%
 *
 * Copyright 1997, 1998 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above
 *   copyright notice, this list of conditions and the following
 *   disclaimer in the documentation and/or other materials
 *   provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY
 * DAMAGES OR LIABILITIES SUFFERED BY LICENSEE AS A RESULT OF OR
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THIS SOFTWARE OR
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT,
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF
 * THE USE OF OR INABILITY TO USE THIS SOFTWARE, EVEN IF SUN HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed, licensed or
 * intended for use in the design, construction, operation or
 * maintenance of any nuclear facility.
 */

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import yogi.base.app.Executor;
import yogi.base.relationship.types.capacity.io.CapacityReader;
import yogi.base.util.node.Node;
import yogi.base.util.node.TreeNodeImpl;
import yogi.tools.swingx.treetable.TreeTableDataHelper;
import yogi.tools.viewers.TreeTableViewer;


/**
 * A TreeTable example, showing a JTreeTable, operating on the local file
 * system.
 *
 * @version %I% %G%
 *
 * @author Philip Milne
 */

public class PropertyViewer
{
	private static String[] jarFileNames;

	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: PropertyTreeViewer jarFile [...]");
			return;
		}
		jarFileNames = args;
	CapacityReader capacityReader = new CapacityReader();
	Executor.get().execute(capacityReader);
	new TreeTableViewer<NodeData>(new PropertyTreeTableDataHelper(), "Property Viewer", getRootNode());

    }
	public static TreeNodeImpl<NodeData> getRootNode() {
		Node<NodeData> rootNode = new Node<NodeData>(null, new NodeData("ROOT",
				NodeType.Root));
		Node<NodeData> currentNode = rootNode;
		String last = "";
		String[] lastTokens = {};
		for (String jarFileName : jarFileNames) {
			currentNode = new Node<NodeData>(currentNode, new NodeData(
					jarFileName, NodeType.Jar));
			try {
				JarFile myJarFile = new JarFile(jarFileName);
				Enumeration<JarEntry> entries = myJarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry element = entries.nextElement();
					String entryString = element.toString();
					if (entryString.endsWith(".class")) {
						int lastIndexOf = entryString.lastIndexOf('/');
						String package1 = "";
						String class1 = "";
						if (lastIndexOf == -1)
							class1 = entryString;
						else if (lastIndexOf == 0)
							class1 = entryString.substring(1);
						else {
							package1 = entryString.substring(0, lastIndexOf);
							class1 = entryString.substring(lastIndexOf + 1);
						}
						if (!package1.equals(last)) {
							String[] tokens = package1.split("/");
							int i = 0;
							for (; i < lastTokens.length && i < tokens.length; i++) {
								if (!tokens[i].equals(lastTokens[i]))
									break;
							}
							for (int j = 0; j < lastTokens.length - i; j++) {
								currentNode = currentNode.getParent();
							}
							for (int k = i; k < tokens.length; k++) {
								currentNode = new Node<NodeData>(currentNode,
										new NodeData(tokens[k],
												NodeType.Package));
							}
							last = package1;
							lastTokens = tokens;
						}
						String className = entryString.substring(0,
								entryString.indexOf(".class")).replaceAll("/",
								".");
						try {
							Class<?> class2 = Class.forName(className);
							Node<NodeData> node = null;

							Field[] fields = class2.getFields();
							for (Field field : fields) {
								int modifiers = field.getModifiers();
								if (!Modifier.isFinal(modifiers)
										&& Modifier.isStatic(modifiers)) {
									if (node == null)
										node = new Node<NodeData>(currentNode,
												new NodeData(class1, class2));
									new Node<NodeData>(node, new NodeData(field
											.getName(), field));
								}
							}
						} catch (ClassNotFoundException e) {
							System.out.println("ClassNotFoundException: " + e.getMessage());
						} catch (Throwable t)
						{
							System.out.println(t.getClass().getName() + " - " + className);
						}

					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return new TreeNodeImpl<NodeData>(rootNode);
	}

	static class NodeData {
		String name;
		Class klass;
		Field field;
		NodeType nodeType;
		String value;

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
		public String getValue()
		{
			if(value == null)
			{
				if (field != null) {
					try {
						Object objectValue = field.get(null);
						if(objectValue != null)
						{
							value = objectValue.toString();
						}else
						{
							value = "";
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}else
				{
					value = "";
				}
			}
			return value;
		}

	}

	public enum NodeType {
		Root, Jar, Package, Class, Field, Method
	}

	static class PropertyTreeTableDataHelper implements TreeTableDataHelper<NodeData> {

		public Object getValueAt(NodeData nodeData, int column) {
			switch(column)
			{
			case 0: return nodeData.getName();
			case 1:
				return nodeData.getValue();
			}
			return null;
		}

		public int getColumnCount() {
			return 2;
		}

		public boolean isEditable(NodeData nodeData, int column) {
			return false;
		}

		public void setValueAt(NodeData nodeData, Object aValue, int column) {

		}

		public String getColumnName(int column) {
			switch(column)
			{
			case 0: return "Name";
			case 1: return "Value";
			}
			return "";
		}

	}
}

