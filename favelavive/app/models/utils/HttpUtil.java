package models.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class HttpUtil {
	
	public static String get(String address, int timeout) throws IOException, SocketTimeoutException{
		return get(address, null, timeout);
	}
	
	public static String get(String address, HashMap<String, String> params, int timeout) throws IOException, SocketTimeoutException{
		String urlParameters  = "";
		if(params != null && !params.keySet().isEmpty()){
			urlParameters += "?";
			for (String key : params.keySet()) {
				urlParameters +=  key+"="+params.get(key)+"&";
			}
		}
		
		URL url = new URL(address+urlParameters);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		HttpURLConnection.setFollowRedirects(false);
		conn.setConnectTimeout(timeout);
		conn.setReadTimeout(timeout);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		conn.connect();
		
		if(conn.getResponseCode() != 200){
			throw new IOException(conn.getResponseMessage());
		}
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while((line = rd.readLine()) != null){
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		return sb.toString();
	}
	
	public static String post(String address, int timeout) throws IOException, SocketTimeoutException{
		return post(address, null, timeout);
	}
	
	public static String post(String address, HashMap<String, String> params, int timeout) throws IOException, SocketTimeoutException{
		URL url = new URL(address);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		HttpURLConnection.setFollowRedirects(false);
		conn.setConnectTimeout(timeout);
		conn.setReadTimeout(timeout);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty("charset", "utf-8");
		
		if(params != null && !params.keySet().isEmpty()){
			String urlParameters  = "?";
			for (String key : params.keySet()) {
				urlParameters +=  key+"="+params.get(key)+"&";
			}
			byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
			conn.setDoOutput(true);
	        conn.getOutputStream().write(postData);
			conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
		}
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		conn.connect();
		
		if(conn.getResponseCode() != 200){
			throw new IOException(conn.getResponseMessage());
		}
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while((line = rd.readLine()) != null){
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		return sb.toString();
	}
}
