package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBhelper {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url ="jdbc:mysql://localhost:3306/myfirstdb";
	private static final String username = "root";
	private static final String password = "admin";
	private static Connection cnn = null;
	
	//��̬�����������
	static {
		try {
			
			Class.forName(driver);
		}
			catch (Exception e) {
				
				// TODO: handle exception
				e.printStackTrace();
			}
		
		
	}
	//����ģʽ�������ݿ�����
	public static Connection getConnection() throws SQLException {
		if(cnn==null) {
			cnn = DriverManager.getConnection(url, username, password);
			return cnn;
		}
		else return cnn;
	}
//// �������ݿ�����
//	public static void main(String[] args) {
//		try {
//			Connection cnn = DBhelper.getConnection();
//			if(cnn!=null) System.out.println("��������");
//			else System.out.println("����ʧ��");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
	
	
	
};