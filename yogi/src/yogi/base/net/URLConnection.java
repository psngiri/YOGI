package yogi.base.net;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
 
public class URLConnection {
 
	private String url;
	private boolean doPost;
	
	public URLConnection(String url, boolean doPost) {
		super();
		this.url = url;
		this.doPost = doPost;
	}

	public StringBuffer response() throws Exception{
		if(doPost) {
			return sendPost();
		}else
		{
			return sendGet();
		}
	}
 
	// HTTP GET request
	private StringBuffer sendGet() throws Exception {
		try {
			return processGet(url);	
		}catch(Exception e)
		{
			int indexOf = url.indexOf('?');
			if(indexOf != -1) {
				System.out.println(e.getMessage());
				System.out.println("Retrying encoding URL");
				return processGet(encodeParams(url));			
			}else {
				throw e;
			}
			
		}
	}

	private StringBuffer processGet(String url) throws Exception {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
		//con.setRequestProperty("Accept-Charset", "UTF-8");
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		
		return response;
	}

	private String encodeParams(String myUrl) throws UnsupportedEncodingException {
		int indexOf = myUrl.indexOf('?');
		if(indexOf != -1) {
			StringBuilder paramBuilder = new StringBuilder(myUrl.substring(0, indexOf+1));
			String params = myUrl.substring(indexOf+1);
			String[] paramArr = params.split("&");
			for(String param : paramArr) {
				String[] pair = param.split("=");
				paramBuilder.append(pair[0]);
				paramBuilder.append("=");
				if(pair[1].contains("&")) throw new RuntimeException("Param value cannot contain &");
				paramBuilder.append(URLEncoder.encode(pair[1], "UTF-8"));
				paramBuilder.append("&");
			}
			if(paramBuilder.length() != 0) paramBuilder.deleteCharAt(paramBuilder.length() - 1);
			myUrl = paramBuilder.toString();
		}
		return myUrl;
	}
 
	// HTTP POST request
	private StringBuffer sendPost() throws Exception {
		url = encodeParams(url);
		int indexOf = url.indexOf('?');
		URL obj = new URL(url.substring(0, indexOf));		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = url.substring(indexOf+1);
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
  
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		return response;
	}
 
}