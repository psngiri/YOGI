package yogi.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import yogi.base.app.multithread.ThreadPoolExecutor;

public class Util {
	public static <T> void purge(List<T> items, Selector<T> selector) {
		synchronized(items)
		{
			int i = 0;
			int size = items.size();
			for(int j = 0; j < size; j++)
			{
				T object = items.get(j);
				if(!selector.select(object))
				{
					if(i != j) items.set(i, object);
					i++;
				}
			}
			for(int j = size-1; j >= i; j--)
			{
				items.remove(j);
			}
		}
	}
	
	public static void checkAndCreateFileAlongWithParentDirsAsRequired(File file)
	{
		if (!file.canWrite()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteFile(File file)
	{
		if(file.isDirectory())
		{
			for(File childFile: file.listFiles())
			{
				deleteFile(childFile);
			}
		}
		file.delete();
	}
	
	public static String getLineSeparator() {
		return System.getProperty("line.separator");
	}

	public static String getTestDataDirectoryPath()
	{
		return "data";
	}
	
	public static String convert(List<?> lines) {
		if (lines.isEmpty()) return "";
		StringWriter stringWriter = new StringWriter();
		PrintWriter pw = new PrintWriter(stringWriter);
		int lastIndex = lines.size() -1;
		for(int i = 0; i < lastIndex; i ++)
		{
			pw.println(lines.get(i));
		}
		pw.print(lines.get(lastIndex));
		return stringWriter.toString();
	}

	public static double round(double value, int precission){
		double pow = Math.pow(10, precission);
		double round = Math.round(value * pow);
		return round/pow;
	}

	public static StringBuilder pad(String value, int length, boolean left, char padChar){
		return pad(new StringBuilder(length), value, length, left, padChar);
	}
	
	
	public static StringBuilder pad(StringBuilder stringBuilder, String value, int length, boolean left, char padChar)
	{
		if(value == null) value = "";
		int width = value.length();
		if(width> length){
			stringBuilder.append(value.substring(0, length));
		}else if( width == length){
			stringBuilder.append(value);
		}else{
			if(left){
				int padLenght = length - width;
				for(int i = 0; i < padLenght; i ++){
					stringBuilder.append(padChar);
				}
				stringBuilder.append(value);
			}else{
				stringBuilder.append(value);
				int padLenght = length - width;
				for(int i = 0; i < padLenght; i ++){
					stringBuilder.append(padChar);
				}
			}
		}
		return stringBuilder;
	}
	
	public static StringBuilder pad(double value, int precision, int length, boolean left, char padChar){
		return pad(new StringBuilder(length), value, length, precision, left, padChar);
	}
	
	
	public static StringBuilder pad(StringBuilder stringBuilder, double item, int length, int precision,boolean left, char padChar)
	{   
		String value = String.valueOf(round(item,precision));
		 int indexOfE =value.indexOf("E"); 
		 if(indexOfE >=0){
			 long longValue = Double.valueOf(item).longValue();
			 value = String.valueOf(longValue);
			 value=value+".0";
		 }
		 int width = value.length();
 		int decimal = value.indexOf('.')+1;
 		if(length == 0){
 			length = decimal+precision;
 		}
		int myLength = length;
		
		int dp = decimal + precision;
		if(width > dp){
			value = value.substring(0, dp);
		}
		width = value.length();
		length = length - dp + width;
		if(precision == 0 ) {
			
			value = value.substring(0,value.indexOf('.'));
			length = myLength;
			width = value.length();
		}
		
		if(width> length){
			stringBuilder.append(value.substring(0, length));
			for(int i = 0; i < myLength-length; i ++){
				stringBuilder.append('0');
			}
		}else if( width == length){
			stringBuilder.append(value);
			for(int i = 0; i < myLength-length; i ++){
				stringBuilder.append('0');
			}
		}else{
			if(left){
				int padLenght = length - width;
				for(int i = 0; i < padLenght; i ++){
					stringBuilder.append(padChar);
				}
				stringBuilder.append(value);
				for(int i = 0; i < myLength-length; i ++){
					stringBuilder.append('0');
				}
			}else{
				stringBuilder.append(value);
				for(int i = 0; i < myLength-length; i ++){
					stringBuilder.append('0');
				}
				int padLenght = length - width;
				for(int i = 0; i < padLenght; i ++){
					stringBuilder.append(padChar);
				}
			}
		}
		
		return stringBuilder;
	}
	
	public static String trimEnd(String value){
		int i = value.length()-1;
		while(i >= 0 && value.charAt(i) == ' '){
			i--;
		}
		return value.substring(0, i+1);
	}
	
	public static String cut(float amt,int p, int size){
 		String value = String.valueOf(amt);
 		int d = value.indexOf('.');
 		int length = d+p;
		if(size < length) return value.substring(length);
		else if(size - d < p){
			
		}
 		return value;
  	}
	
	public static <T> List<List<T>> getSubLists(List<T> objects){
		int numberOfThreads = ThreadPoolExecutor.NumberOfThreads;
		List<List<T>> mulItems = new ArrayList<List<T>>();
		int objectsperthread = objects.size()/numberOfThreads;
		int start = 0;
		int end = 0;
		for(int i = 0; i < numberOfThreads-1; i ++)
		{
			end = start + objectsperthread;
			mulItems.add(objects.subList(start, end));
			start = end;
		}
		mulItems.add(objects.subList(start, objects.size()));
		return mulItems;
	}
	
	public static <T> T[] combine(T[]... lists)
	{
		List<T> rtnValue = new ArrayList<T>();
		for(T[] list: lists){
			for(T item: list)
			{
				rtnValue.add(item);
			}
		}
		@SuppressWarnings("unchecked")
		T[] array = (T[]) rtnValue.toArray();
		return array;
	}
	
    public  static <C> C getInstance(Class<C> klass, Object... parameters) {
        C instance = null;
        try {
               Class<?>[] parameterTypes = new Class[parameters.length];
               for(int i = 0; i < parameters.length; i ++ ){
                      parameterTypes[i] = Object.class;
               }
               Constructor<C> constructor = klass.getConstructor(parameterTypes);
               instance = constructor.newInstance(parameters);
        } catch (Exception e) {
               throw new RuntimeException("Could not construct class: " + klass.getCanonicalName(), e);
        } 
        return instance;
  }

	
}
