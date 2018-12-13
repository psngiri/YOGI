package yogi.tools.container;

import java.lang.reflect.Method;

public class Run {
	public static void main(String[] args) {
		if (args.length >= 1) {
			try {
				// load the target class to be run 
				Class<?> clas = Run.class.getClassLoader().loadClass(args[0]);
				// invoke main method of target class 
				Class<?>[] ptypes = new Class[] { args.getClass() };
				Method main = clas.getDeclaredMethod("main", ptypes);
				String[] pargs = new String[args.length-1];
				System.arraycopy(args, 1, pargs, 0, pargs.length);
				main.invoke(null, new Object[] {pargs});
				} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} else {
			System.out.println("Usage: Run mainClass args ");
		}
	}
}
