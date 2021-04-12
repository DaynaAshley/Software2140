package BusinessLogic;

import javax.swing.JOptionPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProfitReport {

    public static void GenerateReportTU(){
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dateobj = new Date();
            String date= df.format(dateobj);

            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t"+date);
            System.out.println("\n\t\t\t\t\tProfit/Loss Report");

                final String USER= "root";
                final String PASS="";

                final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
                final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 
                Connection conn=null;
            try{
                
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t"+date);
                System.out.println("\n\t\t\t\t\tProft/Loss Report");
                ReportFormat.printTable(conn, "CALROUTES",50,100);


            }catch(SQLException se){
                //Handle errors for JDBC
                se.printStackTrace();
            }catch(Exception e){
                //Handle errors for Class.forName
                e.printStackTrace();
            }finally{
                //finally block used to close resources
                // nothing we can do5
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }//end finally try
            }
    }
    

    public static ArrayList <String[]> GenerateReportGUI(){
            final String USER= "root";
            final String PASS="";
           
            ArrayList <String[]> entries=new ArrayList<>();
            
            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
            final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 
            Connection conn=null;
            Statement stmt = null;
            ResultSet rs = null;
            try{
                    Class.forName(JDBC_DRIVER);
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);

                    String sqlSelectAll = "SELECT * FROM CALROUTES";
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sqlSelectAll);
                    
                 
                    while(rs.next())
                        {
                           String company= rs.getString("company_name");
                           String dest= rs.getString("destination_name");
                           String truckid= String.valueOf(rs.getInt("truck_id"));
                           String date= rs.getString("date");
                           String price= String.valueOf(rs.getDouble("calculate_profit"));

                           String [] tbdata={company,dest,truckid,date,price};
                        
                           entries.add(tbdata);

                        }
                        
                   
                }
                catch(SQLException se){
                //Handle errors for JDBC
                    JOptionPane.showMessageDialog(null, se);
                }catch(Exception e){
                //Handle errors for Class.forName
                    JOptionPane.showMessageDialog(null, e);
                }finally{
                //finally block used to close resources
                // nothing we can do5
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    JOptionPane.showMessageDialog(null, se);
                }//end finally try
                }
        
        return entries;        
    }   
                               
    }
    

