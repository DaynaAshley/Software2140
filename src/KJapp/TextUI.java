package KJapp;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class TextUI {
    private Scanner scan;

    public TextUI(){
        this.scan= new Scanner(System.in);
    }
 
    public boolean login(){
        boolean value;
        System.out.println("\n======-======-=====-====-===-----------KJ TRUCKING------------===-====-=====-=====-====\n");
        System.out.println("\n\n\n\n\n\n\n\nWelcome...");
    
        System.out.println("\n=====-=====-=====-===----------------- LOGIN -----------------====-=====-====-=====\n");
        System.out.print("\t\tID number: ");
        int id=scan.nextInt();
        scan.nextLine();

        System.out.print("\t\tPassword: ");
        String password=scan.next();
        value= DataValidation.passwordCheck(id, password);
        return value;
    }

    public int loginAdmin(){
        System.out.println("\n-----------KJ TRUCKING-------------\n");
        System.out.println("\n--------------LOGIN----------------\n");
        System.out.print("ID number: ");
        int id=scan.nextInt();
        scan.nextLine();

        System.out.print("Password: ");
        String password=scan.next();
        int value=DataValidation.AdminpasswordCheck(id, password);
        return value;
    }


    public void go(){
        KJDatabase.Connect();
        boolean v=login();
        if (v){
            Menu();
        }
        else{
            System.out.println("Access Denied!");
            System.exit(0);
        }
    }
    
    public static void clrs() {
        try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            System.out.print("\033\143");
        }
    } catch (IOException | InterruptedException ex) {}
    }


    public void Menu(){
        String choice="";
     
        while(!choice.equals("6"))
        {
            
            System.out.println("\n-------------KJ TRUCKING------------\n\n");
            System.out.println("1. User Management");
            System.out.println("2. Driver Management"); 
            System.out.println("3. Route Management");
            System.out.println("4. Truck Management"); 
            System.out.println("5. Report Management");
            System.out.println("6. Exit Application");

            System.out.println("Enter choice:\n");
            choice=scan.next();

            switch(choice)
            {
                case "1":
                    clrs();
                    UserManagement();
                    break;
                
                case "2":
                     DriverManagement();
                    break;
                
                case "3":
                    RouteManagement();
                    break;

                case "5":
                    ReportManagement();
                    break;

                case "6":
                    System.out.println("Exiting..");
                    System.exit(0);
            }

        }
    }

    public void UserManagement(){
        String choice="";
     
        int value=loginAdmin();

        if (value==0){
            System.out.println("Access Denied!");
            Menu();
        }
        else{
            while(!choice.equals("5")){
                System.out.println("\n-------------USER MANAGEMENT------------\n");
                System.out.println("1. Add User");
                System.out.println("2. Update User");
                System.out.println("3. Delete User");
                System.out.println("4. Add Admin");
                System.out.println("5. Exit to Menu");
                System.out.println("Enter choice:");
                choice=scan.next();

                switch(choice)
                {
                case "1":
                    AddUser();
                    
                    break;
              
                case "2":
                    UpdateUser();
                    break;
                
                case "3":
                   DeleteUser();
                   break;
                
                case "4":
                    AddAdmin();
                    break;
                
                case "5":
                    System.out.println("Exiting to Menu...");
                    break;

                }
            }
        }
    }

    public void AddUser(){
        scan.nextLine();
        System.out.println("---------====---------ADD USER---------====-------\n");
        System.out.print("Enter the First Name of the new user:");
        String fname=scan.nextLine();

        System.out.print("\nEnter the Last Name of the new user:");
        String lname=scan.nextLine();

        System.out.print("\nEnter the Date of birth of the new user (xx/xx/xxxx): ");
        String dob=scan.nextLine();

        System.out.print("\nEnter the Email of the new user: ");
        String email=scan.nextLine();

        System.out.print("\nEnter the Telephone Number of the new user ((xxx)xxx-xxxx): ");
        String telephone=scan.nextLine();

        System.out.print("\nEnter the ID number of the new user: ");
        int id=scan.nextInt();

        scan.nextLine();
        System.out.print("\nNew User's password: ");
        String password=scan.nextLine();

        User user1=new User(fname,lname,email,dob,telephone,id,password);
        User.AddUser(user1);

        System.out.println("\nUser successfully added to the system!");    
    }

    public void UpdateUser(){
        scan.nextLine();
        System.out.println("------------------UPDATE USER--------------\n");
        System.out.print("Enter the ID number of the User you wish to edit:");
        int id=scan.nextInt();

        scan.nextLine();
        System.out.print("\nEnter the new Last Name of the user if applicable else enter the same last name:");
        String lname=scan.nextLine();

        System.out.print("\nEnter the new Email of the user if applicable else enter the same email: ");
        String email=scan.nextLine();

        System.out.print("\nEnter the new Telephone Number of if applicable else enter the same number: ");
        String telephone=scan.nextLine();

        System.out.print("\nEnter new User's password: ");
        String password=scan.nextLine();

        User.UpdateUser(id, lname, email, telephone, password);

        System.out.println("\nUser successfully updated!");    
    }

    public void DeleteUser(){
        scan.nextLine();
        System.out.println("------------------DELETE USER--------------\n");
        System.out.print("Enter the ID number of the User you wish to delete:");
        int id=scan.nextInt();

        scan.nextLine();
        System.out.print("Are you sure y/n:");
        String input=scan.nextLine();

        if (input.equals("y")){
            int status=User.DeleteUser(id);
            if (status==0){
                System.out.println("\nUser ID entered does not exist!");
            }else{
                System.out.println("\nUser successfully deleted!");    
            } 
        }
        else{
            System.out.println("\nReturning to the Menu..");
        }    
    }
    
    public void AddAdmin(){
        scan.nextLine();
        System.out.println("------------------ADD ADMIN--------------\n");
        System.out.print("\nEnter the Email of the user: ");
        String email=scan.nextLine();

        System.out.print("\nEnter the ID number of the user: ");
        int id=scan.nextInt();

        scan.nextLine();
        System.out.print("\nUser's password: ");
        String password=scan.nextLine();

        User.AddAdmin(id, email, password);
        System.out.println("\nNew Admin successfully added to the system!");    
    }

    public void DriverManagement(){
        String choice="";
     
        boolean value=login();

        if (value==false){
            System.out.println("Access Denied!");
            Menu();
        }
        else{
            while(!choice.equals("5")){
                System.out.println("\n-------------DRIVER MANAGEMENT------------\n");
                System.out.println("1. Add Driver");
                System.out.println("2. Update Driver");
                System.out.println("3. Delete Driver");
                System.out.println("4. Input Driver's Work Log");
                System.out.println("5. Exit to Menu");
                System.out.println("Enter choice:");
                choice=scan.next();

                switch(choice)
                {
                case "1":
                    AddDriver();
                    
                    break;
              
                case "2":
                    UpdateDriver();
                    break;
                
                case "3":
                   DeleteDriver();
                   break;
                
                case "4":
                    DriverWork();
                    break;
                
                case "5":
                    System.out.println("Exiting to Menu...");
                    break;

                }
            }
        }
    }

    public void AddDriver(){
        scan.nextLine();
        System.out.println("------------------ADD DRIVER--------------\n");

        System.out.print("Enter the Name of the new Driver:");
        String name=scan.nextLine();

        System.out.print("\nEnter the Address of the new Driver:");
        String address=scan.nextLine();

        System.out.print("\nEnter the Email of the new Driver: ");
        String email=scan.nextLine();

        System.out.print("\nEnter the Telephone Number of the new Driver ((xxx)xxx-xxxx): ");
        String telephone=scan.nextLine();

        System.out.print("\nEnter the ID number of the new Driver: ");
        int id=scan.nextInt();

        scan.nextLine();
        System.out.print("\nEnter the TRN of the new Driver (xxx-xxx-xxx): ");
        String trn=scan.nextLine();

        System.out.print("\nEnter the NIS of the new Driver: ");
        String nis=scan.nextLine();

        System.out.print("\nEnter the Driver's License of the new Driver: ");
        String dlicense=scan.nextLine();

        System.out.println("\nBanking Details:");
        System.out.print("\nEnter the Bank of the new Driver: ");
        String bank=scan.nextLine();

        System.out.print("\nEnter the branch of the new Driver: ");
        String branch=scan.nextLine();

        System.out.print("\nEnter the account number of the new Driver: ");
        int act=scan.nextInt();

        Driver driver1=new Driver(name,id,address,telephone,email,trn,nis,dlicense, bank, branch,act);
        Driver.AddDriver(driver1);

        System.out.println("\nDriver successfully added to the system!");    
    }


    public void UpdateDriver(){
        scan.nextLine();
        System.out.println("=====-====-=====-====------------------UPDATE DRIVER-----------------====-=====-=====-====\n");

        System.out.print("Enter the ID number of the User you wish to edit:");
        int id=scan.nextInt();

        Driver [] drive= Driver.FindDriver(id);

        if (drive[0]!=null){
            System.out.println("\n\n====-=====-===--------------------DRIVER'S INFORMATION--------------------===-====-====\n");
            Driver dr=drive[0];
            System.out.print(dr.toString());

            scan.nextLine();
            System.out.print("\n\nEnter the updated Name of the Driver if applicable else enter the same name:");
            String name=scan.nextLine();

            System.out.print("\nEnter the new Address of the new Driver if applicable else enter the same address:");
            String address=scan.nextLine();

            System.out.print("\nEnter the updated Email of the new Driver if applicable else enter the same email: ");
            String email=scan.nextLine();

            System.out.print("\nEnter the upadated Telephone Number of the new Driver ((xxx)xxx-xxxx) if applicable else enter the same number: ");
            String telephone=scan.nextLine();

            System.out.print("\nEnter the updated Driver's License of the new Driver: ");
            String dlicense=scan.nextLine();

            Driver [] driver1=Driver.UpdateDriver(id,name,telephone,address,dlicense,email);
            
            System.out.println("\n\n====-===-===--------------------UPDATED DRIVER'S INFORMATION--------------------===-===-====\n");
            Driver dr1=driver1[0];
            System.out.print(dr1.toString());


            System.out.println("\n\nDriver successfully updated!");    
        }
        else{
            System.out.println("ID entered for Driver does not exist!");
        }
    }
    
    public void DeleteDriver(){
        scan.nextLine();
        System.out.println("------------------DELETE DRIVER--------------\n");
        System.out.print("Enter the ID number of the Driver you wish to delete:");
        int id=scan.nextInt();

        scan.nextLine();
        System.out.print("\nAre you sure y/n:");
        String input=scan.nextLine();

        if (input.equals("y")){
            int status=Driver.DeleteDriver(id);
            if (status==0){
                System.out.println("\nDriver ID entered does not exist!");
            }else{
                System.out.println("\nDriver successfully deleted!");    
            }   
        }
        else{
            System.out.println("\nReturning to the Menu..");
        }  
    }
    
    public void DriverWork(){
        int value=loginAdmin();

        if (value==0){
            System.out.println("\nAccess Denied!");
        }
        else{
            scan.nextLine();
            System.out.println("\n\n==-====-===------------------INPUT DRIVER'S WORK LOG-----------------===-====-==\n");
            
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Date dateobj = new Date();
            String date= df.format(dateobj);
            
            System.out.print("Enter the Name of the Driver:");
            String name=scan.nextLine();
    
            System.out.print("\nEnter the ID of the new Driver:");
            int id= scan.nextInt();

            scan.nextLine();
            System.out.print("\nEnter the Destination of the delivery: ");
            String destname=scan.nextLine();

            System.out.print("\nEnter the Company the delivery was made for: ");
            String company=scan.nextLine();
    
            System.out.print("\nEnter the Goods Delivered: ");
            String load=scan.nextLine();
    
            System.out.print("\nEnter the weight of the goods (kg): ");
            double weight=scan.nextDouble();
    
            scan.nextLine();
            System.out.print("\nEnter the name of the Side Man 1 used for the delivery: ");
            String side1=scan.nextLine();
    
            System.out.print("\nEnter the name of the Side Man 2 used for the delivery:: ");
            String side2=scan.nextLine();
    
            System.out.print("\nEnter the ID of the truck used for the delivery: ");
            int truckid=scan.nextInt();

            DriverWork work= new DriverWork(name,id,company,destname,load,weight,side1,side2,truckid,date);
            Driver.DriverWork(work);

            System.out.println("\n\n==-====-===-==----------Driver's Log Successfully Logged---------==-===-====-==\n");
            System.out.println(work.toString());
        }
    }

    public void ReportManagement(){
        String choice="";
        
        int value=loginAdmin();

        if (value==0){
            System.out.println("Access Denied!");
            Menu();
        }
        else{
            while(!choice.equals("3")){
                System.out.println("\n\n===--==-------===-------------REPORT MANAGEMENT------------===----==---===\n");
                System.out.println("\t1. Profit/Loss Report");
                System.out.println("\t2. Drivers Work Log Report");
                System.out.println("\t3. Exit to Menu");
                System.out.print("\tEnter choice:");
                choice=scan.next();

                switch(choice)
                {
               /* case "1":
                    AddUser();
                    
                    break;*/
            
                case "2":
                    WorkReport();
                    break;
            
                case "3":
                    System.out.println("Exiting to Menu...");
                    break;

                }
            }
        }
    }
        

    public void WorkReport(){
        int value=loginAdmin();

        if (value==0){
            System.out.println("\nAccess Denied!");
        }
        else{
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

public void RouteManagement(){
    String choice="";
 
    int value=loginAdmin();

    if (value==0){
        System.out.println("Access Denied!");
        Menu();
    }
    else{
        while(!choice.equals("5")){
            System.out.println("\n-------------ROUTE MANAGEMENT------------\n");
            System.out.println("1. Add Route");
            System.out.println("2. Update Route");
            System.out.println("3. Delete Route");
            System.out.println("4. Calculate Route Expense");
            System.out.println("5. Exit to Menu");
            System.out.println("Enter choice:");
            choice=scan.next();

            switch(choice)
            {
            case "1":
                AddRoute();
                
                break;
          
            case "2":
                UpdateRoute();
                break;
            
            case "3":
               DeleteRoute();
               break;
            
            case "4":
               CalculateExpenses();
               break;
            
            case "5":
                System.out.println("Exiting to Menu...");
                break;

            }
        }
    }
}

public void AddRoute(){
    scan.nextLine();
    System.out.println("------------------ADD ROUTE--------------\n");

    System.out.print("Enter the name of the company:");
    String company_name=scan.nextLine();

    System.out.print("\nEnter the name of the destination:");
    String destination_name=scan.nextLine();

    System.out.print("\nEnter the cost of the fuel: ");
    double fuel_cost = scan.nextDouble();

    System.out.print("\nEnter the cost of the driver's wage: ");
    double driver_wage=scan.nextDouble();

    System.out.print("\nEnter the cost of the sidemen's wage: ");
    double side_wage=scan.nextDouble();

    System.out.print("\nEnter the cost of the second sidemen's wage: ");
    double side_wage2=scan.nextDouble();

    System.out.print("\nEnter the price of the delivery: ");
    double delivery_price=scan.nextDouble();

    System.out.print("\nEnter the billing length: ");
    int length_billing=scan.nextInt();

    Route route1=new Route(company_name,destination_name,fuel_cost,driver_wage, side_wage,side_wage2, delivery_price,length_billing);
    Route.AddRoute(route1);

    System.out.println("\nRoute was successfully added to the system!");    
}


public void UpdateRoute(){
    scan.nextLine();
    System.out.println("------------------UPDATE ROUTE--------------\n");

    System.out.print("Enter the company name of the route you wish to edit:");
    String company_name=scan.nextLine();

    ArrayList<Route>routes= Route.FindCompanyRoute(company_name);

    if (routes.get(0)!=null){
        System.out.println("\n\n--------------------ROUTES INFORMATION-----------------\n");
        for (Route i: routes){
            System.out.println(i.toString()+"\n"+"\n");
        }

        scan.nextLine();
        System.out.print("\n\nEnter the destination name of the route you wish to update:");
        String ndestination_name=scan.nextLine();

        Route [] r=Route.FindRoute(company_name, ndestination_name);

        if (r[0]!=null){
            System.out.print("\nEnter the updated price of delivery for the route if applicable else enter the same price:");
            double undelivery_price=scan.nextDouble();

            System.out.print("\nEnter the updated fuel cost of the route if applicable else enter the same cost:");
            double nfuel_cost=scan.nextDouble();

            System.out.print("\nEnter the updated driver wage of the route if applicable else enter the same wage: ");
            double ndriver_wage=scan.nextDouble();

            System.out.print("\nEnter the updated side wage of the route if applicable else enter the same wage: ");
            double nside_wage=scan.nextDouble();

            System.out.print("\nEnter the updated the second side wage of the route if applicable else enter the same wage: ");
            double nside_wage2=scan.nextDouble();

            System.out.print("\nEnter the updated billing length of the Route: ");
            int nlength_billing =scan.nextInt();


            Route [] nroute1=Route.UpdateRoute(company_name, ndestination_name, nfuel_cost, ndriver_wage, nside_wage, nside_wage2, undelivery_price, nlength_billing);
            
            System.out.println("\n\n--------------------UPDATED ROUTE'S INFORMATION-----------------\n");
            Route rt1=nroute1[0];
            System.out.print(rt1.toString());


            System.out.println("\n\nRoute successfully updated!");    
        }
        else{
            System.out.println("\nRoute entered for the company does not exist!");
        }
    }else{
        System.out.println("\nCompany name doesn't exist!");

    }
}

public void DeleteRoute(){
    scan.nextLine();
    System.out.println("------------------DELETE ROUTE--------------\n");
    System.out.print("Enter the company name of the route you wish to delete:");
    String company_name = scan.nextLine();

    System.out.print("Enter the route that you wish to delete:");
    String dest = scan.nextLine();

    scan.nextLine();
    System.out.print("\nAre you sure y/n:");
    String input=scan.nextLine();

    if (input.equals("y")){
        int route=Route.DeleteRoute(company_name,dest);
        if (route == 0){
            System.out.println("\nCompany and route entered does not exist!");
        }else{
            System.out.println("\nRoute successfully deleted!");    
        }   
    }
    else{
        System.out.println("\nReturning to the Menu..");
    }  
}

public void CalculateExpenses(){
    scan.nextLine();
    System.out.println("------------------CALCULATE ROUTE EXPENSES---------------\n");

    System.out.print("Enter the name of the company:");
    String company_name=scan.nextLine();

    System.out.print("\nEnter the name of the destination:");
    String destination_name=scan.nextLine();

    System.out.print("\nEnter the truck id for the truck that did the delivery:");
    int truck_id=scan.nextInt();

    DateFormat df = new SimpleDateFormat("dd/MM/yy");
    Date dateobj = new Date();
    String date= df.format(dateobj);

    double profit=Route.CalculateExpenses(company_name, destination_name);

    Route.AddCalculateExpenses(company_name, destination_name, truck_id, date, profit);

    System.out.println("\n-------CALCULATED EXPENSE---------\n");
    System.out.print("Date:");
    System.out.print(date);

    System.out.print("\nName of Company: ");
    System.out.print(company_name);

    System.out.print("\nDestination:");
    System.out.print(truck_id);

    if (profit==0.0){
        System.out.println("No data provided for route entered!");
    }else if(profit<0.0){
        System.out.print("\nA Loss is generated:$ ");
        System.out.print(profit);
    }else{
        System.out.print("A profit is generated of:$");
        System.out.print(profit);
    }
}
}

