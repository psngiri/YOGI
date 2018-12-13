package yogi.period.interval.test;

import junit.framework.TestCase;

public class IntervalCompressorTestDataFile extends TestCase{

	/**
	 * @param args
	 */
	public void test() {
		String inputFile = "data/sabre.ssim";
		String discontinueDate = "11NOV2008";
		String outputFile = inputFile + ".out";
		IntervalCompressor compressor = new IntervalCompressor(inputFile, outputFile, discontinueDate);
		compressor.compress();
	}

}
 