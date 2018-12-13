package yogi.tools.builder;

import java.util.HashSet;
import java.util.Set;

public class BuilderData {
	private String basePackageName;
	private String objectName;
	private String packageName;
	private String camelCaseObjectName;
	private String sourceDirectory;
	private Class base;
	private Class baseManager;
	private Class baseAssistant;
	private Set<String> convertSet;
	public static Set<String> allFileTypes = getAllFileTypes();

	public BuilderData() {
		super();
		try {
			setBase("yogi.base.relationship.RelationshipObject");
			setBaseManager("yogi.base.relationship.RelationshipManager");
			setBaseAssistant("yogi.base.relationship.RelationshipAssistant");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		convertSet = new HashSet<String>();
	}
	
	public BuilderData(boolean convertAll)
	{
		this();
		convertSet.addAll(allFileTypes);
	}

	private static Set<String> getAllFileTypes() {
		Set<String> fileTypes = new HashSet<String>();
		fileTypes.add(".java");
		fileTypes.add("Impl.java");
		fileTypes.add("Assistant.java");
		fileTypes.add("Creator.java");
		fileTypes.add("Factory.java");
		fileTypes.add("Manager.java");
		fileTypes.add("Validator.java");
		fileTypes.add("IoFormatter.java");
		fileTypes.add("IoReader.java");
		fileTypes.add("IoScanner.java");
		fileTypes.add("IoWriter.java");
		fileTypes.add("IoRecordSelector.java");
		fileTypes.add("IoFinder.java");
		fileTypes.add("IoCreatorObjectProcessor.java");
		fileTypes.add("IoDbRecordSelector.java");
		fileTypes.add("IoDbReader.java");
		fileTypes.add("IoDbScanner.java");
		fileTypes.add("IoDbWriter.java");
		fileTypes.add("IoDbFormatter.java");
		return fileTypes;
	}

	public Class getBaseAssistant() {
		return baseAssistant;
	}

	public void setBaseAssistant(String baseAssistantName) throws ClassNotFoundException {
		this.baseAssistant = Class.forName(baseAssistantName);
	}

	public Class getBaseManager() {
		return baseManager;
	}

	public void setBaseManager(String baseManagerName) throws ClassNotFoundException {
		this.baseManager = Class.forName(baseManagerName);
	}

	public Class getBase() {
		return base;
	}

	public void setBase(String baseName) throws ClassNotFoundException {
		this.base = Class.forName(baseName);
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}

	public String getCamelCaseObjectName() {
		if(camelCaseObjectName == null)
		{
			String myObjectName = getObjectName();
			if(myObjectName == null) return null;
			camelCaseObjectName = myObjectName.substring(0,1).toLowerCase() + myObjectName.substring(1);
		}
		return camelCaseObjectName;
	}

	public void setCamelCaseObjectName(String camelCaseObjectName) {
		this.camelCaseObjectName = modifyIfNeeded(camelCaseObjectName);
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		if(this.objectName != null && !this.objectName.equals(objectName))
		{
			packageName = null;
			camelCaseObjectName = null;
		}
		this.objectName = objectName;
	}

	public String getPackageName() {
		if(packageName == null) return getLowerCaseObjectName();
		return packageName;
	}

	private String getLowerCaseObjectName() {
		String myObjectName = getObjectName();
		if(myObjectName == null) return null;
		return myObjectName.toLowerCase();
	}

	public void setPackageName(String packageName) {
		this.packageName = modifyIfNeeded(packageName);
	}

	private String modifyIfNeeded(String packageName) {
		String myObjectName = getObjectName();
		if(myObjectName != null && myObjectName.toLowerCase().equals(packageName)) packageName = null;
		return packageName;
	}

	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}
	
	public void convertFileType(String fileType)
	{
		if(!allFileTypes.contains(fileType)) throw new RuntimeException("Not a valid file type:" + fileType + " \nValid types:" + allFileTypes);
		convertSet.add(fileType);
	}
	
	public void dontConvertFileType(String fileType)
	{
		convertSet.remove(fileType);
	}
	
	public boolean convert(String fileType)
	{
		return convertSet.contains(fileType);
	}
}
