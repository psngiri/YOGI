package yogi.report.server.tuple.evaluators.binary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowBinaryShortEvaluator extends TupleRowBaseEvaluator<Long> {


	public TupleRowBinaryShortEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Long parse(FileChannelReader fileChannelReader) {
		
		try {
			int size = getRange().getEnd() - getRange().getStart();
			long rtnValue = (long) ByteBuffer.wrap(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()),0,size).order(ByteOrder.BIG_ENDIAN).getShort();
			return rtnValue;
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
