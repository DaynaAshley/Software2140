package BusinessLogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import Security.User;

public class StaffPaySlip extends PaySlip {
    User user;
    private double hours;
    private double rate;    

    public StaffPaySlip(User user1, double hour, double staff_rate,double nis,double income,double edu,double nht,double pay,double totaltax,double netpay,double nht_t,double nis_t,double edu_t,double income_t) {
        super(nis,income,nht,edu,pay,totaltax,netpay,nht_t,edu_t,income_t,nis_t);
        this.user=user1;
        this.hours=hour;
        this.rate=staff_rate;                
    }

    public User getUser(){
        return this.user;
    }
    
    public double getHours(){
        return this.hours;
    }

    public double getRate(){
        return this.rate;
    }
    

    public static ArrayList<StaffPaySlip> GeneratePaySlip(int id,double hours, double rate){
    
        ArrayList<StaffPaySlip> info =new ArrayList<>();
        User [] user=User.FindUser(id);
        if (user[0]==null){
            return info;
        }
        else{
            User user1=user[0];
            double pay=(hours*rate);
            StaffPaySlip slip=new StaffPaySlip(user1, hours, rate, 0.0, 0.0, 0.0, 0.0, pay, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            info.add(slip);         
            return info;
        }
    }

    public static void SendSlip(StaffPaySlip slip,String user){
        String pdf=ExportPdf.ExportStaffPaySlip(slip);
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        String date= df.format(dateobj);
        EmailNotification.SendAttachment(user,"PaySlip", "Please See Attachment", pdf, date);

    }

}