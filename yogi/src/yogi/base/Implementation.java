package yogi.base;

import java.util.HashMap;
import java.util.Map;

import yogi.base.relationship.RelationshipObject;

public class Implementation {
	private static Map<Class, Class> firstInterfaceCache = new HashMap<Class, Class>();
	private static Class getFirstInterface(Class klass)
	{
		Class[] interfaces = klass.getInterfaces();
		while(interfaces.length == 0)
		{
			klass = klass.getSuperclass();
			if(klass == null) return null;
			interfaces = klass.getInterfaces();
		}
		return interfaces[0];
	}
	
	public static Class getInterface(Object implementation)
	{
		return getInterface(implementation.getClass());
	}
	
	public static Class getInterface(Class implementation)
	{
		Class firstInterface = firstInterfaceCache.get(implementation);
		if(firstInterface != null) return firstInterface;
		firstInterface = getFirstInterface(implementation);
		if(firstInterface != null) return firstInterface;
		throw new RuntimeException("Business Object Implementation should implement a Business Interface: " + implementation);
	}
	
	public static boolean isImplementationOf(Class interfaceClass, Object object) {
		return getInterface(object).equals(interfaceClass);
	}
	
	public static boolean isOfType(Class interfaceClass, Object object)
	{
		Class myInterface = getInterface(object);
		if(myInterface.equals(interfaceClass)) return true;
		do
		{
			myInterface = Implementation.getInterface(myInterface);
			if(myInterface.equals(interfaceClass)) return true;
		}while(myInterface != RelationshipObject.class);
		return false;
	}
}
