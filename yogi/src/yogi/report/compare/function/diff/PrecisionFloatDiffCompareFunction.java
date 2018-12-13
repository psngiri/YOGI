package yogi.report.compare.function.diff;

import yogi.base.util.PrecisionFloat;

public class PrecisionFloatDiffCompareFunction extends BaseDiffCompareFunction<PrecisionFloat>{
	public PrecisionFloatDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Object compare(Object[] objects) {
		PrecisionFloat value1 = (PrecisionFloat)objects[getDataSetIndex1()];
		PrecisionFloat value2 = (PrecisionFloat)objects[getDataSetIndex2()];
		if (value1==null && value2 ==null) return null;
		if(value1 == null)
			return new PrecisionFloat(value2.getValue(), value2.getDecimal());
		else if(value2 == null)
			return new PrecisionFloat(-value1.getValue(), value1.getDecimal());
		else 
			return new PrecisionFloat(value2.getValue() - value1.getValue(), value2.getDecimal());
	}

}
