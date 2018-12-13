package yogi.optimize.sm.io;

import java.util.Scanner;

import yogi.base.io.CreatorScanner;
import yogi.optimize.sm.Sm;

public class SmScanner implements CreatorScanner<Sm, SMVariableCreator, String> {

	public SMVariableCreator scan(String record) {
		Scanner scanner = new Scanner(record);
		SMVariableCreator creator = new SMVariableCreator();
		creator.setVariableName(scanner.next());
		creator.setSolutionValue(Float.parseFloat(scanner.next()));
		return creator;
	}

}
