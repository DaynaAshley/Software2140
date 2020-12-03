package KJapp;
import java.util.*;
import java.sql.*;

public class Report{
   private String company;
   private String destination;
   private int truckid;
   private double profit;
   private String date;

  public Report(String company,String dest,int truckid,String date,double profit){
    this.company=company;
    this.destination=dest;
    this.truckid=truckid;
    this.profit=profit;
    this.date=date;

  }

public String getCompany() {
   return this.company;
 }
 public String getDest() {
   return this.destination;
 }
 public int getTruckid() {
   return this.truckid;
 }
 public double getProfit() {
   return this.profit;
 }
 public String getDate() {
   return this.date;
 }

public static ArrayList<Report> ReportGenerated(){
   final String USER= "root";
   final String PASS="";

   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

   Connection conn = null;
   Statement stmt = null;
   ArrayList <Report> reports=new ArrayList<>();
   Report report;
   try{
       Class.forName(JDBC_DRIVER);
       conn = DriverManager.getConnection(DB_URL, USER, PASS);
       stmt = conn.createStatement();

       String sql= "SELECT * FROM CALROUTES";
       PreparedStatement ps= conn.prepareStatement(sql);

       ResultSet results=ps.executeQuery();

       while(results.next()){
           String company=results.getString(1);
           String destname= results.getString(2);
           int truck_id= results.getInt(3);
           String date=results.getString(4);
           double calculate_profit=results.getDouble(5);

           report=new Report(company,destname,truck_id,date,calculate_profit);
           reports.add(report);
       }
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
   }//end try
   return reports;
}
}

