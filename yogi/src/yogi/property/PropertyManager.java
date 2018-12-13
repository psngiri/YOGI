package yogi.property;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;
import yogi.base.util.converter.StringToObjectConverter;
import yogi.base.util.logging.Logging;

public class PropertyManager{
	private static Logger logger = Logging.getLogger(PropertyManager.class);
	public static Level level = Level.INFO;
	
	public static boolean assignProperty(Property property)
	{
		try {
			PropertyName propertyName = property.getPropertyName();
			Class<?> c = Class.forName(propertyName.getClassName());
			if(propertyName.isField())
			{
				Field field = c.getField(propertyName.getName());
				Object value = convert(field, property.getValue());
				field.set(null, value);
				if(logger.isLoggable(level)) logger.log(level, String.format("Assigned: %1$s.%2$s to %3$s", propertyName.getClassName(), propertyName.getName(), property.getValue()));
			}else
			{
				Class<?>[] parameterTypes = new Class[] {String.class};
				Method method = c.getMethod(propertyName.getName(), parameterTypes);
				Object[] arguments = new Object[] {property.getValue()};
				method.invoke(null, arguments);
				if(logger.isLoggable(level)) logger.log(level, String.format("Assigned: %1$s.%2$s to %3$s", propertyName.getClassName(), propertyName.getName(), property.getValue()));
			}
		} catch (Throwable e) {
			ErrorReporter.get().error("Could not assign Property : ", property , e);
			return false;
		}
		return true;
	}
	
	private static Object convert(Field field, String value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class<?> type = field.getType();
		return StringToObjectConverter.convertTo(value, type, field.getGenericType());
	}	

	@SuppressWarnings("unchecked")
	public static <T> T getPropertyValue(PropertyName propertyName) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class<?> klass = Class.forName(propertyName.getClassName());
		Object value;
		if(propertyName.isField())
		{
			Field field = klass.getField(propertyName.getName());
			value = field.get(null);
		}else
		{
			Method method = klass.getMethod(propertyName.getName());
			value = method.invoke(null);
		}
		return (T) value;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getPropertyValue(String propertyName){
		try {
			return (T)PropertyManager.getPropertyValue(PropertyAssistant.getPropertyname(propertyName));
		} catch (Exception e) {
			throw new RuntimeException("Could not find Property Value for Property: " + propertyName, e);
		}
	}
}
