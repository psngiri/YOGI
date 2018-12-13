package yogi.base.io.resource;

public class ResourceAssistant {
	private static String ZipExtention = ".zip";
	private static String GZipExtention = ".gz";
	public static SystemResource getResource(String fileName)
	{
		if(fileName.startsWith("*"))
		{
			return new ClassPathResource(fileName.substring(1));
		}
		return getFileResource(fileName);
	}
	
	public static FileResource getFileResource(String fileName) {
		if(fileName.length()-4 > 0 && fileName.substring(fileName.length()-4).equalsIgnoreCase(ZipExtention)){
			return new ZipFileResource(fileName);
		}if(fileName.length()-3 > 0 && fileName.substring(fileName.length()-3).equalsIgnoreCase(GZipExtention)){
			return new GzipFileResource(fileName);
		}else{
			return new FileResource(fileName);
		}
	}
}
