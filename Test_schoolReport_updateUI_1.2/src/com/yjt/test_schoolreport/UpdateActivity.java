package com.yjt.test_schoolreport;

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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bean.ScoreOfStudent;
import utils.Constant;
import utils.DBUpdateHttp;

public class UpdateActivity extends Activity implements OnClickListener {
	Spinner spi_man_terms ,spi_man_term2,spi_man_subject;
	Button btn_update;
	Button btn_change;
	EditText edt_man_id ;
	EditText edt_man_score;
	private TextView test;
	private RadioButton mMaleRb;  
    private RadioButton mFamaleRb;
    private RadioButton class_161_rb;  
    private RadioButton class_162_rb;
    private RadioButton class_163_rb;  
    private EditText dele_edt_id;
    private Button dele_btn;
    
    
    private EditText insert_edt_name,insert_edt_id;
	
	
	ArrayList<Integer> condition =new  ArrayList<Integer>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_modify);
		initView();
		
		//现在学期
		condition.add(ScoreOfStudent.nowTerms);
		//可拓展学期
		final List<String> list_terms = new ArrayList<String>();
		
		for(int i =1;i <=8;i++){
			String str_term = "第"+i+"学期";
			list_terms.add(str_term);
		}
		
		
		
		//获取控件
		spi_man_terms = (Spinner)findViewById(R.id.spi_man_term);
		btn_update = (Button)findViewById(R.id.btn_update);
		
		
		
		//学期拓展
		spi_man_terms.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list_terms));
		spi_man_terms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				condition.set(0, arg2);
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
		
		btn_update.setOnClickListener(this);
	}

		
		private void initView(){
//	        初始化对象  
	        mMaleRb = (RadioButton)findViewById(R.id.male_rb);  
	        mFamaleRb = (RadioButton)findViewById(R.id.famale_rb);
	        class_161_rb = (RadioButton)findViewById(R.id.class_161_rb);
	        class_162_rb = (RadioButton)findViewById(R.id.class_162_rb);
	        class_163_rb = (RadioButton)findViewById(R.id.class_163_rb);
	        insert_edt_name = (EditText)findViewById(R.id.insert_edt_name);
	        insert_edt_id = (EditText)findViewById(R.id.insert_edt_id);
	        btn_change = (Button)findViewById(R.id.insert_btn);
	        dele_btn = (Button)findViewById(R.id.dele_btn);
	        dele_edt_id=(EditText)findViewById(R.id.dele_edt_id);
	        //test = (TextView)findViewById(R.id.insert_test);
	        
	        //添加修改的点击事件
	        btn_change.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String sql = "insert into schoolreport(_id,name,sex,class) values(";
			        String id,sex,classes,name;
			        id = ""+insert_edt_id.getText().toString();
			        name=",'" +insert_edt_name.getText().toString()+"'";
			        if(mMaleRb.isChecked())sex=",'男'";
			        else sex=",'女'";
			        if(class_161_rb.isChecked())classes=",161";
			        else if(class_162_rb.isChecked())classes=",162";
			        else classes=",163";
			        sql=sql+id+name+sex+classes;
			        
			        sql = sql+");";
			        //测试生成的sql语句是否正确
			        //test.setText(sql);
			        if(!id.equals("")){
			        	new DBUpdateHttp(Constant.updateURL, sql).start();
				        Toast toast=Toast.makeText(UpdateActivity.this, "插入成功", Toast.LENGTH_LONG);
						toast.show();
			        }
			        
				}
			});
	        dele_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					String sql="delete from schoolreport where _id=";
					String id = dele_edt_id.getText().toString()+"";
					sql=sql+id+";";
					new DBUpdateHttp(Constant.updateURL, sql).start();
					//test.setText(sql);
					
					if(!id.equals("")){
			        	new DBUpdateHttp(Constant.updateURL, sql).start();
				        Toast toast=Toast.makeText(UpdateActivity.this, "删除成功", Toast.LENGTH_LONG);
						toast.show();
			        }
				}
			});
	        
			
			
		}
		
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		ScoreOfStudent.nowTerms =condition.get(0);
		SharedPreferences sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
		sp.edit().putInt("学期", condition.get(0)).commit();
		
			Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
			
				
				startActivity(intent);

	}
	
	
	
}
