package yogi.report.viewer.compare;

import java.util.List;

import yogi.base.app.BaseProcessor;
import yogi.report.compare.template.BaseCompareReportTemplate;

public abstract class CompareReportViewerProcessor<T> extends BaseProcessor{

	public CompareReportViewerProcessor() {
	}

	public void run() {
		new CompareReportViewer<T>(getReportTemplate(), getItems());
	}

	public abstract BaseCompareReportTemplate<T> getReportTemplate();

	public abstract List<T>[] getItems();

	public boolean isActivated() {
		return true;
	}

}
