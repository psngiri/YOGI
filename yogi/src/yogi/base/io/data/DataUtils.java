package yogi.base.io.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataUtils {
	
	public static String readString(DataInput record, int lengthOfString) throws IOException{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< lengthOfString; i ++){
			sb.append(record.readChar());
		}
		return sb.toString();
	}

	public static char readCharacterByte(DataInput record) throws IOException{
		byte readByte = record.readByte();
		return (char)readByte;
	}
	
	public static String readStringBytes(DataInput record, int lengthOfString) throws IOException{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i< lengthOfString; i ++){
			char readCharacterByte = readCharacterByte(record);
			sb.append(readCharacterByte);
		}
		return sb.toString();
	}
	
	public static void writeCharacterByte(DataOutput record, char character) throws IOException{
		record.writeByte(character);
	}
	
	public static void writeStringBytes(DataOutput record, String string) throws IOException{
		for(int i = 0; i< string.length(); i ++){
			writeCharacterByte(record, string.charAt(i));
		}
	}
	
}
