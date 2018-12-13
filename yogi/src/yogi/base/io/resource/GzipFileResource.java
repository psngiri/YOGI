package yogi.base.io.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

import yogi.base.Util;
import yogi.base.util.logging.Logging;
import yogi.property.PropertyReplacer;

public class GzipFileResource extends FileResource {
	public static int DecompressionSleepTime = 100;
	public static String WorkingDirectory = "D:/temp/";
	private static Logger logger = Logging.getLogger(GzipFileResource.class);
	private static Set<String> workingDecompressionFiles  = new HashSet<String>();
	
	public GzipFileResource(String fileName) {
		super(fileName);
		int length = fileName.length();
		String extension = fileName.substring(length-3, length);
		String ext = ".gz";
		if(!extension.equals(ext)) throw new RuntimeException("Only handle gzip files with .gz extension :" + fileName);
	}

	public File getFile() {
		if(file == null) {
			String myFileName = locallySlashedPath(new PropertyReplacer().replaceVariables(fileName));
			if(!addToWorkingDecompressionFiles(myFileName)){
				waitIfFileIsBeingDecompressed(myFileName);
			}
			try{
				String fileNameWithoutExtension = myFileName.substring(0, myFileName.length()-3);
				File myFile = new File(fileNameWithoutExtension);
				if(!myFile.canRead()){
					String fileNameWithoutExtensionWD = WorkingDirectory+fileNameWithoutExtension;
					myFile = new File(fileNameWithoutExtensionWD);
					if(!myFile.canRead()){
						File gipFile = new File(myFileName);
						if(!gipFile.canRead()) throw new RuntimeException("Could not find either the file or its compressed file:"+fileNameWithoutExtension);
						decompressFile(myFileName, fileNameWithoutExtensionWD);
					}
				}
				file = myFile;
			}finally{
				workingDecompressionFiles.remove(myFileName);
			}			
		}
		return file;
	}

	private void decompressFile(String myFileName, String fileNameWithoutExtensionWD) {
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(new File(fileNameWithoutExtensionWD));
		try(
				FileOutputStream fos = new FileOutputStream(fileNameWithoutExtensionWD);
				GZIPInputStream gip = new GZIPInputStream(new FileInputStream(myFileName));
				ReadableByteChannel gch = Channels.newChannel(gip);
				)
		{
			long currentTimeMillis = System.currentTimeMillis();
			fos.getChannel().transferFrom(gch, 0, Long.MAX_VALUE);
			logger.info("Time taken to unzip " + myFileName +" in milliseconds:"+ (System.currentTimeMillis()-currentTimeMillis));
		} catch (Exception e) {
			throw new RuntimeException("Could not extract gzip file to location :"+fileNameWithoutExtensionWD, e);
		}
	}

	private synchronized boolean addToWorkingDecompressionFiles(String myFileName) {
		return workingDecompressionFiles.add(myFileName);
	}

	private static void waitIfFileIsBeingDecompressed(String myFileName) {
		while(workingDecompressionFiles.contains(myFileName)){
			try {
				Thread.sleep(DecompressionSleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
