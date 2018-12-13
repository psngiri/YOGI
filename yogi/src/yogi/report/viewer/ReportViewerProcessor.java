package yogi.report.viewer;

import java.util.List;

import yogi.base.app.BaseProcessor;
import yogi.report.template.BaseReportTemplate;

public abstract class ReportViewerProcessor<T> extends BaseProcessor{
	String title = "Report Viewer";
	public ReportViewerProcessor() {
	}

	public ReportViewerProcessor(String title) {
		super();
		this.title = title;
	}

	public void run() {
		new ReportViewer<T>(getItems(), getReportTemplate(), title);
	}

	public abstract BaseReportTemplate<T> getReportTemplate();

	public abstract List<T> getItems();

	public boolean isActivated() {
		return true;
	}

}
