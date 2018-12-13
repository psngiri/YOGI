package yogi.period.frequency.io;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;

public class MondayToSundayFrequencyScanner implements Scanner<Frequency, String>, Serializable {
	
	private static final long serialVersionUID = -290540335444690152L;
	private FrequencyCreator frequencyCreator = new FrequencyCreator();
	private char[] onCharacters;
	public MondayToSundayFrequencyScanner(char onCharacter) {
		this(new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}
	
	public MondayToSundayFrequencyScanner(char... onCharacters)
	{
		super();
		this.onCharacters = onCharacters;
		if(onCharacters.length != 7) throw new RuntimeException("OnCharacters array length should be 7");
	}
	
	public MondayToSundayFrequencyScanner() {
		this('1');
	}

	public Creator<Frequency> scan(String record) {
		int frequency = 0;
		for(int i = 0; i < 7; i ++)
		{
			if(record.charAt(i) ==onCharacters[i])
			{
				int pow = 5-i;
				if(i == 6) pow = 6;
				frequency += Math.pow(2, pow);
			}
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}

}
