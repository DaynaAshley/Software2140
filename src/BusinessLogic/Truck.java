package BusinessLogic;

import java.sql.*;

public class Truck {
    private int truck_id;
    private String make;
    private String model;
    private String license_num;
    private String fitness_expdate;
    private String registration_expdate;
    private String insurance_expdate;

    public Truck(int truckid, String make, String model, String license, String fitnessdate, String regdate, String insurdate){
        this.truck_id=truckid;
        this.make=make;
        this.model=model;
        this.license_num=license;
        this.fitness_expdate=fitnessdate;
        this.registration_expdate=regdate;
        this.insurance_expdate=insurdate;
    }

    public int getTruckID()
    {
        return this.truck_id;
    }

    public String getMake(){
        return this.make;
    }

    public String getModel(){
        return this.model;
    }

    public String getLicense(){
        return this.license_num;
    }

    public String getFitnessExpDate(){
        return this.fitness_expdate;
    }

    public String getRegistrationExpDate(){
        return this.registration_expdate;
    }

    public String getInsuranceExpDate(){
        return this.insurance_expdate;
    }

    public void setFitnessDate(String newfitnessdate){
        this.fitness_expdate=newfitnessdate;
    }

    public void setRegistrationDate(String newregdate){
        this.registration_expdate=newregdate;
    }

    public void setInsuranceDate(String newinsdate){
        this.insurance_expdate=newinsdate;
    }

    public String toString(){
        return "Truck ID:"+ getTruckID() +"\n"+ "Make:" +getMake() +"\n"+ "Model:" +getModel() + "\n"+"License Plate Number:"+ getLicense()
        + "\n"+"Fitness Expires:" +getFitnessExpDate()+ "\n"+ "Registration Expires:" + getRegistrationExpDate() +"\n" +"Insurance Expires:" +getInsuranceExpDate();
    }     


    public static void AddTruckInfo(Truck truck1) {
        final String USER = "root";
        final String PASS = "";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking";

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            final String sql = "INSERT INTO PASSWORD VALUES (?,?,?,?,?,?,?)";
            final PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, truck1.getTruckID());
            ps.setString(2, truck1.getMake());
            ps.setString(3, truck1.getModel());
            ps.setString(4, truck1.getLicense());
            ps.setString(5, truck1.getFitnessExpDate());
            ps.setString(6, truck1.getRegistrationExpDate());
            ps.setString(7, truck1.getInsuranceExpDate());
            ps.executeUpdate();

        } catch (final SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (final Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // final block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (final SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (final SQLException se) {
                se.printStackTrace();
            } // end final try
        } // end try

    }

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
public static void main(String [] args) {
    Truck truck1= new Truck(12,"Toyota","Voxy", "1432P","12/3/2020","12/13/2020","14/3/2020");
    System.out.println(truck1.toString());
    
}
}