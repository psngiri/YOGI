package yogi.period.interval.test;

import java.util.List;

import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;

import junit.framework.TestCase;

public class OrderlyIntervalCompressorTestDataFile extends TestCase{

	/**
	 * @param args
	 */
	public void test() {
		String inputFile = "data/sabre.ssim";
		String discontinueDate = "11NOV2008";
		String outputFile = inputFile + ".out.orderly";
		IntervalCompressor compressor = new IntervalCompressor(inputFile, outputFile, discontinueDate){

			@Override
			protected List<Interval> compressIntervals(List<Interval> intervals) {
				try {
					return Intervals.orderlyCompress(intervals);
				} catch (RuntimeException e) {
						System.out.println(intervals);
						throw e;
				}
			}
		};
		compressor.compress();
	}

}
 