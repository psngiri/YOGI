package yogi.report.compare.function.diff;


public class DoubleDiffCompareFunction extends BaseDiffCompareFunction<Double>{
	public DoubleDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Double compare(Object[] objects) {
		Double value1 = 0.0;
		if(objects[getDataSetIndex1()] != null) value1 = (Double)objects[getDataSetIndex1()];
		Double value2 = 0.0;
		if(objects[getDataSetIndex2()] != null) value2 = (Double)objects[getDataSetIndex2()];
		return value2 - value1;
	}

}
