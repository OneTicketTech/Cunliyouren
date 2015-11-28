package com.clyr.utils;  
  
import java.io.*;     
import java.sql.*;     
import java.util.Properties;
  
public class DBConn {     
	public static String driver;//��������     
    public static String url;//����URL     
    public static String user;//�����û���     
	public static String password;//��������     
 	public static Connection conn;//��������     
  	public static Statement stmt;//����STMT     
  	public ResultSet rs;//��������     
  	//����CONN     
   	static{     
   		Properties pro = new Properties();
   		try {   
   			driver="com.mysql.jdbc.Driver";  
   			try {
				pro.load(DBConn.class.getResourceAsStream("jdbc.properties"));
			} catch (IOException e) {
				System.out.println("δ�ҵ������ļ�");
			} 
   			url=pro.getProperty("url");
   			user = pro.getProperty("user");
			password = pro.getProperty("password");
   			Class.forName(driver);     
   			conn = DriverManager.getConnection(url,user,password);  
   			System.out.println("-------���ӳɹ�------");  
   		} catch(ClassNotFoundException classnotfoundexception) {     
   			classnotfoundexception.printStackTrace();     
   			System.err.println("db: " + classnotfoundexception.getMessage());     
   		} catch(SQLException sqlexception) {     
   			System.err.println("db.getconn(): " + sqlexception.getMessage());     
   		}     
	}     
	//���캯����Ĭ�ϼӲ������ļ�Ϊjdbc.driver     
	public DBConn(){     
		this.conn=this.getConn();  
	}     
	//����Conn     
	public Connection getConn(){     
 		return this.conn;     
	}     
	//ִ�в���     
	public void doInsert(String sql) {     
		try {     
			stmt = conn.createStatement();     
			int i = stmt.executeUpdate(sql);     
		} catch(SQLException sqlexception) {     
			System.err.println("db.executeInset:" + sqlexception.getMessage());     
		}finally{                     
		}     
	}     
	//ִ��ɾ��     
	public void doDelete(String sql) {     
  	    try {     
  		    stmt = conn.createStatement();     
  		    int i = stmt.executeUpdate(sql);     
  	    } catch(SQLException sqlexception) {     
  		    System.err.println("db.executeDelete:" + sqlexception.getMessage());     
  		}     
    }     
	//ִ�и���     
	public void doUpdate(String sql) {     
		try {     
			stmt = conn.createStatement();     
			int i = stmt.executeUpdate(sql);     
		} catch(SQLException sqlexception) {     
			System.err.println("db.executeUpdate:" + sqlexception.getMessage());     
		}     
	}     
	//��ѯ�����     
 	public ResultSet doSelect(String sql) {     
 		try {  
 			conn=DriverManager.getConnection(url,user,password);  
 			stmt = conn.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);       
 			rs = stmt.executeQuery(sql);   
 			System.out.println("ȡ�ý����");  
 		} catch(SQLException sqlexception) {     
 			System.err.println("db.executeQuery: " + sqlexception.getMessage());     
 		}     
 		return rs;     
	}     
	/**   
	*�ر����ݿ����������ݿ�����������ݿ�����   
  	@Function: Close all the statement and conn int this instance and close the parameter ResultSet   
 	@Param: ResultSet   
 	@Exception: SQLException,Exception   
	**/    
	public void close(ResultSet rs) throws SQLException, Exception {     
		if (rs != null) {     
			rs.close();     
			rs = null;     
		}     
        
		if (stmt != null) {     
			stmt.close();     
			stmt = null;     
		}     
        
		if (conn != null) {     
			conn.close();     
			conn = null;     
		}     
 	}     
        
	/**   
 	*�ر����ݿ�����������ݿ����Ӷ���   
	* Close all the statement and conn int this instance   
	* @throws SQLException   
	* @throws Exception   
	*/    
  	public void close() throws SQLException, Exception {     
  		if (stmt != null) {     
  			stmt.close();     
  			stmt = null;     
  		}     
        
  		if (conn != null) {     
  			conn.close();     
  			conn = null;     
  		}     
	}   
    //���Է���
//  public static void main(String []args){  
//      DBConn db=new DBConn();  
//      db.getConn();  
//     ResultSet rs=db.doSelect("select * from admin where adminName='admin'");  
//     try {  
//         while(rs.next()){  
//             System.out.println(rs.getString("adminName"));  
//             System.out.println(rs.getString("password"));
//               
//         }  
//     } catch (SQLException e) {  
//         // TODO Auto-generated catch block  
//         e.printStackTrace();  
//     }  
//  } 
         
}