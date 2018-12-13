package yogi.period.frequency.io;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;

public class SundayToSaturdayFrequencyMultiScanner implements Scanner<Frequency, String> {
	private FrequencyCreator frequencyCreator = new FrequencyCreator();
	private char[] onCharacters;
	public SundayToSaturdayFrequencyMultiScanner(char... onCharacter) {
		onCharacters = onCharacter;
	}
	
	public SundayToSaturdayFrequencyMultiScanner() {
		this('1');
	}

	public Creator<Frequency> scan(String record) {
		int frequency = 0;
		for(int i = 0; i < 7; i ++)
		{
			for (int j=0; j < onCharacters.length; j++)
			{
				if(record.charAt(i) ==onCharacters[j])
					frequency += Math.pow(2, 6-i);
			}
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}

}
