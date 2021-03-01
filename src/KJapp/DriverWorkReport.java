package KJapp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
public class DriverWorkReport {

    public static void GenerateWorkReport(){
                final String USER= "root";
                final String PASS="";

                final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
                final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 
                Connection conn=null;
            try{
                
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Date dateobj = new Date();
                String date= df.format(dateobj);

                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t"+date);
                System.out.println("\n\t\t\t\t\tDrivers Work Log Report");
                ReportFormat.printTable(conn, "DRIVERWORK",50,100);

                ArrayList<String> freq=Driver.ReportFrequency();

                System.out.println("\n\n==-==-==-==-==-Summary-==-==-==-==-==");

                if ((Double.parseDouble(freq.get(0))==0.0)){
                    System.out.println("\nNo Deliveries added to the system!");
                }
                else{
                    if ((Integer.parseInt(freq.get(1))>5)){
                        
                        System.out.print("\nCompany with the most Deliveries: ");
                        System.out.print(freq.get(0));
                        
                        System.out.print("\nNumber of Deliveries: ");
                        System.out.print(freq.get(1));

                        System.out.print("\nLeast Productive Driver: ");
                        System.out.print(freq.get(2));

                        System.out.print("\nTotal Weight Delivered (kg): ");
                        System.out.print(freq.get(4));
                    }
                    System.out.print("\nTotal Weight Delivered (kg): ");
                    System.out.print(freq.get(4));
                }

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
            }//end try
    
}
}

