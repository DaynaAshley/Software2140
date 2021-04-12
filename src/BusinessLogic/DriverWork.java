package BusinessLogic;

import java.sql.*;


public class DriverWork {
    private String driver_name;
    private int id;
    private String dest_name;
    private String load_name;
    private double weight;
    private String side1_name;
    private String side2_name;
    private int truck_id;   
    private String company; 
    private String date;


    public DriverWork(String dname,int id, String company,String dest, String load, double wght, String side1name, String side2name, int truckid, String date){
        this.dest_name=dest;
        this.company=company;
        this.load_name=load;
        this.weight=wght;
        this.side1_name=side1name;
        this.side2_name=side2name;
        this.truck_id=truckid;
        this.driver_name=dname;
        this.id=id;
        this.date=date;
    }

    public String  getDriverName(){
        return this.driver_name;
    }

    public String getDate(){
        return this.date;
    }

    public String getCompany(){
        return this.company;
    }

    public int getID(){
        return this.id;
    }

    public String getDestName(){
        return this.dest_name;
    }

    public String getLoadName(){
        return this.load_name;
    }

    public double getWeight(){
        return this.weight;
    }

    public String getSide1Name(){
        return this.side1_name;
    }

    public String getSide2Name(){
        return this.side2_name;
    }

    public int getTruckID(){
        return this.truck_id;
    }

    public String toString(){
        return "Driver's Name: "+getDriverName()+"\n"+"Driver's ID: "+getID()+"\n"+"Destination of Delivery: "+getDestName()+"\n"+"Company Delievered For: "+getCompany()
        +"\n"+"Items Delivered: "+getLoadName()+"\n"+"Weight of Items: "+getWeight()+" kg"
        +"\n"+"Name of the first sideman used: "+getSide1Name()+"\n"+
        "Name of the second sideman used:"+getSide2Name()+"\n"+
        "ID of Truck used for the delivery: "+getTruckID() +"\n"+"Date of Delivery: "+getDate();
    }

    public static Double TotalPay(int id){
        final String USER= "root";
        final String PASS="";
        
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        Double sumwage=0.0;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql= "SELECT * FROM WORK WHERE id = ?";
            PreparedStatement preps= conn.prepareStatement(sql);
            preps.setInt(1, id);
            ResultSet rs= preps.executeQuery();
           
            while(rs.next()){
                sumwage+= rs.getDouble(5);
              
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
       return sumwage;
    }
    

  
}