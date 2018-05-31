package com.yjt.test_schoolreport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import bean.ScoreOfStudent;

public class SelectPart extends Activity {
	Button btn_dispalyresult ;
	EditText edt_minscore;
	EditText edt_maxscore;
	EditText edt_name;
	EditText edt_findbyscore;
	TextView tv_terms,tv_periodscore,tv_name,tv_howtorank;
	
	Spinner spi_terms,spi_sex,spi_class,spi_subject,spi_howtorank;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partselect);
		
		
		
	//选择展示方式
		Bundle bundle = this.getIntent().getExtras();	
		int howToSelect = bundle.getInt("howtoselect");
		
		
	//控件获取
	//tv_text = (TextView)findViewById(R.id.tv_test);
	tv_periodscore = (TextView)findViewById(R.id.tv_preiodscore);
	tv_name = (TextView)findViewById(R.id.tv_name);
	tv_howtorank = (TextView)findViewById(R.id.tv_howtorank);
	edt_name = (EditText)findViewById(R.id.edt_name);
	tv_terms = (TextView)findViewById(R.id.tv_terms);
	edt_maxscore = (EditText)findViewById(R.id.edt_maxscore);
	edt_minscore = (EditText)findViewById(R.id.edt_minscore);
	spi_sex = (Spinner)findViewById(R.id.spi_sex);
	spi_class = (Spinner)findViewById(R.id.spi_class);
	spi_subject = (Spinner)findViewById(R.id.spi_subject);
	spi_howtorank = (Spinner)findViewById(R.id.spi_howtorank);
	edt_findbyscore = (EditText)findViewById(R.id.edt_findbysocre);
	
	//部分查找和单独查找的控件显示
	//tv_text.setVisibility(View.GONE);
	if(howToSelect==0){
		edt_name.setVisibility(View.GONE);
		tv_name.setVisibility(View.GONE);
		edt_findbyscore.setVisibility(View.GONE);
	}
	if(howToSelect == 3){
		tv_periodscore.setVisibility(View.GONE);
		tv_howtorank.setVisibility(View.GONE);
		edt_maxscore.setVisibility(View.GONE);
		edt_minscore.setVisibility(View.GONE);
		spi_howtorank.setVisibility(View.GONE);
		
	}
	
	//实现各个学期的选择
	spi_terms = (Spinner)findViewById(R.id.spi_term);
	
	final List<String> list_terms = new ArrayList<String>();
	
	SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
	int item2 = sp.getInt("学期",0);
	for(int i =1;i <=item2+1;i++){
		String str_term = "第"+i+"学期";
		list_terms.add(str_term);
	}
	//性别选择
	final List<String> list_sex = new ArrayList<String>();
	list_sex.add("任意");
	list_sex.add("男");
	list_sex.add("女");
	//班级选择
	final List<String> list_class = new ArrayList<String>();
	list_class.add("全部");
	list_class.add("161");
	list_class.add("162");
	list_class.add("163");
	//科目选择
	final List<String> list_subject = new ArrayList<String>();
		list_subject.add("英语1");
		list_subject.add("数学1");
		list_subject.add("体育1");
		if(howToSelect == 3)list_subject.add("全部");
	//排序算法
		final List<String> list_howtorank = new ArrayList<String>();
		list_howtorank.add("升序");
		list_howtorank.add("降序");
	
	
	//所有spinner条件的汇总
	final ArrayList<Integer> list_condition = new ArrayList<Integer>();
	for(int i = 0;i < 7;i++)
		list_condition.add(0);
	list_condition.set(5,howToSelect );
	
	//为了关联学期和科目重新设置几个适配器
	final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[0]);
	final ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[1]);
	final ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[2]);
	final ArrayAdapter<String> adapter4=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[3]);
	final ArrayAdapter<String> adapter5=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[4]);
	final ArrayAdapter<String> adapter6=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[5]);
	final ArrayAdapter<String> adapter7=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[6]);
	final ArrayAdapter<String> adapter8=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,ScoreOfStudent.subject[7]);
	
	//学期选择事件的监听
	spi_terms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list_terms));
	spi_terms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			list_condition.set(0, arg2);
//			if(arg2==0)spi_subject.setAdapter(adapter);
//			if(arg2 == 1) spi_subject.setAdapter(adapter2);
//			if(arg2==2)spi_subject.setAdapter(adapter3);
			switch(arg2){
			case 0:spi_subject.setAdapter(adapter);break;
			case 1:spi_subject.setAdapter(adapter2);break;
			case 2:spi_subject.setAdapter(adapter3);break;
			case 3:spi_subject.setAdapter(adapter4);break;
			case 4:spi_subject.setAdapter(adapter5);break;
			case 5:spi_subject.setAdapter(adapter6);break;
			case 6:spi_subject.setAdapter(adapter7);break;
			case 7:spi_subject.setAdapter(adapter8);break;
			default:spi_subject.setAdapter(adapter);break;
			
			}
			
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
	//性别选择事件的监听
	spi_sex.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list_sex));
	spi_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			list_condition.set(1, arg2);
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	//班级选择事件的监听
	spi_class.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list_class));
	spi_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			list_condition.set(2, arg2);
//			tv_text.setText(list_condition.get(2)+"");
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
	//科目选择事件的监听
	spi_subject.setAdapter(adapter);
	spi_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			list_condition.set(3, arg2);
			
		}
		
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
	
	//排序方式选择事件监听
	//性别选择事件的监听
		spi_howtorank.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list_howtorank));
		spi_howtorank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				list_condition.set(4, arg2);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
	
	
	
	
	
	
	

	btn_dispalyresult = (Button)findViewById(R.id.btn_displayresult);
	btn_dispalyresult.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			
			
			
			
			
			
			
			
			//通过bundle类进行多个数据传输
			Intent intent = new Intent(SelectPart.this,ResultActivity.class);
			Bundle bundle1  = new Bundle();
			String maxscore,minscore;
			if(edt_maxscore.getText().toString().length()==0)
				maxscore = "100";
			else maxscore = edt_maxscore.getText().toString();
			
			if(edt_minscore.getText().toString().length()==0)
				minscore = "0";
			else minscore = edt_minscore.getText().toString();
			
		
			bundle1.putString("minscore",minscore);
			bundle1.putString("maxscore", maxscore);
			bundle1.putString("name", edt_name.getText().toString());
			bundle1.putInt("which_terms", list_condition.get(0));
			bundle1.putInt("whichclass", list_condition.get(2));
			String findscore;
			if(edt_findbyscore.getText().toString().length()==0)
				findscore = "-1";
			else findscore = edt_findbyscore.getText().toString();
			bundle1.putString("findscore", findscore);
			
			bundle1.putIntegerArrayList("condition", list_condition);
			//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtras(bundle1);
			startActivity(intent);
			
		}
	});
	}

}
