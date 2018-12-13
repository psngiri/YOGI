package yogi.base.io.resource;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Vikram Vadavala
 *
 */
public class ZipFileResource extends FileResource {

	public ZipFileResource(File file) {
		super(file);
	}

	public ZipFileResource(String fileName) {
		super(fileName);
	}

	public Reader getReader(){
		try {
			File file = getFile();
			ZipFile zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			if(entries.hasMoreElements())
			{
				return new InputStreamReader(zipFile.getInputStream(entries.nextElement()));
			}
			throw new Exception("Zip File " + file.getAbsolutePath() + " is empty");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
