package yogi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CsvCommandServlet")
public class CsvCommandServlet extends CommandServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CsvCommandServlet() throws IOException {
		super();
	}

	@Override
	protected void processGsonCommandReturnValue(HttpServletRequest req,
			HttpServletResponse resp, String gsonCommandReturnValue)
			throws IOException {
		String fileName = req.getParameter("MyFileName");
		resp.setContentType("text");
		resp.setHeader("Content-Disposition",  "attachment; filename="+fileName );
		gsonCommandReturnValue=gsonCommandReturnValue.replace("],[", "\n");
		gsonCommandReturnValue=gsonCommandReturnValue.substring(2, gsonCommandReturnValue.length()-2);
		PrintWriter out = resp.getWriter();
		out.print(gsonCommandReturnValue);
	}

}
