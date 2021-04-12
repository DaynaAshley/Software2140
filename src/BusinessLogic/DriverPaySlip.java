package BusinessLogic;

import java.util.ArrayList;

import java.util.Date;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DriverPaySlip extends PaySlip {
    Driver driver;

    public DriverPaySlip(Driver driver,double nis,double income,double edu,double nht,double pay,double totaltax,double netpay,double nht_t,double nis_t,double edu_t,double income_t){
        super(nis,income,nht,edu,pay,totaltax,netpay,nht_t,edu_t,income_t,nis_t);
        this.driver=driver;
    }

    public Driver getDriver(){
        return this.driver;
    }
    

    public static ArrayList<DriverPaySlip> GenerateDriverPaySlips(int id){
        Driver[] driverarray=Driver.FindDriver(id);
        ArrayList<DriverPaySlip> slips=new ArrayList<>();
        double wage=0.0;
        
        if (driverarray[0]==null){
            return slips;
        }
        else{
            final String USER= "root";
            final String PASS="";

            final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
            final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

            Connection conn = null;
            Statement stmt = null;
            try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();

                String sql= "SELECT * FROM WORK WHERE id = ?";
                PreparedStatement preps= conn.prepareStatement(sql);
                preps.setInt(1, id);
                ResultSet rs= preps.executeQuery();
            
                while(rs.next()){
                    wage+=rs.getDouble(5); 
                }

                Driver driver=driverarray[0];
                DriverPaySlip slip=new DriverPaySlip(driver, 0.0, 0.0, 0.0, 0.0, wage, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                slips.add(slip);
    
               /* String sql1= "INSERT INTO WORK VALUES (?,?,?,?,?)";
                PreparedStatement ps1= conn.prepareStatement(sql1);
                ps1.setString(1, driver.getName());
                ps1.setInt(2, driver.getID());
                ps1.setString(3, "");
                ps1.setString(4, "");
                ps1.setDouble(5,-(wage));          
                ps1.executeUpdate();*/
    
                

                
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

            return slips;
        }
    }

    public static void SendSlip(DriverPaySlip slip,String user){
        String pdf=ExportPdf.ExportDriverPaySlip(slip);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        String date= df.format(dateobj);
        EmailNotification.SendAttachment(user,"Pay Slip", "Please See Attachment", pdf, date);

    }

    
}

        

        
    

