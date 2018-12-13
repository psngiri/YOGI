package yogi.base.app.management;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import yogi.base.app.ApplicationProperties;
import yogi.property.management.PropertiesManager;

public class ManagementAssistant {
	private static final String COM_SUN_MANAGEMENT_JMXREMOTE = "com.sun.management.jmxremote";
	private static ManagementAssistant itsInstance = new ManagementAssistant();
	
	public static ManagementAssistant get()
	{
		return itsInstance;
	}
	
	public void registerMBean(String type, Object mbeanInstatnce)
	{
		if(System.getProperty(COM_SUN_MANAGEMENT_JMXREMOTE) == null) return;
		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName(ApplicationProperties.ApplicationName +":type=" + type);
			mbs.registerMBean(mbeanInstatnce, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void registerPropertyMBean()
	{
		registerMBean("Property", new PropertiesManager());
	}
	public void registerExecutionManagerMBean()
	{
		registerMBean("Executor", new ExecutionManager());
	}
}
