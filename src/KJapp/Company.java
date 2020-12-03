package KJapp;

public class Company {
    private String company_name;
    private String company_address;
    private String telephone_num;
    private String email;
    Route route;
    
    public Company(String compname, String address, String telephone, String email,String destname, double fuel, double driverwage, double sidewage,double sidewage2, double deliveryprice, int billlength,Truck truck){
        this.company_name=compname;
        this.company_address=address;
        this.telephone_num=telephone;
        this.email=email;
        this.route=new Route(company_name,destname,fuel,driverwage,sidewage,sidewage2,deliveryprice,billlength);
    }

    public String getCompanyName(){
        return this.company_name;
    }

    public String toString(){
        return "Company Name:"+getCompanyName()+route.toString();
    }
}
