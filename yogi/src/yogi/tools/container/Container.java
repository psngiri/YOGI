package yogi.tools.container;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Container {

	@SuppressWarnings("unchecked")
	public static <T> T instantiateInSeparateContainer(String applicaitonClassName, String[] args, URL[] urls)
	{
		URLClassLoader classLoader = createContainerClassLoader(urls);
		try {
			Class clas = classLoader.loadClass(applicaitonClassName);
			Class[] ptypes = new Class[] { args.getClass() };
			Constructor constructor = clas.getConstructor(ptypes);
			return (T) constructor.newInstance(new Object[] { args });
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static Object instantiateInSeparateContainer(String applicaitonClassName, String[] args)
	{
		URLClassLoader classLoader = createContainerClassLoader();
		try {
			Class clas = classLoader.loadClass(applicaitonClassName);
			Class[] ptypes = new Class[] { args.getClass() };
			Constructor constructor = clas.getConstructor(ptypes);
			return constructor.newInstance(new Object[] { args });
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void execute(String applicaitonClassName, String[] args, URL[] urls, boolean useParentUrls)
	{
		URLClassLoader classLoader = createContainerClassLoader();
		
		try {
			Class<?> clas = classLoader.loadClass(applicaitonClassName);
			Class<?>[] ptypes = new Class[] { args.getClass() };
			Method main = clas.getDeclaredMethod("main", ptypes);
			Thread.currentThread().setContextClassLoader(classLoader);
			main.invoke(null, new Object[] { args });

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static URLClassLoader createContainerClassLoader() {
		ClassLoader base = ClassLoader.getSystemClassLoader();
		
		URL[] myUrls = getUrls(base, true);
		printUrls(myUrls, "Base");
		ClassLoader parent = base.getParent();
		printUrls(getUrls(parent, false), "Parent");
		URLClassLoader  classLoader = new URLClassLoader(myUrls, parent);
		return classLoader;
	}

	private static URLClassLoader createContainerClassLoader(URL[] urls) {
		ClassLoader base = ClassLoader.getSystemClassLoader();
		
		URL[] myUrls = getUrls(base, true);
		printUrls(myUrls, "Base");
		
		printUrls(urls, "Self");
		URLClassLoader  classLoader = new URLClassLoader(urls, base);
		return classLoader;
	}
	
	public static void printUrls(URL[] myUrls, String name) {
		System.out.println(name);
		for(URL url: myUrls)
		{
			System.out.println(url);
		}
	}

	private static URL[] getUrls(ClassLoader base, boolean setDefault) {
		URL[] myUrls = null;
		if (base instanceof URLClassLoader) {
			myUrls = ((URLClassLoader)base) .getURLs();
		} else if(setDefault){
			try {
				myUrls = new URL[]{ new File(".").toURI().toURL() };
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return myUrls;
	}
}
