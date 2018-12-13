package yogi.base.app;

public class ErrorReporterExtention {

	public static void push(ErrorReporter errorReporter)
	{
		ErrorReporter.push(errorReporter);
	}
	
	public static ErrorReporter pop()
	{
		return ErrorReporter.pop();
	}
	
	public static boolean isEmpty()
	{
		return ErrorReporter.isEmpty();
	}
}
