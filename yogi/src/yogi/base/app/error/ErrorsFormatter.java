package yogi.base.app.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;

import yogi.base.app.ErrorReporter;
import yogi.base.indexing.ManyIndexer;
import yogi.base.util.UniqueCounter;

public abstract class ErrorsFormatter
{
	
	public ErrorsFormatter() {
		super();
	}

	public void report(ErrorReporter errorReporter) {
		report(errorReporter.getFinestMessages(), Level.FINEST);
		report(errorReporter.getFinerMessages(), Level.FINER);
		report(errorReporter.getFineMessages(), Level.FINE);
		report(errorReporter.getInfoMessages(), Level.INFO);
		report(errorReporter.getWarnings(), Level.WARNING);
		reportStack(errorReporter.getErrors(), Level.SEVERE);
		if(!errorReporter.getErrors().isEmpty()){
			throw new RuntimeException("Errors found during Execution check the log."+errorReporter.getErrors());
		}
	}
	

	private void report(ManyIndexer<String, Object> messages, Level level) {
		for(Map.Entry<String, List<Object>> message: messages.entrySet())
		{
			report(level, message);
		}
	}


	public abstract void report(Level level, Map.Entry<String, List<Object>> message);
	
	private void reportStack(StackIndexer messages, Level level) {
		for(Map.Entry<String, List<Object>> message: messages.entrySet())
		{
			Throwable throwable = messages.getThrowable(message.getKey());
			report(level, message, throwable);
		}
	}


	public abstract void report(Level level, Map.Entry<String, List<Object>> message, Throwable throwable);
	
	public String convertToString(Map.Entry<String, List<Object>> message) {
		List<Object> value = new ArrayList<Object>(message.getValue());
		String key = message.getKey();
		return buildHeaderMessage(value, key);
	}
	
	public String convertToString(Object message) {
		return message.toString();
	}
	
	private String buildHeaderMessage(List<Object> value, String key) {
		UniqueCounter<String> uniqueCounter = new UniqueCounter<String>();
		for(Object item: value)
		{
			try {
				uniqueCounter.put((item != null)?item.toString() : "null");
			} catch (Exception e) {
				throw new RuntimeException("Error while processing: " + key,e);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(key).append("(s):");
		Set<Entry<String, Integer>> entrySet = uniqueCounter.entrySet();
		sb.append("(").append(entrySet.size()).append(")");
		for(Entry<String, Integer> item: entrySet)
		{
			sb.append(item.getKey());
			if(item.getValue() > 1)
			{
				sb.append('(').append(item.getValue()).append(')');
			}
			sb.append(',');
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}
		
}
