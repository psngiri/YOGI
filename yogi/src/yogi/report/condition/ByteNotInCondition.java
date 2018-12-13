package yogi.report.condition;


public class ByteNotInCondition extends ByteInCondition {

	public ByteNotInCondition(String value) {
		super(value);
	}
	
	public ByteNotInCondition(String value,char separator) {
		super(value, separator);
	}
	
	@Override
	public boolean satisfied(Byte data) {
		return !super.satisfied(data);
	}
}
