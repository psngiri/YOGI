package yogi.base.util.test;

import java.net.MalformedURLException;
import java.net.URL;

import yogi.base.util.URLFileDownloader;

import junit.framework.TestCase;

public class URLFileDownloaderTest1 extends TestCase {

	public void test() throws MalformedURLException{
		URLFileDownloader.download(new URL("http://cpappt01.qcorpaa.aa.com:7090/Export/AA627674.8"), "C:/temp/test.csv");
	}
}
