package yogi.report.server.tuple.evaluators.complex;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.complex.StringAddComplexSeparatorTupleEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class StringAddComplexSeparatorUniqueSetEvaluator extends StringAddComplexSeparatorTupleEvaluator {
	
	private static String separator="/";
	
	public StringAddComplexSeparatorUniqueSetEvaluator(String name, char separator, int by, String... dependentColumnNames) {
		super(name, separator, by, dependentColumnNames);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] cols = super.getDependentColumnNames();
		Long numStops = tupleRow.getValue(cols[0]);
		int numLegs = numStops.intValue() + 1;
		Set<String> uniqueStrings = new HashSet<String>(numLegs);
		switch(numLegs){
		case 5:
			uniqueStrings.add(""+tupleRow.getValue(cols[5]));
		case 4:
			uniqueStrings.add(""+tupleRow.getValue(cols[4]));
		case 3:
			uniqueStrings.add(""+tupleRow.getValue(cols[3]));
		case 2:
			uniqueStrings.add(""+tupleRow.getValue(cols[2]));
		case 1:
			uniqueStrings.add(""+tupleRow.getValue(cols[1]));
			
		}
		String rtnValue = separator+uniqueStrings.stream().sorted().collect(Collectors.joining(separator))+separator;
		if(rtnValue.equals("//"))
			return rtnValue;
		else
			return rtnValue.replaceAll("//", "/");
			
	}
}
