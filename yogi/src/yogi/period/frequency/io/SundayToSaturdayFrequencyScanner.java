package yogi.period.frequency.io;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;

public class SundayToSaturdayFrequencyScanner implements Scanner<Frequency, String>, Serializable {
	private FrequencyCreator frequencyCreator = new FrequencyCreator();
	private char[] onCharacters;
	public SundayToSaturdayFrequencyScanner(char onCharacter) {
		this(new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}

	public SundayToSaturdayFrequencyScanner(char... onCharacters)
	{
		super();
		this.onCharacters = onCharacters;
		if(onCharacters.length != 7) throw new RuntimeException("OnCharacters array length should be 7");
	}
	
	public SundayToSaturdayFrequencyScanner() {
		this('1');
	}

	public Creator<Frequency> scan(String record) {
		int frequency = 0;
		for(int i = 0; i < 7; i ++)
		{
			if(record.charAt(i) ==onCharacters[i])
			frequency += Math.pow(2, 6-i);
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}

}
