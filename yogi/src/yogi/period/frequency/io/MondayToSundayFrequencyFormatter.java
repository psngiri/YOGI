package yogi.period.frequency.io;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.period.frequency.Frequency;

public class MondayToSundayFrequencyFormatter implements Formatter<Frequency>, Serializable {

	private static final long serialVersionUID = -5660215137056101362L;
	private char offCharacter;
	private char[] onCharacters;
	private boolean ignoreOffCharacter = false;
	public MondayToSundayFrequencyFormatter() {
		this('0', '1');
	}

	public MondayToSundayFrequencyFormatter(char offCharacter, char onCharacter) {
		this(offCharacter, new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}

	public MondayToSundayFrequencyFormatter(char offCharacter, char[] onCharacters) {
		super();
		this.offCharacter = offCharacter;
		this.onCharacters = onCharacters;
	}

	public MondayToSundayFrequencyFormatter(char offCharacter, char[] onCharacters, boolean ignoreOffCharacter) {
		this(offCharacter, onCharacters);
		this.ignoreOffCharacter = ignoreOffCharacter;
	}
	
	public String format(Frequency frequency) {
		StringBuilder stringBuilder = new StringBuilder();
		byte myFrequency = frequency.getValue();
		int mask = Frequency.MONDAY;
		for(int i = 0; i < 6; i ++)
		{
			appendCharacter(stringBuilder, myFrequency, mask, i);
			mask = mask >> 1;
		}
		mask = Frequency.SUNDAY;
		appendCharacter(stringBuilder, myFrequency, mask, 6);
		
		return stringBuilder.toString();
	}

	private void appendCharacter(StringBuilder stringBuilder, byte myFrequency, int mask, int i) {
		if((myFrequency & mask) != 0 ) stringBuilder.append(onCharacters[i]);
		else if(!isIgnoreOffCharacter())stringBuilder.append(offCharacter);
	}

	public boolean isIgnoreOffCharacter() {
		return ignoreOffCharacter;
	}

	public void setIgnoreOffCharacter(boolean ignoreOffCharacter) {
		this.ignoreOffCharacter = ignoreOffCharacter;
	}
}
