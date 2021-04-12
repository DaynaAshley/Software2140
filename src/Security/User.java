package Security;

import java.sql.*;


public class User {
    private String first_name;
    private String last_name;
    private String email;
    private String dob;
    private String tel_num;
    private int id_num;
    private String password;
    private String trn;
    private String nis;
    
    public User(String fname, String lname, String email, String dob, String telnum, int id, String password,String trn,String nis){
        this.first_name=fname;
        this.last_name=lname;
        this.email=email;
        this.dob=dob;
        this.id_num=id;
        this.tel_num=telnum;
        this.password=password;
        this.trn=trn;;
        this.nis=nis;
    }

    public String getFirstName(){
        return this.first_name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public String getTelephone(){
        return this.tel_num;
    }
    public String getEmailAddress(){
        return this.email;
    }

    public String getDOB(){
        return this.dob;
    }

    public String getTRN(){
        return this.trn;
    }
    public String getNIS(){
        return this.nis;
    }
    public int getID(){
        return this.id_num;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(String newemail){
        this.email= newemail;
    }

    public void setLastName(String newlstname){
        this.last_name= newlstname;
    }

    public void setFirstName(String newfname){
        this.first_name=newfname;
    }

    public void setPassword(String newpass){
        this.password=newpass;
    }

    public void setDOB(String newdob){
        this.dob=newdob;
    }

    public void setTelephoneNumber(String newtel){
        this.tel_num=newtel;
    }

    public String toString(){
        return "First Name:"+getFirstName()+"\n"+"Last Name:"+getLastName()+"\n"+"Date of Birth:"+getDOB()+"\n"+"Email Address:"+getEmailAddress()
        +"\n"+"Telephone Number:"+getTelephone()+"\n"+"TRN:"+getTRN()+"\n"+"NIS:"+getNIS();
    }
    public static void AddUser(User user1){
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

            String fname=user1.getFirstName();
            String lname=user1.getLastName();
            int id=user1.getID();
            String telephone=user1.getTelephone();
            String dob= user1.getDOB();
            String email= user1.getEmailAddress();
            String password=user1.getPassword();
            String trn=user1.getTRN();
            String nis=user1.getNIS();

            String sql= "INSERT INTO PASSWORD VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps= conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, dob);
            ps.setString(5, email);
            ps.setString(6, telephone);
            ps.setString(7, password);
            ps.setString(8, trn);
            ps.setString(9, nis);

            ps.executeUpdate();
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

    public static void AddAdmin(int id, String email, String password){
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


            String sql= "INSERT INTO ADMIN VALUES (?,?,?)";
            PreparedStatement ps= conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
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

    public static void UpdateUser(int id, String lname, String email, String telephone, String password){
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
         
            
            String sql2 = "Update PASSWORD Set last_name=?, email=?, telephone=?, password=? Where id=?";
            PreparedStatement prep= conn.prepareStatement(sql2);
            prep.setString(1, lname);
            prep.setString(2, email);
            prep.setString(3, telephone);
            prep.setString(4, password);
            prep.setInt(5, id);
            prep.executeUpdate();

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


    public static int DeleteUser(int id){
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

            String sql= "DELETE FROM PASSWORD WHERE id = ?";
            PreparedStatement pr= conn.prepareStatement(sql);
            pr.setInt(1, id);

            status=pr.executeUpdate();
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

    public static User[] FindUser(int id){
        final String USER= "root";
        final String PASS="";

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/kjtrucking"; 

        Connection conn = null;
        Statement stmt = null;
        User user;
        User[] i=new User[4];
        

        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql= "SELECT * FROM PASSWORD Where id=?";
            PreparedStatement preps= conn.prepareStatement(sql);
            preps.setInt(1, id);
            ResultSet rs= preps.executeQuery();
           
            while(rs.next()){
                int uid= rs.getInt(1);
                String first_name=rs.getString(2);
                String last_name= rs.getString(3);
                String dob= rs.getString(4);
                String email= rs.getString(5);
                String tel_num= rs.getString(6);
                String password= rs.getString(7);
                String trn=rs.getString(8);
                String nis=rs.getString(9);

                user=new User(first_name, last_name, email, dob, tel_num,uid, password,trn,nis);
                i[0]=user;
            }
     }
     catch(SQLException se){
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
    return i;
}

}

