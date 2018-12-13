package yogi.period.frequency;

public interface Frequency extends Comparable<Frequency>{
	public static final byte SUNDAY = 64;
	public static final byte MONDAY = 32;
	public static final byte TUESDAY = 16;
	public static final byte WEDNESDAY = 8;
	public static final byte THURSDAY = 4;
	public static final byte FRIDAY = 2;
	public static final byte SATURDAY = 1;
	byte getValue();	
}
