package BusinessLogic;

public class PaySlip {
    final double nis_tax = 0.025;
    final double income_tax = 0.25;
    final double nht_tax = 0.02;
    final double edu_tax = 0.0225;
    private double pay;
    private double totaltax;
    private double netpay;
    private double nis_to;
    private double nht_to;
    private double edu_to;
    private double income_to;


    public PaySlip(double nis,double income,double nht,double edu,double pay,double totaltax,double netpay,double nht_t,double edu_t,double income_t,double nis_t){
        nis=nis_tax;
        income=income_tax;
        nht=nht_tax;
        edu=edu_tax;
        this.pay=pay;
        this.totaltax=((this.pay*nis)+(this.pay*income)+(this.pay*nht)+(this.pay*edu));
        this.netpay=this.pay-this.totaltax;
        this.nht_to= this.pay*nht;
        this.edu_to=this.pay*edu;
        this.nis_to=this.pay*nis;
        this.income_to=this.pay*income;
    }

    public double getNISTax(){
        return this.nis_tax;
    }

    public double getEduTax(){
        return this.edu_tax;
    }

    public double getNHTTax(){
        return this.nht_tax;
    }

    public double getIncomeTax(){
        return this.income_tax;
    }

    public double getNISTotal(){
        return this.nis_to;
    }

    public double getEduTotal(){
        return this.edu_to;
    }

    public double getNHTTotal(){
        return this.nht_to;
    }

    public double getIncomeTotal(){
        return this.income_to;
    }
    public double getBasicPay(){
        return this.pay;
    }

    public double getNetPay(){
        return this.netpay;
    }

    public double getTotalTax(){
        return this.totaltax;
    }

}
