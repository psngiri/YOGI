package yogi.period.frequency.io;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;

/**
 * @author Vikram Vadavala
 *
 */
public class MondayToSundayVariableFrequencyScanner implements Scanner<Frequency, String> {
	private FrequencyCreator frequencyCreator = new FrequencyCreator();
	private char[] onCharacters;
	public MondayToSundayVariableFrequencyScanner(char onCharacter) {
		this(new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}
	
	public MondayToSundayVariableFrequencyScanner(char[] onCharacters)
	{
		super();
		this.onCharacters = onCharacters;
		if(onCharacters.length != 7) throw new RuntimeException("OnCharacters array length should be 7");
	}
	
	public MondayToSundayVariableFrequencyScanner() {
		this('1');
	}

	public Creator<Frequency> scan(String record) {
		int frequency = 0;
		for(int i = 0; i < record.length(); i++)
		{
			for(int j=0;j<7;j++) {
				if (record.charAt(i) == onCharacters[j]){
					int pow = 5 - j;
					if (j == 6)pow = 6;
					frequency += Math.pow(2, pow);
					break;
				}
			}
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}

}
