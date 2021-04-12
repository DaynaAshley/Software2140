package BusinessLogic;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.JOptionPane;
public class DriverWorkReport {

    public static void GenerateWorkReportTU(){
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

    public static ArrayList<DriverWork>GenerateReportGUI(){
        ArrayList<DriverWork> entries=new ArrayList<>();
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 
        Connection conn=null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
        
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        
        
        
        String sqlSelectAll = "SELECT * FROM DRIVERWORK";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sqlSelectAll);
            
        while(rs.next())
            {
               String driver= rs.getString("driver_name");
               String company= rs.getString("company");
               String dest=rs.getString("destination_name");
               String load=rs.getString("load_name");
               double weight= rs.getDouble("weight");
               String s1= rs.getString("side_man1");
               String s2= rs.getString("side_man2");
               int truckid= rs.getInt("truck_id");
               int id= rs.getInt("id");
               String date= rs.getString("date");

               DriverWork row=new DriverWork(driver,id,company,dest,load,weight,s1,s2,truckid,date);
               
               entries.add(row);
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

