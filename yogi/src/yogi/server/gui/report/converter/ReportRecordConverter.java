package yogi.server.gui.report.converter;

import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.db.DbResource;
import yogi.base.util.loader.DbLoader;

public class ReportRecordConverter extends DbLoader {
	public ReportRecordConverter(DbResource resource) {
		super(resource, resource, new ReportConverterDbFormatter());
	}

	@Override
	protected QueryReader getQueryReader() {
		QueryReader queryReader = super.getQueryReader();
		queryReader.addVariable("TableName", ReportConverterDbFormatter.TableName);
		return queryReader;
	}

}
