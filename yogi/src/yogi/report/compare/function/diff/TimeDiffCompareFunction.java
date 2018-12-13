package yogi.report.compare.function.diff;

import yogi.period.time.Time;
import yogi.period.time.TimeCreator;
import yogi.period.time.TimeUtils;

public class TimeDiffCompareFunction extends BaseDiffCompareFunction<Time>{
	public TimeDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Time compare(Object[] objects) {
		Time value1 = null;
		Time value2 = null;
		if(objects[getDataSetIndex1()] != null) value1 = (Time)objects[getDataSetIndex1()];
		if(objects[getDataSetIndex2()] != null) value2 = (Time)objects[getDataSetIndex2()];
		if(value1==null) return value2;
		if(value2==null) return null;
		TimeCreator tc = new TimeCreator();
		tc.setTime(Math.abs(TimeUtils.getDistance(value1, value2)));
		return tc.create();
	}
}
