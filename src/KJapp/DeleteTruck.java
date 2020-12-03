package KJapp;

import java.sql.*;
public class DeleteTruck {

public static int DelTruck(int truckID){

  final String USER= "root";
  final String PASS="";

  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

  Connection conn = null;
  Statement stmt = null;
  int status=0;
  try{
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
            // create the mysql delete statement.

      String query = "Delete FROM TRUCK where truck_id = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(1, truckID);

      // execute the preparedstatement
      status= preparedStmt.executeUpdate();
      
    
    }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
  }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
  }finally{
      //finally block used to close resources
      try{
          if(stmt!=null)
              stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
          if(conn!=null)
              conn.close();
      }catch(SQLException se){
          se.printStackTrace();
      }//end finally try
  }
  return status;
}
 

}
