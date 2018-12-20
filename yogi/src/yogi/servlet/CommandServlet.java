package yogi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yogi.auth.AuthUser;
import yogi.base.util.JsonAssistant;
import yogi.remote.CommandException;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.remote.gson.GsonCommand;

@WebServlet("/CommandServlet")
public class CommandServlet extends HttpServlet {

	private static final long serialVersionUID = -735983759544639221L;

	public CommandServlet() throws IOException {
		MultiServerCommandExecutor.get().initialize();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String returnValue = "";
		String key = req.getParameter("Key");
		StringBuffer value = new StringBuffer(req.getParameter("Value"));
		String server = req.getParameter("ServerType");
		String sendAuthUser = req.getParameter("SendAuthUser");
		String userId = getUserId(req);
		if(value.toString().indexOf("userId")==-1 && userId!=null ){
			if(value.toString().length()>2){
				value.insert(value.length()-1,",\"userId\":\""+userId+"\"");
			}else{
				value.insert(value.length()-1,"\"userId\":\""+userId+"\"");
			}
		}
		
		
		if(sendAuthUser != null){
			AuthUser authUser = getAuthUser(req); 
			StringBuffer jsonBuffer = new StringBuffer(JsonAssistant.get().toJson(authUser));
			if (value.length()==2) jsonBuffer.insert(0, "\"authUser\":");
			else jsonBuffer.insert(0, ",\"authUser\":");
			value.insert(value.length()-1, jsonBuffer.toString());
		}
		
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
		    resp.setContentType("text/javascript");
		} else {
		    resp.setContentType("application/x-json");
		}			//PrintWriter out = resp.getWriter();
		if (jsonP) {
			out.write(cb + "(");
		}
		if(gsonCommandReturnValue.isEmpty()){
			gsonCommandReturnValue = "{\"ErrorMessage\":\"Empty Response from Server\"}";
		}
		out.print(gsonCommandReturnValue);
		if (jsonP) {
			out.write(");");
		}
	}
	
	protected String getUserId(HttpServletRequest request){
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			if (headerName.equalsIgnoreCase("SM_USER")){
				String userId =request.getHeader(headerName);
				int userIdLlength = userId.length();
				if(userIdLlength>6) userId = userId.substring(userIdLlength-6,userIdLlength);
				return userId;
			}
		}
		return null;
	}

	private AuthUser getAuthUser(HttpServletRequest request){
		
		Enumeration<String> headerNames = request.getHeaderNames();
				
		String firstName = null;
		String lastName = null;
		String role = null;
		String userId = null;
		String email = null;
		String sessionId = null;
		String transactionId = null;
		String location = null;
		String companyCode = "AA";
		
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			//System.out.println("Headers received:"+headerName+ " Value:"+request.getHeader(headerName));
			if (headerName.equalsIgnoreCase("FIRST_NAME")) firstName = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("LAST_NAME")) lastName = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("SM_ROLE")) role = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("SM_USER")) userId = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("EMAIL")) email = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("SM_SERVERSESSIONID")) sessionId = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("SM_TRANSACTIONID")) transactionId = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("HTTP_LOCATION")) location = request.getHeader(headerName);
			if (headerName.equalsIgnoreCase("COMPANY_CODE")) companyCode = request.getHeader(headerName);
		}
		
		AuthUser rtnValue= new AuthUser(firstName, lastName, userId,
				role, email, companyCode, location,
				sessionId, transactionId);
		return rtnValue;
				
	}

	protected String executeCommand(String serverType, GsonCommand command){
		try {
			return MultiServerCommandExecutor.get().execute(serverType,command);
		} catch (CommandException e) {
			e.printStackTrace();
			return "{\"ErrorMessage\":\""+e.getMessage()+"\"}";
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
