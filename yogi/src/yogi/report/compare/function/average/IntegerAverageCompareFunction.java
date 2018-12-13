package yogi.report.compare.function.average;


public class IntegerAverageCompareFunction extends BaseAverageCompareFunction<Integer>{
	public IntegerAverageCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Integer compare(Object[] objects) {
		Integer value1 = 0;
		if(objects[getDataSetIndex1()] != null) value1 = (Integer)objects[getDataSetIndex1()];
		Integer value2 = 0;
		if(objects[getDataSetIndex2()] != null) value2 = (Integer)objects[getDataSetIndex2()];
		return (value1 + value2)/2;
	}

}
