package BusinessLogic;

import java.sql.*;
import java.util.ArrayList;



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


    public static Truck[] FindTruck(int id){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        Truck untruck;
        Truck[] truckarr= new Truck[2];
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql= "SELECT * FROM TRUCK WHERE truck_id = ?";
            PreparedStatement preps= conn.prepareStatement(sql);
            preps.setInt(1, id);
            ResultSet rs= preps.executeQuery();
           
            while(rs.next()){
                int uid= rs.getInt(1);
                String umake=rs.getString(2);
                String umodel= rs.getString(3);
                String ulnum= rs.getString(4);
                String ufitex= rs.getString(5);
                String uregex= rs.getString(6);
                String uninsex= rs.getString(7);

                untruck=new Truck(uid,umake,umodel,ulnum,ufitex,uregex,uninsex);
                truckarr[0]=untruck;
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
        }
        return truckarr;
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

            final String sql = "INSERT INTO TRUCK VALUES (?,?,?,?,?,?,?)";
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
       
      public static Truck[] UpdateTruckInfo(int newTruckID ,  String newTruckMake,  String newTruckModel, String newTruckLiscence, String newTruckFitness,  String newTruckReg,  String newTruckIns){
        final String USER= "root";
        final String PASS="";
        Truck[] truckarr = new Truck[10];
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
            prep.setInt(7, newTruckID);
            prep.executeUpdate();

            String sql3= "SELECT * FROM TRUCK WHERE truck_id = ?";
            PreparedStatement pr= conn.prepareStatement(sql3);
            pr.setInt(1, newTruckID);
            ResultSet rs1= pr.executeQuery();
            while(rs1.next()){
                int uid= rs1.getInt(1);
                String umake=rs1.getString(2);
                String umodel= rs1.getString(3);
                String ulnum= rs1.getString(4);
                String ufitex= rs1.getString(5);
                String uregex= rs1.getString(6);
                String uninsex= rs1.getString(7);
                
                Truck untruck=new Truck(uid,umake,umodel,ulnum,ufitex,uregex,uninsex);
                truckarr[0]=untruck;
            
            }

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
        return truckarr;
    }

    public static ArrayList<Truck> AvaliableTrucks(){
        final String USER= "root";
        final String PASS="";
        ArrayList <Truck> truckarr=new ArrayList<>();
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
         
        

            String sql3= "SELECT * FROM TRUCK";
            PreparedStatement pr= conn.prepareStatement(sql3);
            ResultSet rs1= pr.executeQuery();
            while(rs1.next()){
                int uid= rs1.getInt(1);
                String umake=rs1.getString(2);
                String umodel= rs1.getString(3);
                String ulnum= rs1.getString(4);
                String ufitex= rs1.getString(5);
                String uregex= rs1.getString(6);
                String uninsex= rs1.getString(7);
                
                Truck untruck=new Truck(uid,umake,umodel,ulnum,ufitex,uregex,uninsex);
                truckarr.add(untruck);
            
            }

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
        return truckarr;
    }


    public static ArrayList<Truck> TruckNotifications(){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        ArrayList<Truck> info= new ArrayList<>();
        Truck truck;
        
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql= "SELECT * FROM TRUCK";
            PreparedStatement preps= conn.prepareStatement(sql);
            ResultSet rs= preps.executeQuery();
           
            while(rs.next()){
                int uid= rs.getInt(1);
                String umake=rs.getString(2);
                String umodel= rs.getString(3);
                String ulnum= rs.getString(4);
                String ufitex= rs.getString(5);
                String uregex= rs.getString(6);
                String uninsex= rs.getString(7);

                truck=new Truck(uid,umake,umodel,ulnum,ufitex,uregex,uninsex);
                info.add(truck);
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
        }
        return info;
    }

    public static String FormatMessage(ArrayList<Truck> info){
        String message="";
        int num=0;
        if (info.isEmpty()){
            message="No Info Entered";
            return message;
        }
        else{
            for (Truck i:info){
                
                message+="\n"+"#"+String.valueOf(num++)+"\n"+"Truck ID:"+ String.valueOf(i.getTruckID())+"\n"+"Fitness Expiration Date:"+ i.getFitnessExpDate()+"\n"+
                "Insurance Expiration Date:"+i.getInsuranceExpDate()+"\n"+"Fitness Expiration Date:"+i.getInsuranceExpDate()+"\n";
            }

            return message;
        }
    }

}