package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ScoreOfStudent;
import utils.DBhelper;

public class StuDBHelper {
	
	
	
	static public ScoreOfStudent findStudentByname(String name){
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from schoolreport where name =?;";
			conn = DBhelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rs=stmt.executeQuery();
			if(rs.next()) {
				ScoreOfStudent stu = new ScoreOfStudent();
				stu.setName(rs.getString("name"));
				
			return stu;
		}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	//Ö´ÐÐËÑË÷sqlÓï¾ä
	public static ArrayList<ScoreOfStudent> executeSQL(String sql){
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ScoreOfStudent> list=new ArrayList<ScoreOfStudent>();
		
		try {
			conn = DBhelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			while(rs.next()) {
				ScoreOfStudent stu = new ScoreOfStudent();
				stu.setName(rs.getString("name"));
				stu.set_id(rs.getInt("_id"));
				stu.setClasses(rs.getInt("class"));
				stu.setSex(rs.getString("sex"));
				int a = 5;
				for(int i = 0;i < 8;i ++){
					for(int j = 0;j < ScoreOfStudent.eachTermSubjectNum[i];j++){
						stu.scores[i][j] = rs.getInt(a);
						a++;
					}
					
					
				}
				
			list.add(stu);
			
		}
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	//Ö´ÐÐ¸üÐÂsqlÓï¾ä
	public static void updateSQL(String sql){
		Connection conn=null;
		PreparedStatement stmt = null;
		
	
	try {
		conn = DBhelper.getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	finally {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
			
		
			
		}
	
	
	public static void main(String[] args) {
		
//		ScoreOfStudent stu=StuDBHelper.findStudentByname("Îâ¾ü");
//		
//		System.out.println(stu.getName());
//		System.out.println(stu.get_id());
		
//		ArrayList<ScoreOfStudent> list = new ArrayList<ScoreOfStudent>();
//		list = StuDBHelper.executeSQL("select *from schoolreport;");
//		for(ScoreOfStudent stu:list){
//			System.out.println(stu.getName());
//		}
		StuDBHelper.updateSQL("update schoolreport set English1=69 where _id=9999998;");
		
	}
	
};
