package yogi.report.function.frequency;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.report.compare.function.diff.BaseDiffCompareFunction;

public class FrequencyIntersectionCompareFunction extends BaseDiffCompareFunction<Frequency>{
	private int dataSetIndex1;
	private int dataSetIndex2;
	
	public FrequencyIntersectionCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Frequency compare(Object[] objects) {
		Frequency value1=FrequencyManager.NoDayFrequency;
		if(objects[getDataSetIndex1()] != null) value1 = (Frequency)objects[getDataSetIndex1()];
		Frequency value2=FrequencyManager.NoDayFrequency;
		if(objects[getDataSetIndex2()] != null) value2 = (Frequency)objects[getDataSetIndex2()];
		return Frequencies.intersection(value1, value2);	
	}


	public String getName() {
		StringBuilder sb = new StringBuilder();
		sb.append("Intersection[").append(dataSetIndex1).append(",").append(dataSetIndex2).append("]");
		return sb.toString();
	}

	public int getDataSetIndex1() {
		return dataSetIndex1;
	}

	public int getDataSetIndex2() {
		return dataSetIndex2;
	}
}
