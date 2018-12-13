package yogi.report.compare.function.diff;

import yogi.base.util.FractionDouble;

public class FractionDoubleDiffCompareFunction extends BaseDiffCompareFunction<FractionDouble>{
	public FractionDoubleDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public FractionDouble compare(Object[] objects) {
		FractionDouble value1 = null;
		if(objects[getDataSetIndex1()] != null) value1 = (FractionDouble)objects[getDataSetIndex1()];
		FractionDouble value2 = null;
		if(objects[getDataSetIndex2()] != null) value2 = (FractionDouble)objects[getDataSetIndex2()]; 
		if(value1 == null) return value2;
		if(value2 == null) return new FractionDouble(value1.getNumerator()*-1,value1.getDenominator());
		
		double numerator=value2.getNumerator()*value1.getDenominator() - value1.getNumerator()*value2.getDenominator();
		double denominator=value2.getDenominator()*value1.getDenominator();
		
		return new FractionDouble(numerator, denominator);
	}

}
