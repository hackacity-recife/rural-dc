package models.injects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import models.utils.StringUtil;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class CaptchaValidator {
    private static final String API_KEY;
    private static final String ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";
    private static final int TIMEOUT = 5000;

    static {
        Config load = ConfigFactory.load();
        API_KEY = load.getString("api.captcha.key");
    }
    
    public static boolean validate(String captcha, String remoteIp) throws IOException, JSONException {                
        URL url = new URL(ENDPOINT);		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		HttpURLConnection.setFollowRedirects(false);
		conn.setConnectTimeout(TIMEOUT);
		conn.setReadTimeout(TIMEOUT);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");

		OutputStream os = conn.getOutputStream();
		BufferedWriter writer = new BufferedWriter(
		        new OutputStreamWriter(os, "UTF-8"));
		writer.write("secret="+API_KEY+"&response="+captcha+"&remoteip="+remoteIp);
		writer.flush();
		writer.close();
		os.close();
		
		conn.connect();
				
		if(conn.getResponseCode() != 200){
			return false;
		}
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null){
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		line = sb.toString();
		if(StringUtil.isNullOrEmpty(line)) return false;
				
		JSONObject jResultado = new JSONObject(line);
		return jResultado.getBoolean("success");
    }
}
