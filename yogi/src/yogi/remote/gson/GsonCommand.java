package yogi.remote.gson;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.Command;
import yogi.base.app.Executor;
import yogi.base.util.logging.Logging;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;
import com.google.gson.Gson;

public class GsonCommand extends CommandAdapter<String> {
	private static Logger logger = Logging.getLogger(GsonCommand.class);
	private static final long serialVersionUID = -6266681102461058536L;
	private String key;
	private String value;
	private static Map<String, Logger> loggers = new HashMap<String, Logger>();
	
	public GsonCommand(String key, String value, String userId) {
		super(userId);
		this.key = key;
		this.value = value;
	}

	@Override
	public String execute() {
		Gson gson = new Gson();
		String returnValue = "";
		log();
        try {
			@SuppressWarnings("unchecked")
			Class<Command<?>> klass =  (Class<Command<?>>) Class.forName(key);
			Command<?> command = gson.fromJson(value, klass);
			Object rtnValue = Executor.get().execute(command);
			String smUserId = getUserId();
			//if(smUserId!=null && !compare(command.getUserId(), smUserId)){
				//logger.log(Level.SEVERE,String.format("WRONG USER TRYING TO ACCESS THE SERVER:smUserId=%s, userId=%s", smUserId, command.getUserId()));
				//throw new RuntimeException("UNAUTHORIZED ACCESS");
			//}
			if(rtnValue != null)
			{
				returnValue = gson.toJson(rtnValue);
			}
		} catch (Throwable e) {
			String msgValue = value;
			if(value.length() > 2000) msgValue = value.substring(0, 1999);
			logger.log(Level.WARNING, String.format("GsonCommand: Key=%s, Value=%s", key, msgValue));
			logger.log(Level.WARNING, e.getMessage(), e);
			returnValue = "{\"ErrorMessage\":\""+ e.getMessage()+"\"}";
		} 
		return returnValue;
	}

	public void log() {
		if(logger.isLoggable(Level.FINER)){
			logger.log(Level.FINER, String.format("GsonCommand: Key=%s, Value=%s", key, value));
		}
		if(logger.isLoggable(Level.FINE)){
			Logger keyLogger = getLogger();
			if(keyLogger.isLoggable(Level.FINE)){
				keyLogger.log(Level.FINE, String.format("GsonCommand: Key=%s, Value=%s", key, value));
			}
		}
	}

	private Logger getLogger() {
		Logger rtnValue = loggers.get(key);
		if(rtnValue == null) rtnValue = Logger.getLogger(key);
		return rtnValue;
	}

	public static void createLoggers(String loggerClassNamesSeparatedByComas){
		String[] split = loggerClassNamesSeparatedByComas.split(",");
		for(String klass: split){
			if(loggers.get(klass) == null) loggers.put(klass, Logger.getLogger(klass));
		}
	}

	protected String getMessage() {
		String msg = "Error in Module " + this.getClass().getSimpleName();
		return msg;
	}
	
	public boolean compare(String carrierUserId, String userId) {
		int carrierUserIdLlength = carrierUserId.length();
		String carrier = carrierUserId.substring(0,2);
		if(carrierUserIdLlength>6) carrierUserId = carrierUserId.substring(carrierUserIdLlength-6,carrierUserIdLlength);
		int userIdLlength = userId.length();
		if(userIdLlength>6) userId = userId.substring(userIdLlength-6,userIdLlength);
		userId=String.format("%6s", userId).replace(' ', '0');
		carrierUserId=String.format("%6s", carrierUserId).replace(' ', '0');
		//carrierUserId = carrierUserId.substring(2);
		if(userId.equals(carrierUserId)) return true;	
		return SuperUserPermissionAssistant.get().isSuperUser(carrier+userId, carrier+carrierUserId);
	}

	@Override
	public String toString() {
		return "GsonCommand [key=" + key + ", userId=" + getUserId()+ ", value=" + value  + "]";
	}

	
}