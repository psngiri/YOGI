package yogi.property.parameter.io;

import java.util.Scanner;

import yogi.base.Creator;
import yogi.base.app.ErrorReporter;
import yogi.property.Property;
import yogi.property.parameter.ParameterAssistant;


public class ParameterScanner implements yogi.base.io.Scanner<Property, String> {
	public Creator<Property> scan(String record) {
		try {
			Scanner scanner = new Scanner(record);
			String parameterName = scanner.next();
			String parameterValue = scanner.nextLine();
			return ParameterAssistant.create(parameterName, parameterValue);
		} catch (RuntimeException e) {
			ErrorReporter.get().warning("Could not Scan Record", record);
			return null;
		}
	}
}
