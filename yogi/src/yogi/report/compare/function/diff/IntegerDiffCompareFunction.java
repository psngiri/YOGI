package yogi.report.compare.function.diff;


public class IntegerDiffCompareFunction extends BaseDiffCompareFunction<Integer>{
	public IntegerDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Object compare(Object[] objects) {
		Integer value1 = 0;
		if(objects[getDataSetIndex1()] != null) value1 = (Integer)objects[getDataSetIndex1()];
		Integer value2 = 0;
		if(objects[getDataSetIndex2()] != null) value2 = (Integer)objects[getDataSetIndex2()];
		return value2 - value1;
	}

}
