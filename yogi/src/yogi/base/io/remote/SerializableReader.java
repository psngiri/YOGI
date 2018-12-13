package yogi.base.io.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;

import yogi.base.Util;
import yogi.base.app.ErrorReporter;
import yogi.base.io.resource.SystemResource;


public class SerializableReader extends Reader implements Serializable
{
   static final long serialVersionUID = 1244952470388397765L;
   protected char buf[];
   protected int pos;
   protected int mark = 0;
   protected int count;
   public SerializableReader(SystemResource systemResource) throws IOException
   {
	  super();
	  if(systemResource.canRead())
	  {
		  ErrorReporter.get().info("Reading from Resource - "+ systemResource.getName());
		  read(systemResource.getReader());
	  }else
	  {
		  ErrorReporter.get().warning("Could not read from Resource - "+ systemResource.getName());
	  }
   }
   
   public SerializableReader(Reader reader) throws IOException
   {
      read(reader);

   }
	private void read(Reader reader) throws IOException {
		BufferedReader in = new BufferedReader(reader);
	      String line;
	      StringBuffer sb = new StringBuffer();
	      while ((line = in.readLine()) != null)
	      {
	    	 sb.append(line).append(Util.getLineSeparator());
	      }
	
	      reader.close();
	
	      this.buf = sb.toString().toCharArray();
	      this.pos = 0;
	      this.count = this.buf.length;
	      ErrorReporter.get().info(String.format("Read %s bytes", count));
	}

   /**
    * Close the stream.  Once a stream has been closed, further read(),
    * ready(), mark(), or reset() invocations will throw an IOException.
    * Closing a previously-closed stream, however, has no effect.
    *
    * @throws java.io.IOException If an I/O error occurs
    */
   public void close() throws IOException
   {
      // Do nothing
   }

   /**
    * Read characters into a portion of an array.  This method will block
    * until some input is available, an I/O error occurs, or the end of the
    * stream is reached.
    *
    * @param cbuf Destination buffer
    * @param off  Offset at which to start storing characters
    * @param len  Maximum number of characters to read
    * @return The number of characters read, or -1 if the end of the
    *         stream has been reached
    * @throws java.io.IOException If an I/O error occurs
    */
   public int read(char cbuf[], int off, int len) throws IOException
   {
      if (cbuf == null)
      {
         throw new NullPointerException();
      }
      else if ((off < 0) || (off > cbuf.length) || (len < 0) ||
            ((off + len) > cbuf.length) || ((off + len) < 0))
      {
         throw new IndexOutOfBoundsException();
      }

      if (pos >= count)
      {
         return -1;
      }
      if (pos + len > count)
      {
         len = count - pos;
      }
      if (len <= 0)
      {
         return 0;
      }
      System.arraycopy(buf, pos, cbuf, off, len);
      pos += len;
      return len;
   }

}