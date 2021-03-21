package BusinessLogic;

import java.sql.*;
import java.util.ArrayList;


public class RouteManager {
    
    public static double CalculateExpenses(String company,String dest){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        Double profit=0.0;
        try{
    
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql="SELECT * FROM ROUTES WHERE company_name =? and destination_name=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, company);
            ps.setString(2, dest);
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
                profit=((fdelivery)-(ffuel_cost+fdriver_wage+fside_wage+fside_wage2));
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
    
        return profit;
    }

    
    public static void AddCalculateExpenses(String company,String dest,int truck_id,String date,double profit){
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
            String sql4 ="INSERT INTO CALROUTES VALUES (?,?,?,?,?)";
    
            //code to create mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(sql4);
            preparedStmt.setString(1, company);
            preparedStmt.setString(2, dest);
            preparedStmt.setInt(3, truck_id);
            preparedStmt.setString(4, date);
            preparedStmt.setDouble(5, profit);
    
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
        }//end try
        
    }

    public static Route[] FindRoute(String company, String dest){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        Route froute;
        Route[] route_list = new Route[2];
        try{
    
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        
            String sql="SELECT * FROM ROUTES WHERE company_name =? and destination_name=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, company);
            ps.setString(2, dest);
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
                froute=new Route(fcompany_name,fdestination_name,ffuel_cost, fdriver_wage,fside_wage, fside_wage2,fdelivery,flength_billing);
                route_list[0] =froute;

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

   

    public static ArrayList <Route> FindCompanyRoute(String company_name){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        Route froute;
        ArrayList <Route> routes=new ArrayList<>();
        try{
    
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        

            String query = "SELECT * FROM ROUTES WHERE company_name =?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1,company_name);
            ResultSet result=pStmt.executeQuery();


            while(result.next() ){
                String fcompany_name=result.getString(1);
                String fdestination_name=result.getString(2);
                double ffuel_cost=result.getDouble(3);
                double fdriver_wage=result.getDouble(4);
                double fside_wage=result.getDouble(5);
                double fside_wage2=result.getDouble(6);
                double fdelivery=result.getDouble(7);
                int flength_billing=result.getInt(8);
                froute=new Route(fcompany_name,fdestination_name,ffuel_cost, fdriver_wage,fside_wage, fside_wage2,fdelivery,flength_billing);
                routes.add(froute);

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
    return routes;
    }
    

    public static String RouteDisplay(String company){
        ArrayList<Route>routes= FindCompanyRoute(company);
 
        if (routes.size()==0){
            return ("No");
        }
        else{
            for (Route i: routes){
                System.out.println(i.toString()+"\n"+"\n");
            }  
        }
        return ("");
    }

    public static ArrayList<Route> RouteNames(String company){
        ArrayList<Route>routes= FindCompanyRoute(company);
 
        if (routes.size()==0){
            return routes;
        }
        else{
             return routes;
            }  
        }
       
    public static void main(String [] args) {
      RouteDisplay("Seprod");
        
    }
}


