package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;
import bean.ScoreOfStudent;

public class Dbmanager {
	private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "mydb.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.yjt.test_schoolreport";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置(/data/data/com.yjt.test_schoolreport/cssystem.db)
    
    
    private SQLiteDatabase database;
    private Context context;

    public Dbmanager(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public void openDatabase() {
    	System.out.println(DB_PATH + "/" + DB_NAME);
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {

        try {
            if (!(new File(dbfile).exists())) {
            	//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = this.context.getAssets().open("mydb.db");  //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            return db;

        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }
    
    public void closeDatabase() {
        this.database.close();

    }
    
    //根据sql语句进行查找
    public static Cursor selectDataBySql(SQLiteDatabase db,String sql,String[] selectionArgs){
		Cursor cursor = null;
		if(db!=null){
			cursor = db.rawQuery(sql, selectionArgs);
			
		}
		
		
		return cursor;
		
		
	}
    
    public static List<ScoreOfStudent> cursorToList(Cursor cursor,int howtostring){
		List<ScoreOfStudent> list = new ArrayList();
		while(cursor.moveToNext()){
			int columnIndex = cursor.getColumnIndex(Constant._ID);
			int _id = cursor.getInt(columnIndex);
			String name = cursor.getString(1);
			String sex = cursor.getString(2);
			int  classes = cursor.getInt(cursor.getColumnIndex(Constant.CLASSES));
//			int english1 = cursor.getInt(cursor.getColumnIndex(Constant.ENGLISH1));
//			int math1 = cursor.getInt(cursor.getColumnIndex(Constant.MATH1));
//			int sport1 = cursor.getInt(cursor.getColumnIndex(Constant.SPORT1));
//			int english2 = cursor.getInt(cursor.getColumnIndex(Constant.ENGLISH2));
//			int math2 = cursor.getInt(cursor.getColumnIndex(Constant.MATH2));
//			int sport2 = cursor.getInt(cursor.getColumnIndex(Constant.SPORT2));
//			int english3 = cursor.getInt(cursor.getColumnIndex(Constant.ENGLISH3));
//			int math3 = cursor.getInt(cursor.getColumnIndex(Constant.MATH3));
//			int sport3 = cursor.getInt(cursor.getColumnIndex(Constant.SPORT3));
			
			
			
			ScoreOfStudent sos = new ScoreOfStudent(_id, name,  sex,  classes); 
			int a = 4;
			for(int i = 0;i < 8;i ++){
				for(int j = 0;j < sos.eachTermSubjectNum[i];j++){
					sos.scores[i][j] = cursor.getInt(a);
					a++;
				}
				
				
			}
			sos.setHowtoString(howtostring);
			
			list.add(sos);
			}
		
		return list;
		
	}
    
    
    //根据学期,编号获取科目
    public static String getSubject(int a,int b ){
    	//写一个存储所有学期的科目的数组
    	
    	String[][] arr_subject = new String[8][8];
    			arr_subject[0][0] = Constant.ENGLISH1;
    		    arr_subject[0][1] = Constant.MATH1;
    		    arr_subject[0][2] = Constant.SPORT1;
    		    arr_subject[1][0] = Constant.ENGLISH2;
    		    arr_subject[1][1] = Constant.MATH2;
    		    arr_subject[1][2] = Constant.SPORT2;
    		    arr_subject[2][0] = Constant.ENGLISH3;
    		    arr_subject[2][1] = Constant.MATH3;
    		    arr_subject[2][2] = Constant.SPORT3;
    	
    	int item1 = 0; //我的dbtable我的字段直接data0到data14
    	for(int i = 3;i <8;i++){
    		for(int j = 0;j < ScoreOfStudent.eachTermSubjectNum[i];j++){
    			arr_subject[i][j] = "data"+item1;
    			item1++;
    		}
    		
    	}
    	
    	return arr_subject[a][b];
    	
    	
    	
    }
    
    
    

}
