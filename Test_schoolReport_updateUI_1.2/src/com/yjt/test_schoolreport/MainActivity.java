package com.yjt.test_schoolreport;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import utils.Dbmanager;


/*
 * author:yjt
 * 上一版本给部分访问的结果添加点击事件
 * 修改了数据库查询语句默认math1>0,为math1>=0
 * 修改了只能Parthttpselect里成绩赋值的bug,(for循环错误)
 * 
 * 这一版本主要美化UI
 * */
public class MainActivity extends Activity {
	public Dbmanager dbHelper;
	Button btnPartSelect;
	Button btnoneSelect;
	Button btnChange;
	Button btnToupdate;
	private SharedPreferences sp ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//导入外部数据库
		dbHelper = new Dbmanager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
        
        
        SQLiteDatabase database;
        database = SQLiteDatabase.openOrCreateDatabase(Dbmanager.DB_PATH + "/" + Dbmanager.DB_NAME, null);
//        database.execSQL("insert into schoolreport(name,sex) values ('王','男')");
//        Cursor c = database.rawQuery("select * from "+Constant.TABLE_NAME, null);
//按性别查询        
//        Cursor c = Dbmanager.selectDataBySql(database, "select * from "+Constant.TABLE_NAME +" where "+Constant.SEX+"='男'", null);
//按分数段查询  
//        String sql = "select * from "+Constant.TABLE_NAME +" where 1math > ?";
//        Cursor c = Dbmanager.selectDataBySql(database, "select * from "+Constant.TABLE_NAME +" where "+Constant.MATH1+" >90", null);
//按班级查询
//        Cursor c = Dbmanager.selectDataBySql(database, "select * from "+Constant.TABLE_NAME +" where "+Constant.CLASSES+"=161", null);
//        Cursor c = Dbmanager.selectDataBySql(database, "select * from "+Constant.TABLE_NAME +" where "+Constant.MATH1+"< 90", null);
        
        
//        List<ScoreOfStudent> list = new ArrayList<ScoreOfStudent>();
//        list = Dbmanager.cursorToList(c);
//        while(c.moveToNext()){  
//            String classes=c.getString(c.getColumnIndex( "name"));  
//               list.add(classes) ;
//         }  
//        lv.setAdapter(new ArrayAdapter<ScoreOfStudent>(this, android.R.layout.simple_expandable_list_item_1,list));
        
        
        
        
        database.close();
//        c.close();
       
        //通过shareference设置常用参数
        sp = getSharedPreferences("sp_demo", Context.MODE_PRIVATE);
        //判断程序第一次运行
        boolean isFirstRun = sp.getBoolean("isFirstRun", true);
        if(isFirstRun){
        	sp.edit().putBoolean("isFirstRun", false).commit();
        	
        	sp.edit().putInt("学期", 2).commit();
        	
        }
        
        
        
        
        
        //获取控件
        btnToupdate = (Button)findViewById(R.id.btn_ToUpdate);
        
        
     // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 获取布局
        View view2 = View.inflate(MainActivity.this, R.layout.login, null);
        // 获取布局中的控件
        final EditText username = (EditText) view2.findViewById(R.id.edt_username);
        final EditText password = (EditText) view2.findViewById(R.id.edt_password);
        final Button btnlogin = (Button) view2.findViewById(R.id.btn_login);
        
        
        // 设置参数
        builder.setTitle("Login").setIcon(R.drawable.ic_launcher)
                .setView(view2);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();
        
        btnToupdate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	
            	alertDialog.show();
            	btnlogin.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String uname = username.getText().toString().trim();
		                String psd = password.getText().toString().trim();
		                if (uname.equals("abc") && psd.equals("123456")) {
		                    Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
		                    startActivityForResult(intent, 1);
		                    
		                  
		                 }
		                else{
		                	Toast.makeText(MainActivity.this, "登录失败", 0).show();
			                
		                	
		                }
		                
		                alertDialog.dismiss();// 对话框消失
		                
					}
				});
                
                
                
                
            }
           
            
            
        });
        
       
       
        
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void click(View view){
		switch(view.getId()){
		case R.id.btn_partselect:
			Intent intent = new Intent(MainActivity.this,SelectPart.class);
			intent.putExtra("howtoselect", 0);
			startActivity(intent);
			break;
		case R.id.btn_oneselect:
			Intent intent2 = new Intent(MainActivity.this,SelectPart.class);
			intent2.putExtra("howtoselect", 3);
			startActivity(intent2);
			break;
		
		
		}
		
			
		
		
		
	}
	
	
}
