package yogi.base.util.converter;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import yogi.base.util.JsonAssistant;
import yogi.property.MyBooleanParser;

public class StringToObjectConverter {
	private static final Map<Class<?>, Class<?>> primitiveWrappers = new HashMap<Class<?>, Class<?>>(17);
	private static final String[] methods = {"valueOf", "parse"};

	public static Object convertTo(String value, Class<?> type){
		return convertTo(value, type, null);
	}
	
	public static Object convertTo(String value, Class<?> type, Type genericType){
		Class<?> conversionType = conversionTypeFor(type);
		if (String.class.equals(conversionType))
			return value;

		try {
			Method converter = getMethod(conversionType, new Class<?>[] {String.class});
			return converter.invoke(null, value);
		} catch (Exception e) {
			try {
				if(genericType != null){
				    return JsonAssistant.get().fromJson(value, genericType);
				}else{
						return JsonAssistant.get().fromJson(value, conversionType);
				}
			} catch (Exception e1) {
				throw new RuntimeException(e.toString()+ " or " + e1.toString());
			}
		}
	}

	private static Method getMethod(Class<?> type, Class<?>[] parameterTypes) throws NoSuchMethodException {
		for (String methodName : methods) {
			try {
				return type.getMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException ignored) {
				// try next method
			}
		}

		throw new NoSuchMethodException(Arrays.asList(methods).toString());
	}
	
	private static Class<?> conversionTypeFor(Class<?> type) {
		return type.isPrimitive() ? getWrapperType(type) : type;
	}

	private static Class<?> getWrapperType(Class<?> primitiveType) {
		if (primitiveWrappers.isEmpty()) {
			primitiveWrappers.put(Character.TYPE, Character.class);
			primitiveWrappers.put(Byte.TYPE, Byte.class);
			primitiveWrappers.put(Short.TYPE, Short.class);
			primitiveWrappers.put(Integer.TYPE, Integer.class);
			primitiveWrappers.put(Long.TYPE, Long.class);
			primitiveWrappers.put(Float.TYPE, Float.class);
			primitiveWrappers.put(Double.TYPE, Double.class);
			primitiveWrappers.put(Boolean.TYPE, MyBooleanParser.class);
		}

		return primitiveWrappers.get(primitiveType);
	}
}
