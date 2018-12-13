package yogi.report.app;

import yogi.base.io.db.QueryReader;
import yogi.base.io.resource.ClassPathResource;
import yogi.base.io.resource.SystemResource;
import yogi.base.util.JsonAssistant;
import yogi.report.server.Query;

public class GenerateQueryFileReportProcessor extends GenerateQueryReportProcessor {
	private SystemResource queryResource = new ClassPathResource("query.txt", this.getClass());
	QueryReader queryReader=null;
	public GenerateQueryFileReportProcessor(String reportFileName) {
		super(reportFileName);
	}

	@Override
	public void run() {
		Query query = JsonAssistant.get().fromJson(getQuery(), Query.class);
		this.setQuery(query);
		super.run();
	}

	protected SystemResource getQueryResource() {
		return queryResource;
	}

	protected String getQuery() {
		return getQueryReader().read();
	}

	protected QueryReader getQueryReader() {
		if(queryReader == null){
			queryReader = new QueryReader(getQueryResource());
		}
		return queryReader;
	}

}
