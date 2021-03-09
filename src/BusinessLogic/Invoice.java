package BusinessLogic;

public class Invoice {
    private String invoice_num;
    private int hours;

    public Invoice(String num, int hour){
        this.invoice_num=num;
        this.hours=hour;
    }

    public String getInvoiceNumber(){
        return this.invoice_num;
    }

    public int getHours(){
        return this.hours;
    }

}
