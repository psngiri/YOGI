package yogi.report.condition.frequency;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayFrequencyFormatter;

public class FrequencyFormatter implements Formatter<Frequency>, Serializable {

	private static final long serialVersionUID = 923453098957811259L;

	public static Character Ob = '[';
	public static Character Cb = ']';
	private static MondayToSundayFrequencyFormatter formatter = new MondayToSundayFrequencyFormatter('.', new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
	
	@Override
	public String format(Frequency frequency) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Ob);
		stringBuilder.append(formatter.format(frequency));
		stringBuilder.append(Cb);
		return stringBuilder.toString();
	}

}
