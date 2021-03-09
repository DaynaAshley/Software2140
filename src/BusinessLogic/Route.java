package BusinessLogic;

import java.sql.*;


public class Route {
    Truck truck;
    private String destination_name;
    private double fuel_cost;
    private double driver_wage;
    private double side_wage;
    private double side_wage2;
    private double delivery_price;
    private int length_billing;
    Company company;
    private String company_name;

    public Route(String company, String destname, double fuel, double driverwage, double sidewage,double sidewage2,
            double deliveryprice, int billlength) {
        this.destination_name = destname;
        this.fuel_cost = fuel;
        this.driver_wage = driverwage;
        this.side_wage = sidewage;
        this.delivery_price = deliveryprice;
        this.length_billing = billlength;
        this.side_wage2=sidewage2;
        this.company_name = company;
    }

    public String getDestName() {
        return this.destination_name;
    }

    public double getFuelCost() {
        return this.fuel_cost;
    }

    public String getCompany(){
        return this.company_name;
    }
    public int getBillingCycle() {
        return this.length_billing;
    }

    public double getDriverWage() {
        return this.driver_wage;
    }

    public double getSideWage1() {
        return this.side_wage;
    }

    public double getSideWage2(){
        return this.side_wage2;
    }

    public double getDeliveryPrice() {
        return this.delivery_price;
    }

    public void setFuelCost(double newfuel) {
        this.fuel_cost = newfuel;
    }

    public void setDriverWage(double newdriverwage) {
        this.driver_wage = newdriverwage;
    }

    public void setSideWage(double newsidewge) {
        this.side_wage = newsidewge;
    }

    public void setPriceofDelivery(double newpriceofdelivery) {
        this.delivery_price = newpriceofdelivery;
    }

    public void setBillingCycle(int billing) {
        this.length_billing = billing;
    }

    public String toString() {
        return "Route Destination:" + getDestName() + "\n" + "Fuel Cost:$" + getFuelCost() + "\n" + "Driver's Wage:$"
                + getDriverWage() + "\n" + "Side-Men 1 Wage:$" + getSideWage1() + "\n"+"Side-Men 2 Wage:$" +getSideWage2()+"\n"+"Price of Delivery:$"
                + getDeliveryPrice() + "\n" + "Billing Cycle Length:" + getBillingCycle();
    }

    
    public static void AddRoute(Route route){
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
                //mysql insert statement
                String sql3 ="INSERT INTO ROUTES VALUES (?,?,?,?,?,?,?,?)";
                //code to create mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(sql3);
              
                preparedStmt.setString(1,route.getCompany());
                preparedStmt.setString(2, route.getDestName());
                preparedStmt.setDouble(3, route.getFuelCost());
                preparedStmt.setDouble(4,route.getDriverWage());
                preparedStmt.setDouble(5, route.getSideWage1());
                preparedStmt.setDouble(6, route.getSideWage2());
                preparedStmt.setDouble(7, route.getDeliveryPrice());
                preparedStmt.setInt(8, route.getBillingCycle());

                //code to execute the preparedstatement
                preparedStmt.executeUpdate();


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
        }
    
    public static  Route[] UpdateRoute(String company_name,String destination_name, double fuel_cost, double driver_wage, double side_wage,double side_wage2, double delivery_price, int length_billing){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        Route nroute;

        Route[] route_list = new Route[1];

        try{
    
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            

            String sql3="Update ROUTES set fuel_cost=?, driver_wage=?, side_wage= ?,  side_wage2=?, delivery_price= ?,billing_cycle= ? Where company_name=? and destination_name=?";
            PreparedStatement preparedStmt = conn.prepareStatement(sql3);

            preparedStmt.setDouble(1, fuel_cost);
            preparedStmt.setDouble(2, driver_wage);
            preparedStmt.setDouble(3,side_wage);
            preparedStmt.setDouble(4, side_wage2);
            preparedStmt.setDouble(5, delivery_price);
            preparedStmt.setInt(6,length_billing);
            preparedStmt.setString(7, company_name);
            preparedStmt.setString(8, destination_name);


            preparedStmt.executeUpdate();

            String sql="SELECT * FROM ROUTES WHERE company_name =? and destination_name= ?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, company_name);
            ps.setString(2, destination_name);
            ResultSet result= ps.executeQuery();

            while(result.next()){
                String fcompany_name=result.getString(1);
                String fdestination_name=result.getString(2);
                double ffuel_cost=result.getDouble(3);
                double fdriver_wage=result.getDouble(4);
                double fside_wage=result.getDouble(5);
                double fside_wage2=result.getDouble(6);
                double fdelivery=result.getDouble(7);
                int flength_billing=result.getInt(8);
                nroute=new Route(fcompany_name,fdestination_name,ffuel_cost, fdriver_wage,fside_wage, fside_wage2,fdelivery,flength_billing);
                route_list[0] =nroute;

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
        return route_list;
    }


    public static int DeleteRoute(String company_name, String dest){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        int route = 0;

        try{
    
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            


            String sql3="DELETE FROM ROUTES Where company_name = ? and destination_name=?";

            PreparedStatement preparedStmt = conn.prepareStatement(sql3);
            
            preparedStmt.setString(1, company_name);
            preparedStmt.setString(2, dest);
            //code to execute the program
            route = preparedStmt.executeUpdate();

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
        return route;
         
    }

}

