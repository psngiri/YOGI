package yogi.base.app.error;

import java.util.List;
import java.util.Map.Entry;

public class ErrorsLoggerThreadFormatter extends ErrorsLoggerFormatter
{

	@Override
	public String convertToString(Entry<String, List<Object>> message) {
		return appendThreadInformaton(super.convertToString(message));
	}

	private String appendThreadInformaton(String message) {
		long threadId = Thread.currentThread().getId();
		StringBuilder sb = new StringBuilder();
		sb.append("Thread(").append(threadId).append(")");
		return sb.toString() + message;
	}

	@Override
	public String convertToString(Object message) {
		return appendThreadInformaton(super.convertToString(message));
	}
	
}
