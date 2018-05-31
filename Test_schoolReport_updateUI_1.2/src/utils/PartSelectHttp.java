/**
 * 
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.yjt.test_schoolreport.PersonlActivity;

import bean.ScoreOfStudent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Administrator
 *
 */
public class PartSelectHttp extends Thread {
	private String url;
	private Handler handler;
	 ListView listview;
	private String sql;
	Context context;
	public TextView txtv;
	private int whichterms;
	public Class otherActivity;
 	
	
	
	public PartSelectHttp(String url, Handler handler, ListView listview,Context context,String sql,int whichterms,Class otherActivity) {
		super();
		this.sql=sql;
		this.url = url;
		this.handler = handler;
		this.listview = listview;
		this.context = context;
		this.whichterms=whichterms;
		this.otherActivity=otherActivity;
	}
	
	//测试一下向服务器传数据,接受数据
	public PartSelectHttp(String url,String sql) {
		super();
		this.url = url;
		this.sql=sql;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		//GET方式只能通过url传参,这是其比较特殊之处
		try {
			url =  url+"?sql="+URLEncoder.encode(sql,"UTF-8");
			
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
		
		final ArrayList<ScoreOfStudent> list = parseJson(sb.toString());
		
		
		if(list!=null){
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
						//将获取的json数据映射到listview
					ArrayAdapter<ScoreOfStudent> adapter = new ArrayAdapter<ScoreOfStudent>(context,android.R.layout.simple_expandable_list_item_1, list);
						listview.setAdapter(adapter);
						listview.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								Intent intentToInfo = new Intent(context,otherActivity);
								Bundle bun1 = new Bundle();
								bun1.putString("name",list.get(arg2).getName() );
								bun1.putSerializable("scoreofstudeent", list.get(arg2));
								intentToInfo.putExtras(bun1);
								context.startActivity(intentToInfo);
							}
						});
					
					
				}
			});
		}
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		
		
		
	}
	
//	private  void doGet(){//GET方法发送请求,发送量较少,所有信息通过url暴露出来
//		try {
//			//GET方式只能通过url传参,这是其比较特殊之处
//			url =  url+"?sql="+URLEncoder.encode(sql,"UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			URL httpUrl =new URL(url);
//			HttpURLConnection cnn = (HttpURLConnection) httpUrl.openConnection();
//			cnn.setRequestMethod("GET");//设置请求方式为GET
//			cnn.setReadTimeout(5000);
//			
//			//读取服务器给我们返回的数据
//			BufferedReader reader = new BufferedReader(new InputStreamReader(cnn.getInputStream()));
//			String str;
//			StringBuffer sb = new StringBuffer();
//			while((str=reader.readLine())!=null){
//				sb.append(str);
//			}
//			
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	private ArrayList<ScoreOfStudent> parseJson(String json){
		try {
			
			JSONObject object=new JSONObject(json);
			int  result = object.getInt("result");
			ArrayList<ScoreOfStudent> list = new ArrayList<ScoreOfStudent>();
			
			if(result==1){
				JSONArray studentsData = object.getJSONArray("studentsData");
				for(int i=0;i<studentsData.length();i++){
					
					
					JSONObject stu=studentsData.getJSONObject(i);
					int _id = stu.getInt("_id");
					String name = stu.getString("name");
					String sex = stu.getString("sex");
					int classes = stu.getInt("classes");
					JSONArray score = stu.getJSONArray("scores");
				
					ScoreOfStudent stus = new ScoreOfStudent(_id, name, sex, classes);
					for(int j=0;j < score.length();j++){
						JSONArray scoreofterm = score.getJSONArray(j);
						 for(int k=0;k<scoreofterm.length();k++){
							 stus.scores[j][k] = scoreofterm.getInt(k);
						 }
					}
					stus.setHowtoString(whichterms);
					list.add(stus);
					
					
				}
				
				
			}
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		
	}

	
	
}
