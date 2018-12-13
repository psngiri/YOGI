package yogi.base.util;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class URLFileDownloader {

	public static void download(URL url, String fileName){
		try(FileOutputStream fos = new FileOutputStream(fileName);ReadableByteChannel rbc = Channels.newChannel(url.openStream())) {			
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			throw new RuntimeException("Error Downlading file", e);
		}
	}
}
