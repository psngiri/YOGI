package yogi.base.util;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import yogi.base.io.FileToStringReader;
import yogi.base.io.resource.ResourceAssistant;
import yogi.base.io.resource.SystemResource;
import com.google.gson.Gson;
/**
 * @author Vikram Vadavala
 *
 */
public class JsonAssistant {
	private static JsonAssistant itsInstance = new JsonAssistant();
	private static Gson gson = new Gson();
	
	public static JsonAssistant get() {
		return itsInstance;
	}
	
	public String toJson(Object obj){
		return gson.toJson(obj);
	}
	
	public <T> T fromJson(String str, Class<T> klass){
		return gson.fromJson(str, klass);
	}
	
	public <T> T convert(LinkedHashMap<String, String> jsonObject, Class<T> klass){
		return gson.fromJson(toJson(jsonObject), klass);
	}
	
	public <T> T fromJsonFile(SystemResource resource, int headerLineCount, Class<T> klass){
		FileToStringReader fileToStringReader = new FileToStringReader(resource, headerLineCount);
		String read = fileToStringReader.read();
		return fromJson(read, klass);
	}
	
	public String toJson(Object obj, Type type){
		return gson.toJson(obj, type);
	}
	
	public <T> T fromJson(String str, Type type){
		if(str.trim().startsWith("{")||str.trim().startsWith("[")){
			return gson.<T>fromJson(str, type);
		}else{
			return this.<T>fromJsonFile(ResourceAssistant.getResource(str), 0, type);
		}
	}
	
	public <T> T convert(LinkedHashMap<String, String> jsonObject, Type type){
		return gson.<T>fromJson(toJson(jsonObject), type);
	}
	
	public <T> T fromJsonFile(SystemResource resource, int headerLineCount, Type type){
		FileToStringReader fileToStringReader = new FileToStringReader(resource, headerLineCount);
		String read = fileToStringReader.read();
		return this.<T>fromJson(read, type);
	}
	
}
