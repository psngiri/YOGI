package yogi.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;
import yogi.base.app.ErrorReporterExtention;
import yogi.base.io.resource.SystemResource;

public class FileToErrorReporter implements Runnable {
	public static boolean RUN = true;
	private SystemResource resource;
	private boolean stop = false;
	private boolean flush = false;
	private Logger logger = null;
	private ErrorReporter errorReporter;
	private Thread thread;
	public FileToErrorReporter(SystemResource resource) {
		super();
		this.resource = resource;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void run() {
		if(!RUN) return;
		ErrorReporterExtention.push(errorReporter);
		while(!resource.canRead())
		{
			try {
				if(flush) return;
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String message = "Reporting Resource:" + resource.getName();
		if(logger != null)logger.info(message);
		ErrorReporter.get().info(message);
		String fileName = getFileName();
		BufferedReader reader = new BufferedReader(resource.getReader());
		try {
			String line;
			while((line = reader.readLine()) != null && !stop)
			{
				message = fileName +":" + line;
				if(logger != null)logger.info(message);
				ErrorReporter.get().info(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally
		{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			message = "Done Reporting Resource:" + resource.getName();
			if(logger != null)logger.info(message);
			ErrorReporter.get().info(message);
		}
	}

	private String getFileName() {
		int lastIndexOf = resource.getName().lastIndexOf('/')+1;
		String fileName = resource.getName().substring(lastIndexOf);
		return fileName;
	}

	public void start(){
		if(!RUN) return;
		errorReporter = ErrorReporter.get();
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		stop = true;
	}

	public void flush()
	{
		if(!RUN) return;
		try {
			flush = true;
			thread.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
