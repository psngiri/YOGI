package yogi.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import yogi.remote.gson.GsonCommand;
@MultipartConfig()
@WebServlet("/ImportCommandServlet")
public class ImportCommandServlet extends CommandServlet {

	private static final long serialVersionUID = 1L;
	private static final long NumberOfBytesPerGB = 1073741824;
	public ImportCommandServlet() throws IOException {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> items;
		byte[] data;
		String key = null,value = null,server = null,fileString = null;
		String returnValue = "";
		try {
			 items = upload.parseRequest(req);
			for(FileItem item :items){
				 String fieldName = item.getFieldName();
		    	    if(item.getSize()>NumberOfBytesPerGB){
		    	    	processGsonCommandReturnValue(req, resp, returnValue);
		    	    	return;
		    	    }
		    	    InputStream uploadedStream = item.getInputStream();
		    	    data = item.get();
		    	    
		    	    if(fieldName.equals("Key")) key = new String(data,"UTF-8");
		    	    if(fieldName.equals("Value")) value = new String(data,"UTF-8");
		    	    if(fieldName.equals("ServerType")) server = new String(data,"UTF-8");
		    	    if(fieldName.equals("File")){
		    	    	fileString = new String(data,"UTF-8");
		    	    }
		    	    uploadedStream.close();
			 }
			
		} catch (FileUploadException e) {
			processGsonCommandReturnValue(req, resp, returnValue);
			e.printStackTrace();
			return;
		}
		if(fileString==null || fileString.trim().isEmpty()){
			processGsonCommandReturnValue(req, resp, returnValue);
			return;
		}
		fileString = fileString.replace("\"", "");
		value = value.replaceAll("FILE_STRING", fileString);
		GsonCommand gsonCommand=new GsonCommand(key,value.toString(), getUserId(req));
		
		returnValue = executeCommand(server, gsonCommand);
		
		processGsonCommandReturnValue(req, resp, returnValue);
	}

	
	protected void processGsonCommandReturnValue(HttpServletRequest req,
			HttpServletResponse resp, String gsonCommandReturnValue)
			throws IOException {
		PrintWriter out = resp.getWriter();
		
		boolean jsonP = false;
		String cb = req.getParameter("callback");
		if (cb != null) {
		    jsonP = true;
		    resp.setContentType("text/html");
		} else {
		    resp.setContentType("text/x-json");
		}			//PrintWriter out = resp.getWriter();
		if (jsonP) {
			out.write(cb + "(");
		}
		if(gsonCommandReturnValue.trim().isEmpty()){
			gsonCommandReturnValue = "{\"success\":false,\"msg\":\"Excel upload failed.\"}";
		}else{
			gsonCommandReturnValue= "{\"success\":true,\"msg\":"+gsonCommandReturnValue+"}";
		}
		out.print(gsonCommandReturnValue);
		if (jsonP) {
			out.write(");");
		}
	}

}
