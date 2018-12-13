package yogi.base.io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class IoConnector {
	private Reader reader;
	private Writer writer;
	private boolean closeWriterOnCompletion = true;
	private boolean closeReaderOnCompletion = true;
	
	public IoConnector(Reader reader, Writer writer) {
		super();
		this.reader = reader;
		this.writer = writer;
	}
	
	public void start()
	{
		MyRunnable myRunnable = new MyRunnable(new BufferedReader(reader),new PrintWriter(writer), isCloseWriterOnCompletion(), isCloseReaderOnCompletion());
		new Thread(myRunnable).start();
	}
	
	public void run()
	{
		MyRunnable myRunnable = new MyRunnable(new BufferedReader(reader),new PrintWriter(writer), isCloseWriterOnCompletion(), isCloseReaderOnCompletion());
		myRunnable.run();
	}
	
	private static class MyRunnable implements Runnable
	{
		private BufferedReader reader;
		private PrintWriter writer;
		private boolean closeWriterOnCompletion;
		private boolean closeReaderOnCompletion;

		protected MyRunnable(BufferedReader reader, PrintWriter writer, boolean closeWriterOnCompletion, boolean closeReaderOnCompletion) {
			super();
			this.reader = reader;
			this.writer = writer;
			this.closeWriterOnCompletion = closeWriterOnCompletion;
			this.closeReaderOnCompletion = closeReaderOnCompletion;
		}

		public void run() {
			try {
				String line = reader.readLine();
				while(line != null)
				{
					writer.println(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally
			{
				if(closeWriterOnCompletion)writer.close();
				try {
					if(closeReaderOnCompletion)reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public boolean isCloseWriterOnCompletion() {
		return closeWriterOnCompletion;
	}

	public void setCloseWriterOnCompletion(boolean closeWriterOnCompletion) {
		this.closeWriterOnCompletion = closeWriterOnCompletion;
	}

	public boolean isCloseReaderOnCompletion() {
		return closeReaderOnCompletion;
	}

	public void setCloseReaderOnCompletion(boolean closeReaderOnCompletion) {
		this.closeReaderOnCompletion = closeReaderOnCompletion;
	}
	
}
