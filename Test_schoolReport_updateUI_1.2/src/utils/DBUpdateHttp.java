package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DBUpdateHttp extends Thread {
	
	
	
	
	
	String url;
	String sql;
	
	
	

	public DBUpdateHttp( String url ,String sql) {
		super();
		this.sql=sql;
		this.url = url;
		
	}

	@Override
	public void run() {
		
		doGet();
		
		
	}
	
	private  void doGet(){//GET方法发送请求,发送量较少,所有信息通过url暴露出来
		try {
			//GET方式只能通过url传参,这是其比较特殊之处
			url = url+"?sql="+URLEncoder.encode(sql,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			URL httpUrl =new URL(url);
			HttpURLConnection cnn = (HttpURLConnection) httpUrl.openConnection();
			cnn.setRequestMethod("GET");//设置请求方式为GET
			cnn.setReadTimeout(5000);
			
			//读取服务器给我们返回的数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(cnn.getInputStream()));
			String str;
			StringBuffer sb = new StringBuffer();
			while((str=reader.readLine())!=null){
				sb.append(str);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
