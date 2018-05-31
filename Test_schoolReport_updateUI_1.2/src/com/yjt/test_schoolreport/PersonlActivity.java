package com.yjt.test_schoolreport;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bean.ScoreOfStudent;
import utils.Constant;
import utils.DBUpdateHttp;
import utils.Dbmanager;

public class PersonlActivity extends Activity {
	TextView tv_personalname ; 
	TextView tv_id;
 	TextView tv_sex;
	TextView tv_class;
	TextView tv_score1;
	TextView tv_sumandave1;
	TextView tv_score2;
	TextView tv_sumandave2;
	TextView tv_score3;
	TextView tv_sumandave3;
	Spinner spi_per_terms;
	Spinner spi_per_sub;
	Button btn_per_modify;
	EditText edt_per_newscore;
	Button dele_btn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		
		
		Bundle intentTogetinfo = this.getIntent().getExtras();
		final ScoreOfStudent stu = (ScoreOfStudent) this.getIntent().getSerializableExtra("scoreofstudeent");
		String name = intentTogetinfo.getString("name");
		
		
		//获取控件
		tv_personalname = (TextView)findViewById(R.id.tv_onename);
		tv_class = (TextView)findViewById(R.id.tv_personalclass);
		tv_id = (TextView)findViewById(R.id.tv_personalid);
		tv_sex = (TextView)findViewById(R.id.tv_onesex);
		tv_score1= (TextView)findViewById(R.id.tv_personsubject1);
		
		tv_sumandave1= (TextView)findViewById(R.id.tv_personsumandave1);
		spi_per_sub =(Spinner)findViewById(R.id.spi_per_sub);
		btn_per_modify = (Button)findViewById(R.id.btn_per_modify);
		edt_per_newscore = (EditText)findViewById(R.id.edt_per_score);
		
		
		//实现各个学期的选择
		spi_per_terms = (Spinner)findViewById(R.id.spi_per_terms);
		
		final List<String> list_terms = new ArrayList<String>();
		final ArrayList<Integer> condition = new ArrayList<Integer>();
		condition.add(0);
		condition.add(0);
		SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
		int item2 = sp.getInt("学期",0);
		for(int i =1;i <=item2+1;i++){
			String str_term = "第"+i+"学期";
			list_terms.add(str_term);
		}
		
			ArrayList<String> arrlist_sub = new ArrayList<String>();
			for(int i = 0;i < ScoreOfStudent.eachTermSubjectNum[0];i++){
				arrlist_sub.add(ScoreOfStudent.subject[0][i]);
			}
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arrlist_sub);
		spi_per_sub.setAdapter(adapter);
		final ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[1]);
		final ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[2]);
		final ArrayAdapter<String> adapter4=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[3]);
		final ArrayAdapter<String> adapter5=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[4]);
		final ArrayAdapter<String> adapter6=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[5]);
		final ArrayAdapter<String> adapter7=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[6]);
		final ArrayAdapter<String> adapter8=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[7]);
		spi_per_terms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list_terms));
		spi_per_terms.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				String str = "";
				for(int i = 0;i < ScoreOfStudent.eachTermSubjectNum[arg2];i++){
					str = str+ScoreOfStudent.subject[arg2][i]+":"+stu.scores[arg2][i];
					
				}
			 tv_score1.setText(str);
			 tv_sumandave1.setText("总分:"+stu.getsum(arg2)+"平均分:"+stu.getaverage(arg2));
			 switch(arg2){
				case 0:spi_per_sub.setAdapter(adapter);break;
				case 1:spi_per_sub.setAdapter(adapter2);break;
				case 2:spi_per_sub.setAdapter(adapter3);break;
				case 3:spi_per_sub.setAdapter(adapter4);break;
				case 4:spi_per_sub.setAdapter(adapter5);break;
				case 5:spi_per_sub.setAdapter(adapter6);break;
				case 6:spi_per_sub.setAdapter(adapter7);break;
				case 7:spi_per_sub.setAdapter(adapter8);break;
				default:spi_per_sub.setAdapter(adapter);break;
				
				}
			 condition.set(0, arg2);
//				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		spi_per_sub.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				condition.set(1, arg2);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//将数据加载到控件
		tv_personalname.setText(stu.getName().toString());
		tv_class.setText("班级:"+stu.getClasses()+"");
		tv_id.setText("学号:"+stu.get_id());
		tv_sex.setText(stu.getSex().toString());
		tv_score1.setText("英语1:"+stu.getEnglish1()+"数学1:"+stu.getMath1()+"体育1:"+stu.getSport1());
		tv_sumandave1.setText("总分:"+stu.getsum(0)+"平均分:"+stu.getaverage(0));
		
		btn_per_modify.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str = "update "+Constant.TABLE_NAME+" set "+Dbmanager.getSubject(condition.get(0), condition.get(1))+"="
				+edt_per_newscore.getText().toString()+" where "
						+ Constant._ID+"="+stu.get_id();
				str=str+";";
				
				new DBUpdateHttp(Constant.updateURL, str).start();
				Toast toast=Toast.makeText(PersonlActivity.this, "更新成功", Toast.LENGTH_LONG);
				toast.show();
//				SQLiteDatabase db;
//			    db = SQLiteDatabase.openOrCreateDatabase(Dbmanager.DB_PATH + "/" + Dbmanager.DB_NAME, null);
//			    db.execSQL(str);
//			    db.close();
				
			}
		});
		
	}
	

}
