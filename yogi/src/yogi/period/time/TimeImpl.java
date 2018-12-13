package yogi.period.time;

class TimeImpl implements Time {
	short time;
	TimeImpl(short time) {
		super();
		this.time = time;
	}
	public short getTime() {
		return time;
	}
	public int compareTo(Time o) {
		return time - o.getTime();
	}
	@Override
	public String toString() {
		return String.valueOf(getTime());
	}
	
}
