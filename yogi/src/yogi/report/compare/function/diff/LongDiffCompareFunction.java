package yogi.report.compare.function.diff;


public class LongDiffCompareFunction extends BaseDiffCompareFunction<Long>{
	public LongDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Long compare(Object[] objects) {
		Long value1 = 0L;
		if(objects[getDataSetIndex1()] != null) value1 = (Long)objects[getDataSetIndex1()];
		Long value2 = 0L;
		if(objects[getDataSetIndex2()] != null) value2 = (Long)objects[getDataSetIndex2()];
		return value2 - value1;
	}

}
