package yogi.tools.builder;

import java.io.PrintWriter;

import yogi.base.io.BaseHeaderFileWriter;
import yogi.base.util.converter.FileConverter;
import yogi.base.util.converter.SearchReplaceRecordConverter;

public class Builder {
	private BuilderData builderData;
	private String packageLocation;
	private String ioPackageLocation;
	private String ioDbPackageLocation;
	private String inputPackageLocation = "src/com/aa/cp/tools/builder/template/bto/BtoTemplate";
	private String inputIoPackageLocation = "src/com/aa/cp/tools/builder/template/bto/io/BtoTemplate";
	private String inputIoDbPackageLocation = "src/com/aa/cp/tools/builder/template/bto/io/db/BtoTemplate";
	private FileConverter fileConverter = new FileConverter();
	
	public static class MyWriter extends BaseHeaderFileWriter
	{

		public MyWriter(String fileName) {
			super(fileName);
			open();
		}
	
		public PrintWriter getWriter()
		{
			return writer;
		}
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		if(args.length < 2 || args.length > 9) {
			System.out.println("Usage: Builder basePackageName objectName [packageName camelCaseObjectName][sourceDirectory][baseName baseManagerName baseAssistantName baseReaderName]");
			return;
		}
		String sourceDirectory = null;
		BuilderData builderData = new BuilderData();
		int index = args.length;
		if(index > 5) index = index - 4;
		switch(index)
		{
		case 5:
			sourceDirectory = args[4];
		case 4:
			builderData.setBasePackageName(args[0]);
			builderData.setObjectName(args[1]);
			builderData.setPackageName(args[2]);
			builderData.setCamelCaseObjectName(args[3]);
			break;
		case 3:
			sourceDirectory = args[2];
		case 2:
			builderData.setBasePackageName(args[0]);
			builderData.setObjectName(args[1]);
			break;
		}
		if(sourceDirectory == null) sourceDirectory = "src";
		builderData.setSourceDirectory(sourceDirectory);
		if(args.length > 5)
		{
			builderData.setBase(args[index + 0]);
			builderData.setBaseManager(args[index + 1]);
			builderData.setBaseAssistant(args[index + 2]);
		}
		Builder builder = new Builder(builderData);
		builder.writeFiles();
	}	
	
	public Builder(BuilderData builderData) {
		super();
		this.builderData = builderData;
		this.computeLocations();
	}

	private void computeLocations() {
		packageLocation = builderData.getSourceDirectory()  + "/" + builderData.getBasePackageName().replace('.','/') + "/" + builderData.getPackageName().replace('.','/') + "/";
		ioPackageLocation = packageLocation + "io/";		
		ioDbPackageLocation = packageLocation + "io/db/";		
	}

	public void writeFiles()
	{
		setupFileConverter();
		
		convertObject();
		convertImpl();
		convertAssistant();
		convertCreator();
		convertFactory();
		convertManager();
		convertValidator();
		convertFormatter();
		convertWriter();
		convertScanner();
		convertReader();
		convertRecordSelector();
		convertFinder();
		convertCreatorObjectProcesssor();
		convertDbScanner();
		convertDbReader();
		convertDbRecordSelector();
		convertDbWriter();
		convertDbFormatter();
	}

	private void convertCreatorObjectProcesssor() {
		convertIo("IoCreatorObjectProcessor.java");
	}

	private void convertFinder() {
		convertIo("IoFinder.java");
	}

	private void convertRecordSelector() {
		convertIo("IoRecordSelector.java");
	}

	private void convertDbRecordSelector() {
		convertIoDb("IoDbRecordSelector.java");
	}

	private void convertDbReader() {
		convertIoDb("IoDbReader.java");
	}

	private void convertDbScanner() {
		convertIoDb("IoDbScanner.java");
	}

	private void convertDbWriter() {
		convertIoDb("IoDbWriter.java");
	}

	private void convertDbFormatter() {
		convertIoDb("IoDbFormatter.java");
	}

	private void convertWriter() {
		convertIo("IoWriter.java");
	}

	private void convertScanner() {
		convertIo("IoScanner.java");
	}

	private void convertReader() {
		convertIo("IoReader.java");
	}

	private void convertFormatter() {
		convertIo("IoFormatter.java");
	}

	private void convertValidator() {
		convert("Validator.java");
	}

	private void convertManager() {
		Class baseManager = builderData.getBaseManager();
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("yogi.base.relationship.RelationshipManager", baseManager.getName()));
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("RelationshipManager", baseManager.getSimpleName()));
		convert("Manager.java");
	}

	private void convertFactory() {
		convert("Factory.java");
	}

	private void convertCreator() {
		convert("Creator.java");
	}

	private void convertAssistant() {
		Class baseAssistant = builderData.getBaseAssistant();
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("yogi.base.relationship.RelationshipAssistant", baseAssistant.getName()));
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("RelationshipAssistant", baseAssistant.getSimpleName()));
		convert("Assistant.java");
	}

	private void convertImpl() {
		Class base = builderData.getBase();
		if(base == null)
		{
			fileConverter.clear();
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("import yogi.base.BaseImpl;", ""));
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("extends BaseImpl<BtoTemplate> ", ""));
		}else
		{
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("yogi.base.Base", base.getName()));
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("Base", base.getSimpleName()));
		}
		convert("Impl.java");
	}

	private void convertObject() {
		Class base = builderData.getBase();
		if(base == null)
		{
			fileConverter.clear();
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("import yogi.base.Base;", ""));
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("extends Base ", ""));
		}else
		{
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("yogi.base.Base", base.getName()));
			fileConverter.addRecordConverter(new SearchReplaceRecordConverter("Base", base.getSimpleName()));
		}
		convert(".java");
	}

	private void setupFileConverter()
	{
		fileConverter.clear();
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("yogi.tools.builder.template", builderData.getBasePackageName()));
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("BtoTemplate", builderData.getObjectName()));
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("btoTemplate", builderData.getCamelCaseObjectName()));
		fileConverter.addRecordConverter(new SearchReplaceRecordConverter("bto", builderData.getPackageName()));		
	}

	private void convert(String suffix) {
		if(! builderData.convert(suffix)) return;
		fileConverter.setInputFileName(inputPackageLocation + suffix);
		fileConverter.setOutputFileName(packageLocation + builderData.getObjectName() + suffix);
		fileConverter.convert();
	}
	
	private void convertIo(String suffix) {
		if(! builderData.convert(suffix)) return;
		suffix = suffix.substring(2);
		fileConverter.setInputFileName(inputIoPackageLocation + suffix);
		fileConverter.setOutputFileName(ioPackageLocation + builderData.getObjectName() + suffix);
		fileConverter.convert();
	}
	
	private void convertIoDb(String suffix) {
		if(! builderData.convert(suffix)) return;
		suffix = suffix.substring(2);
		fileConverter.setInputFileName(inputIoDbPackageLocation + suffix);
		fileConverter.setOutputFileName(ioDbPackageLocation + builderData.getObjectName() + suffix);
		fileConverter.convert();
	}
}
