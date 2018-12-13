package yogi.report.compare.function.average;


public class FloatAverageCompareFunction extends BaseAverageCompareFunction<Float>{
	public FloatAverageCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public Object compare(Object[] objects) {
		Float value1 = 0f;
		if(objects[getDataSetIndex1()] != null) value1 = (Float)objects[getDataSetIndex1()];
		Float value2 = 0f;
		if(objects[getDataSetIndex2()] != null) value2 = (Float)objects[getDataSetIndex2()];
		return (value1 + value2)/2;
	}

}
