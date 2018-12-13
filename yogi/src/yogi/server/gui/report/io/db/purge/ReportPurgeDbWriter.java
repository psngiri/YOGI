package yogi.server.gui.report.io.db.purge;

import yogi.base.io.resource.db.DbResource;
import yogi.server.base.purge.io.db.PurgeDbORDbFileWriter;
import yogi.server.gui.report.Report;
import yogi.server.gui.report.ReportFactory;
import yogi.server.gui.util.GuiAssistant;

/**
 * @author Vikram Vadavala
 *
 */
public class ReportPurgeDbWriter extends PurgeDbORDbFileWriter<Report> {
	
	
	public ReportPurgeDbWriter(DbResource resource){
		super(resource, ReportFactory.get(), new ReportPurgeDbFormatter(), Report.class, GuiAssistant.PurgeDeleteDir);
	}
	
}
