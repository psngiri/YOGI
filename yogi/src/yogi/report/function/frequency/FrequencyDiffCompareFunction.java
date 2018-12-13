package yogi.report.function.frequency;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.report.compare.function.diff.BaseDiffCompareFunction;

public class FrequencyDiffCompareFunction extends BaseDiffCompareFunction<Frequency>{
	public FrequencyDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Frequency compare(Object[] objects) {
		Frequency value1=FrequencyManager.NoDayFrequency;
		if(objects[getDataSetIndex1()] != null) value1 = (Frequency)objects[getDataSetIndex1()];
		Frequency value2=FrequencyManager.NoDayFrequency;
		if(objects[getDataSetIndex2()] != null) value2 = (Frequency)objects[getDataSetIndex2()];
		return Frequencies.subtract(value1, value2);	
	}

}
