package yogi.report.compare.function.average;


public class LongAverageCompareFunction extends BaseAverageCompareFunction<Long>{
	public LongAverageCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Long compare(Object[] objects) {
		Long value1 = 0L;
		if(objects[getDataSetIndex1()] != null) value1 = (Long)objects[getDataSetIndex1()];
		Long value2 = 0L;
		if(objects[getDataSetIndex2()] != null) value2 = (Long)objects[getDataSetIndex2()];
		return (value1 + value2)/2;
	}

}
