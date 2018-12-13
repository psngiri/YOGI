package yogi.report.condition.frequency;

import java.io.Serializable;

import yogi.base.Creator;
import yogi.base.io.Scanner;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyCreator;

public class FrequencyMultipleFormatScanner implements yogi.base.io.Scanner<Frequency, String>, Serializable{

	private static final long serialVersionUID = 1L;
	private Scanner<Frequency,String> scan1;
	private Scanner<Frequency,String> scan2;
	
	public FrequencyMultipleFormatScanner(Scanner<Frequency,String> s1, Scanner<Frequency,String> s2)
	{
		scan1 = s1;
		scan2 = s2;
	}

	@Override
	public Creator<Frequency> scan(String record) {
		Creator<Frequency> rtnValue1;
		Creator<Frequency> rtnValue2;
		rtnValue1 = scan1.scan(record);
		rtnValue2 = scan2.scan(record);
		FrequencyCreator freq = new FrequencyCreator();
		freq.setFrequency((byte) 0);
		if(rtnValue2.create()==freq.create())
			return rtnValue1;
		return rtnValue2;
	}
}
