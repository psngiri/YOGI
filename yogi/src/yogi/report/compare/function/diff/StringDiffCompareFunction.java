package yogi.report.compare.function.diff;


public class StringDiffCompareFunction extends BaseDiffCompareFunction<String>{
	public StringDiffCompareFunction(int dataSetIndex1, int dataSetIndex2) {
		super(dataSetIndex1, dataSetIndex2);
	}

	public String compare(Object[] objects) {
		if(objects[getDataSetIndex1()] == objects[getDataSetIndex2()]||(objects[getDataSetIndex1()] != null && objects[getDataSetIndex1()].equals(objects[getDataSetIndex2()]))) return "";
		if(objects[getDataSetIndex1()]==null)
		{
			if(((String)objects[getDataSetIndex2()]).isEmpty()) return"";
			return "|"+ objects[getDataSetIndex2()];
		}
		if(objects[getDataSetIndex2()]==null)
		{
			if(((String)objects[getDataSetIndex1()]).isEmpty()) return"";
			return objects[getDataSetIndex1()] +"|";
		}
		return objects[getDataSetIndex1()] +"|"+ objects[getDataSetIndex2()];
		
	}

}
