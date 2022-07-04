package bankpackage;

import java.sql.*;



public class JDBC {
      
	  static Connection con = null;
	  static Statement stmt = null;
	  
	  JDBC(){
		  try {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
			  String url = "jdbc:oracle:thin:@localhost:1521/xe";
			  String username = "sys as sysdba";
			  String password = "Chit@2022";
			   con = DriverManager.getConnection(url , username , password);
			   stmt = con.createStatement();
		  }
		   catch(Exception e) {
			   System.out.println(e);
			   e.printStackTrace();
		   }

	  }
	  

//    static void con_class() {
//	   try {
//		  Class.forName("oracle.jdbc.driver.OracleDriver");
//		  String url = "jdbc:oracle:thin:@localhost:1521/xe";
//		  String username = "sys as sysdba";
//		  String password = "Chit@2022";
//		   con = DriverManager.getConnection(url , username , password);
//		   stmt = con.createStatement();
//
//		 
//		  
////		  stmt.executeUpdate("insert into product12 values ('EL107' , 'LAPTOP' , 'HAWLETT' , 700)");
////		  
////		  ResultSet rs =  stmt.executeQuery("select * from product12");
////		 
////		  while(rs.next()){
////		  ///System.out.println(rs.getString(1));
////			  System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4)+" jhnjfynmbkuhhjmjk");
////		      //System.out.println("lk,nlkmdcx");
////		  }
////		  
//	//	  con.close();
//		  
//	   }
//	   catch(Exception e) {
//		   System.out.println(e);
//		   e.printStackTrace();
//	   }
////	   catch(SQLException e) {
////		   e.printStackTrace();
////	   }
//   }
     
    void insert(String s) throws SQLException {
    	//con_class();
    	stmt.executeUpdate(s);
    }
    
    ResultSet exct(String s) throws SQLException {
    	//con_class();
    	return stmt.executeQuery(s);
    }
    
    void closeconection() throws SQLException {
    	
    	con.close();
    	stmt.close();
    	
    }
    
}
