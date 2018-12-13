package yogi.period.frequency.io;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;

public class MondayToSundayOptionalOffCharFrequencyScanner implements Scanner<Frequency, String>, Serializable{
	private FrequencyCreator frequencyCreator = new FrequencyCreator();
	private char[] onCharacters;
	public MondayToSundayOptionalOffCharFrequencyScanner(char onCharacter) {
		this(new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}
	
	public MondayToSundayOptionalOffCharFrequencyScanner(char... onCharacters)
	{
		super();
		this.onCharacters = onCharacters;
		if(onCharacters.length != 7) throw new RuntimeException("OnCharacters array length should be 7");
	}
	
	public MondayToSundayOptionalOffCharFrequencyScanner() {
		this('1');
	}

	public Creator<Frequency> scan(String record) {
		int frequency = 0;
		for(int i = 0; i < record.length(); i++) {
			if(record.charAt(i) 		== onCharacters[0]) frequency += Math.pow(2, 5);
			else if(record.charAt(i) 	== onCharacters[1]) frequency += Math.pow(2, 4);
			else if(record.charAt(i) 	== onCharacters[2]) frequency += Math.pow(2, 3);
			else if(record.charAt(i) 	== onCharacters[3]) frequency += Math.pow(2, 2);
			else if(record.charAt(i) 	== onCharacters[4]) frequency += Math.pow(2, 1);
			else if(record.charAt(i) 	== onCharacters[5]) frequency += Math.pow(2, 0);
			else if(record.charAt(i)	== onCharacters[6]) frequency += Math.pow(2, 6);			
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}
	
}
