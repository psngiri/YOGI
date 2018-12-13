package yogi.report;

import java.io.File;
import java.util.List;

import yogi.base.io.BaseHeaderFileWriter;
import yogi.base.io.Writer;
import yogi.base.util.immutable.ImmutableList;

public abstract class ReportWriter<T> extends BaseHeaderFileWriter implements Writer{
	private ReportGenerator<T> reportGenerator;
	
	public ReportWriter(File file) {
		super(file);
		initialize();
	}

	public ReportWriter(String fileName) {
		super(fileName);
		initialize();
	}

	private void initialize() {
		reportGenerator = new ReportGenerator<T>(new MyLineWriter<T>(this));
	}

	public ReportGenerator<T> getReportGenerator() {
		return reportGenerator;
	}

	abstract public List<T> getItems(); 
	
	public void write() {
		boolean open = open();
		if(!open) return;
		try {
			ReportGenerator<T> myReportGenerator = getReportGenerator();
			myReportGenerator.setItems(new ImmutableList<T>(getItems()));
			myReportGenerator.generateReport();
		} finally{
			close();
		}
	}

	public boolean isActivated() {
		return true;
	}
		
	static class MyLineWriter<T> implements LineWriter
	{
		ReportWriter<T> reportWriter;
		
		public MyLineWriter(ReportWriter<T> reportWriter) {
			super();
			this.reportWriter = reportWriter;
		}

		public void writeLine(String string) {
			reportWriter.writer.println(string);
		}
		
	}
}
