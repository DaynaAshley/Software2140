package KJapp;
import java.sql.*;

public class UpdateTruck {

    public static void UpdateTruckInfo(final String newTruckID , final String newTruckMake, final String newTruckModel, final String newTruckLiscence, final String newTruckFitness, final String newTruckReg, final String newTruckIns){
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
         
            
            final String sql2 = "Update TRUCK Set make=?, model=?, license_num=?, fitness_expdate=?, registration_expdate=?, insurance_expdate=?  Where truck_id=?";
            final PreparedStatement prep= conn.prepareStatement(sql2);
            prep.setString(1, newTruckMake);
            prep.setString(2, newTruckModel);
            prep.setString(3, newTruckLiscence);
            prep.setString(4, newTruckFitness);
            prep.setString(5, newTruckReg);
            prep.setString(6, newTruckIns);
            prep.setString(7, newTruckID);
            prep.executeUpdate();

        }catch(final SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(final Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(final SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(final SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
