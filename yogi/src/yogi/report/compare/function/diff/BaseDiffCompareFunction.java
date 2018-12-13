package yogi.report.compare.function.diff;

import yogi.report.compare.function.CompareFunction;

public abstract class BaseDiffCompareFunction<C> implements CompareFunction<C>{
	private int dataSetIndex1;
	private int dataSetIndex2;
	
	public BaseDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super();
		this.dataSetIndex1 = dataSetIndex1;
		this.dataSetIndex2 = dataSetIndex2;
	}

	public String getName() {
		StringBuilder sb = new StringBuilder();
		sb.append("Diff[").append(dataSetIndex1).append("|").append(dataSetIndex2).append("]");
		return sb.toString();
	}

	public int getDataSetIndex1() {
		return dataSetIndex1;
	}

	public int getDataSetIndex2() {
		return dataSetIndex2;
	}

}
