package yogi.base.io;

public class StringToFileWriter {
	private String fileName;

	public StringToFileWriter(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public void write(String string)
	{
		Formatter<String> formatter = new Formatter<String>() {

			@Override
			public String format(String object) {
				return object;
			}
			
		};
		FileWriter<String> fileWriter = new FileWriter<String>(fileName, formatter);
		try {
			fileWriter.open();
			fileWriter.write(string);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}
		

	}
}
