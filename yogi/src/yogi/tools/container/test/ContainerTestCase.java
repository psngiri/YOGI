package yogi.tools.container.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import yogi.tools.container.Container;

import junit.framework.TestCase;

public class ContainerTestCase extends TestCase {

//	public void testExecute()
//	{
//		Container.execute("yogi.tools.container.test.Counter", new String[]{"First"}, null, false);
//		new Counter("First plus");
//		Container.execute("yogi.tools.container.test.Counter", new String[]{"Second"}, null, false);
//		new Counter("Second plus");
//		Container.execute("yogi.tools.container.test.Counter", new String[]{"Third"}, null, false);
//		new Counter("Third plus");
//		Container.execute("yogi.tools.container.test.Counter", new String[]{"Fourth"}, null, false);
//		new Counter("Fourth plus");
//	}
	public void test(){
		
	}
	
	public void one() throws MalformedURLException
	{
		URL[] urls = new URL[]{ new File("C:/eclipse ws JCPA/Core/classes/").toURI().toURL() };
		Container.printUrls(urls, "Test");
		CounterInt counter1 = (CounterInt) Container.instantiateInSeparateContainer("yogi.core.test.container.Counter", new String[]{"First"}, urls);
		System.out.println(" Name:" + counter1.getName() + " incrementCount: " + counter1.incrementCount());
		System.out.println(" Name:" + counter1.getName() + " incrementCount: " + counter1.incrementCount());
		
		CounterInt counter2 = (CounterInt) Container.instantiateInSeparateContainer("yogi.core.test.container.Counter", new String[]{"Two"}, urls);
		System.out.println(" Name:" + counter2.getName() + " incrementCount: " + counter2.incrementCount());
		System.out.println(" Name:" + counter2.getName() + " incrementCount: " + counter2.incrementCount());

		CounterInt counter3 = (CounterInt) Container.instantiateInSeparateContainer("yogi.core.test.container.Counter", new String[]{"Three"}, urls);
		System.out.println(" Name:" + counter3.getName() + " incrementCount: " + counter3.incrementCount());
		System.out.println(" Name:" + counter3.getName() + " incrementCount: " + counter3.incrementCount());
	
		
		System.out.println(" Name:" + counter1.getName() + " incrementCount: " + counter1.incrementCount());
		System.out.println(" Name:" + counter2.getName() + " incrementCount: " + counter2.incrementCount());
		System.out.println(" Name:" + counter3.getName() + " incrementCount: " + counter3.incrementCount());

	}
	
}
