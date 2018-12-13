package yogi.report.server.command;

import yogi.base.io.StringToFileWriter;
import yogi.remote.client.app.CommandAdapter;

public class ImportFileForFilterCommand extends CommandAdapter<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int counter=0;
	private String fileString;
	private String fileName = "test";
	
	public ImportFileForFilterCommand(String userId) {
		super(userId);
	}

	@Override
	public String execute() {
		int id = counter++;
		String file = yogi.report.condition.BaseInCondition.UploadedDataFileLocation+ this.getUserId()+"/"+fileName+id;
//		System.out.println("File content: "+fileString);
		StringToFileWriter writer = new StringToFileWriter(file);
		writer.write(fileString);
		return "File:"+ this.getUserId()+"/"+fileName+id+":0";
		
	}

	
	
	
}
