package yogi.period.frequency.io;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;
import yogi.period.frequency.FrequencyManager;

/**
 * @author Vikram Vadavala
 *
 */

public class MondayToSundayRangeVariableFrequencyScanner implements Scanner<Frequency, String> {
	private FrequencyCreator frequencyCreator = new FrequencyCreator();
	private char[] onCharacters;
	public MondayToSundayRangeVariableFrequencyScanner(char onCharacter) {
		this(new char[]{onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter, onCharacter});
	}
	
	public MondayToSundayRangeVariableFrequencyScanner(char[] onCharacters)
	{
		super();
		this.onCharacters = onCharacters;
		if(onCharacters.length != 7) throw new RuntimeException("OnCharacters array length should be 7");
	}
	
	public MondayToSundayRangeVariableFrequencyScanner() {
		this('1');
	}
	
	
	public Creator<Frequency> scan(String string){
		int frequency = 0;
		int begin = Integer.parseInt(string.trim().substring(0, 1));
		int end = Integer.parseInt(string.trim().substring(string.length()-1, string.trim().length()));
		int diff = begin-end >= 0 ? begin-end : end-begin;
		char endChar = string.charAt(string.length()-1);
		int j = 0;
		int k = 0;
		for(int i = 0;i <= diff;i ++){
			k = begin+i;
			if(k > onCharacters.length)
			{
				frequency += Frequencies.shift(FrequencyManager.get().getFrequencyFromDayOfWeek(j+1),1).getValue();
				if(onCharacters[j] == endChar){break;}
				j++;
			}else{
				frequency += Frequencies.shift(FrequencyManager.get().getFrequencyFromDayOfWeek(k),1).getValue();
			}
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}
	
	
	/*public Creator<Frequency> scan(String string){
		int frequency = 0;
		int begin = Integer.parseInt(string.trim().substring(0, 1));
		int end = Integer.parseInt(string.trim().substring(string.length()-1, string.trim().length()));
		if(begin > onCharacters.length || end > onCharacters.length){frequencyCreator.setFrequency((byte) frequency);return frequencyCreator;}
		int diff = begin-end >= 0 ? begin-end : end-begin;
		char endChar = string.charAt(string.length()-1);
		int j = 0;
		int k = 0;
		for(int i = 0;i <= diff;i ++){
			k = (begin-1)+i;
			if(k >= onCharacters.length)
			{
				int pow = 5 - j;
				if (j == 6)pow = 6;
				frequency += Math.pow(2, pow);
				if(onCharacters[j] == endChar){break;}
				j++;
			}
			else{
				int pow = 5 - k;
				if (k == 6)pow = 6;
				frequency += Math.pow(2, pow);
			}
		}
		frequencyCreator.setFrequency((byte) frequency);
		return frequencyCreator;
	}*/
	
	
//	public Creator<Frequency> scan(String range) {
//	int frequency = 0;
//	String record = constructRecordFromRange(range);
//	for(int i = 0; i < record.length(); i++)
//	{
//		for(int j = 0;j < 7;j ++) {
//			if (record.charAt(i) == onCharacters[j]){
//				int pow = 5 - j;
//				if (j == 6)pow = 6;
//				frequency += Math.pow(2, pow);
//				break;
//			}
//		}
//	}
//	frequencyCreator.setFrequency((byte) frequency);
//	return frequencyCreator;
//}
	
	
//	private String constructRecordFromRange(String string){
//		int begin = Integer.parseInt(string.trim().substring(0, 1));
//		int end = Integer.parseInt(string.trim().substring(string.length()-1, string.trim().length()));
//		int diff = begin-end >= 0 ? begin-end : end-begin;
//		char endChar = string.charAt(string.length()-1);
//		char[] temp = new char[diff+1];
//		int j = 0;
//		for(int i = 0;i <= diff;i ++){
//			if(((begin-1)+i) >= onCharacters.length)
//			{
//				if(onCharacters[j] == endChar){temp[i] = endChar;break;}
//				temp[i] = onCharacters[j];j++;
//			}
//			else{temp[i] = onCharacters[(begin-1)+i];}
//		}
//		return new String(temp).trim();
//	}

}
