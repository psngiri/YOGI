package yogi.period.frequency.io;

import yogi.base.io.Formatter;
import yogi.period.frequency.Frequency;

public class SundayToSaturdaySplitFrequencyFormatter implements Formatter<Frequency> {

	private char offCharacter;
	private char[] onCharacters;

	public SundayToSaturdaySplitFrequencyFormatter() {
		this('0', '1');
	}

	public SundayToSaturdaySplitFrequencyFormatter(char offCharacter, char onCharacter) {
		this(offCharacter, new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}

	public SundayToSaturdaySplitFrequencyFormatter(char offCharacter, char[] onCharacters) {
		super();
		this.offCharacter = offCharacter;
		this.onCharacters = onCharacters;
	}

	public String format(Frequency frequency) {
		StringBuilder stringBuilder = new StringBuilder();
		byte myFrequency = frequency.getValue();
		int mask = Frequency.SUNDAY;
		for(int i = 0; i < 7; i ++) {
			if (i > 0) stringBuilder.append(' ');
			if((myFrequency & mask) != 0 ) stringBuilder.append(onCharacters[i]);
			else stringBuilder.append(offCharacter);
			mask = mask >> 1;
		}
		return stringBuilder.toString();
	}
}
