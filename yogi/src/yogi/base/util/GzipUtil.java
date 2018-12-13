package yogi.base.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import yogi.base.Util;
import yogi.base.util.logging.Logging;

public class GzipUtil {
	private static Logger logger = Logging.getLogger(GzipUtil.class);

	public static void decompressFile(String fileNameToDeCompress, String fileNameToDeCompressTo) {
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(new File(fileNameToDeCompressTo));
		try(
				FileOutputStream fos = new FileOutputStream(fileNameToDeCompressTo);
				GZIPInputStream gip = new GZIPInputStream(new FileInputStream(fileNameToDeCompress));
				ReadableByteChannel gch = Channels.newChannel(gip);
				)
		{
			long currentTimeMillis = System.currentTimeMillis();
			fos.getChannel().transferFrom(gch, 0, Long.MAX_VALUE);
			logger.info("Time taken to unzip " + fileNameToDeCompress +" in milliseconds:"+ (System.currentTimeMillis()-currentTimeMillis));
		} catch (Exception e) {
			throw new RuntimeException("Could not extract gzip file to location :"+fileNameToDeCompressTo, e);
		}
	}
	
	public static void compressFile(String fileName) {
		String fileNameToCompressTo = fileName+".gz";
		Util.checkAndCreateFileAlongWithParentDirsAsRequired(new File(fileName));
		try(
				FileInputStream is = new FileInputStream(fileName);
				GZIPOutputStream os = new GZIPOutputStream(new FileOutputStream(fileNameToCompressTo));
				WritableByteChannel gch = Channels.newChannel(os);
				)
		{
			long currentTimeMillis = System.currentTimeMillis();
			is.getChannel().transferTo(0, Long.MAX_VALUE, gch);
			logger.info("Time taken to zip " + fileName +" in milliseconds:"+ (System.currentTimeMillis()-currentTimeMillis));
		} catch (Exception e) {
			throw new RuntimeException("Could not create gzip file to location :"+fileNameToCompressTo, e);
		}
	}
	
	public static byte[] compress(String data) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
		GZIPOutputStream gzip = new GZIPOutputStream(bos);
		gzip.write(data.getBytes());
		gzip.close();
		byte[] compressed = bos.toByteArray();
		bos.close();
		return compressed;
	}
	
	public static String decompress(byte[] compressed) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
		GZIPInputStream gis = new GZIPInputStream(bis);
		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		gis.close();
		bis.close();
		return sb.toString();
	}

}
