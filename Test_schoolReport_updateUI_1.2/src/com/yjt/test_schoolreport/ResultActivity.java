package com.yjt.test_schoolreport;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import bean.ScoreOfStudent;
import utils.Constant;
import utils.Dbmanager;
import utils.PartSelectHttp;

public class ResultActivity extends Activity implements OnItemClickListener {
	
	ListView lv;
	List<ScoreOfStudent> list = new ArrayList<ScoreOfStudent>();
	Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_resultofselect);
	
	//获取listview展示
	lv = (ListView)findViewById(R.id.lv_result);
	
	//获取各个条件
	//获取intent中的bundle对象
	Bundle bundle = this.getIntent().getExtras();
	String minscore = bundle.getString("minscore");
	String maxscore = bundle.getString("maxscore");
	String name = bundle.getString("name");
	String findscore = bundle.getString("findscore");
	int which_terms = bundle.getInt("which_terms");
	
	List<Integer> list_condition = bundle.getIntegerArrayList("condition");
	//是否为单人查询
	int forone = list_condition.get(5);
	
	
	//SQLiteDatabase db;
    //db = SQLiteDatabase.openOrCreateDatabase(Dbmanager.DB_PATH + "/" + Dbmanager.DB_NAME, null);
    
   String sql = "select * from "+Constant.TABLE_NAME+" where "+Constant.MATH1+">=0 ";
   if(list_condition.get(3)<3){
	   sql = sql+" and "+Dbmanager.getSubject(list_condition.get(0),list_condition.get(3))+" between "+minscore+" and "+maxscore;
   }
   
   if(!findscore.equals("-1")){
	   sql = sql +" and "+Dbmanager.getSubject(list_condition.get(0),list_condition.get(3))+"="+findscore;
	   
   }
   		//添加性别条件限制
	   if(list_condition.get(1) == 1)
	   sql = sql+" and "+Constant.SEX+"='男' ";
	   else if(list_condition.get(1) == 2)
		   sql = sql+" and "+Constant.SEX+"='女' ";
   
	   //添加班级条件限制
	   if(list_condition.get(2) == 1)
		   sql = sql + " and "+Constant.CLASSES+"= 161";
		   else if(list_condition.get(2) == 2)
			   sql = sql + " and "+Constant.CLASSES+"= 162";
		   else if(list_condition.get(2) == 3)
			   sql = sql + " and "+Constant.CLASSES+"= 163";
	   if(forone == 3){
		   sql = sql+" and "+Constant.NAME+" like '%"+name+"%'";
	   }
	   
//	   //添加排序方法
//	   
	   if(list_condition.get(3)<3){
		   sql = sql+" order by "+Dbmanager.getSubject(list_condition.get(0),list_condition.get(3));
	   }
	   
	   if(list_condition.get(4) == 1)sql = sql + " DESC";
	   
    //Cursor c = Dbmanager.selectDataBySql(db, sql, null);

    String sql2 = sql+";";
    
    
    new PartSelectHttp(Constant.selectURL, handler, lv,ResultActivity.this,sql2,which_terms,PersonlActivity.class).start();
   
    //list = Dbmanager.cursorToList(c,which_terms);
   
    //lv.setAdapter(new ArrayAdapter<ScoreOfStudent>(this, android.R.layout.simple_expandable_list_item_1,list));
    
    //c.close();
    //db.close();
    
    //lv.setOnItemClickListener(this);
    
    
	
	}

	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intentToInfo = new Intent(this,PersonlActivity.class);
		Bundle bun1 = new Bundle();
		bun1.putString("name",list.get(arg2).getName() );
		bun1.putSerializable("scoreofstudeent", list.get(arg2));
		intentToInfo.putExtras(bun1);
		startActivity(intentToInfo);
		
	}
	

}
