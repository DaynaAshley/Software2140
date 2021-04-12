package BusinessLogic;


import java.text.DecimalFormat;
import java.io.FileOutputStream;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Security.User;



public class ExportPdf{


    public static  String ExportDriverPaySlip(DriverPaySlip slip) {
        Document PaySlipStatement = new Document();
        PdfWriter docWriter = null;
     
        DecimalFormat df = new DecimalFormat("0.00");
        String filename;
        String path="";
        DateFormat dwf = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        String date= dwf.format(dateobj);
      try {
        
          //special font sizes
          Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
          Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
        
          Driver driver=slip.getDriver();
          //file path
          filename="Driver PaySlip "+ driver.getName()+".pdf";


          path = "Pay Slips/" + filename;
          docWriter = PdfWriter.getInstance(PaySlipStatement , new FileOutputStream(path));

          PaySlipStatement.addAuthor("KJ Trucking");
          PaySlipStatement.addCreationDate();
          PaySlipStatement.addProducer();
          PaySlipStatement.addCreator("KJ Trucking");
          PaySlipStatement.addTitle("Pay Slip");
          PaySlipStatement.setPageSize(PageSize.LEGAL_LANDSCAPE.rotate());
       
          PaySlipStatement.open();
  
          Paragraph paragraph = new Paragraph("KJ TRUCKING"+"\nDRIVER PAY SLIP\n");

          paragraph.setFont(new Font(FontFamily.TIMES_ROMAN, 58, Font.BOLDITALIC, new BaseColor(0, 0, 0)));
        
          PdfPTable table = new PdfPTable(11);
          float[] columnWidths = new float[]{15f, 25f, 25f, 20f,20f, 20f, 25f, 25f,25f, 30f, 25f};
          table.setWidths(columnWidths);
       
  
  
          insertCell(table, "ID", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "Name", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "TRN", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "NIS", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "NIS Tax", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "Income Tax", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "Education Tax", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "NHT Tax", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "Basic Pay", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "Deductibles", Element.ALIGN_CENTER, 1, bfBold12);
          insertCell(table, "Net Pay", Element.ALIGN_CENTER, 1, bfBold12);
          table.setHeaderRows(1);

          
         
        
          insertCell(table, df.format(driver.getID()), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, driver.getName(), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, driver.getTRN(), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, driver.getNIS(), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getNISTotal()),Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getIncomeTotal()), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getEduTotal()), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getNHTTotal()), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getBasicPay()), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getIncomeTotal()), Element.ALIGN_CENTER, 1, bf12);
          insertCell(table, df.format(slip.getNetPay()), Element.ALIGN_CENTER, 1, bf12);
          
          paragraph.setAlignment(Element.ALIGN_CENTER);
          PaySlipStatement.add(paragraph);
          PaySlipStatement.add(new Paragraph("\n"));
          PaySlipStatement.add(table);

      }
      catch (DocumentException dex)
      {
       dex.printStackTrace();
      }
      catch (Exception ex)
      {
       ex.printStackTrace();
      }
      finally
      {
       if (PaySlipStatement != null){
        //close the document
        PaySlipStatement.close();
       }
       if (docWriter != null){
        //close the writer
        docWriter.close();
       }
      }
      return path;

  }

    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
   
      //create a new cell with the specified Text and Font
      PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
      //set the cell alignment
      cell.setHorizontalAlignment(align);
      //set the cell column span in case you want to merge two or more cells
      cell.setColspan(colspan);
      //in case there is no text and you wan to create an empty row
      if(text.trim().equalsIgnoreCase("")){
       cell.setMinimumHeight(10f);
      }
      //add the call to the table
      table.addCell(cell);
       
     }


     public static String ExportStaffPaySlip(StaffPaySlip slip) {
      Document PaySlipStatement = new Document();
      PdfWriter docWriter = null;
      User user=slip.getUser();
      DecimalFormat df = new DecimalFormat("0.00");
      DateFormat dwf = new SimpleDateFormat("dd/MM/yy");
      Date dateobj = new Date();
      String date= dwf.format(dateobj);
      String filename="Staff PaySlip "+ user.getFirstName()+" "+user.getLastName()+".pdf";
      String path="";
      try {
      
        //special font sizes
        Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
        Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
      
        //file path
        path ="Pay Slips/" + filename;
        docWriter = PdfWriter.getInstance(PaySlipStatement , new FileOutputStream(path));

        PaySlipStatement.addAuthor("KJ Trucking");
        PaySlipStatement.addCreationDate();
        PaySlipStatement.addProducer();
        PaySlipStatement.addCreator("KJ Trucking");
        PaySlipStatement.addTitle("Pay Slip");
        PaySlipStatement.setPageSize(PageSize.LEGAL_LANDSCAPE.rotate());

     
        PaySlipStatement.open();

        Paragraph paragraph = new Paragraph("KJ TRUCKING"+"\nSTAFF PAY SLIP\n");

        paragraph.setFont(new Font(FontFamily.TIMES_ROMAN, 58, Font.BOLDITALIC, new BaseColor(0, 0, 0)));
      
        PdfPTable table = new PdfPTable(13);
        float[] columnWidths = new float[]{15f, 25f, 25f, 20f,20f, 20f, 25f, 25f,25f,25f,25f, 30f, 25f};
        table.setWidths(columnWidths);
     


        insertCell(table, "ID", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Name", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "TRN", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "NIS", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "NIS Tax", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Income Tax", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Education Tax", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "NHT Tax", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Hours", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Rate", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Basic Pay", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Deductibles", Element.ALIGN_CENTER, 1, bfBold12);
        insertCell(table, "Net Pay", Element.ALIGN_CENTER, 1, bfBold12);
        table.setHeaderRows(1);
        


        insertCell(table, df.format(user.getID()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, user.getFirstName()+" "+user.getLastName(), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, user.getTRN(), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, user.getNIS(), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getNISTotal()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getIncomeTotal()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getEduTotal()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getNHTTotal()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getHours()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getRate()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getBasicPay()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getTotalTax()), Element.ALIGN_CENTER, 1, bf12);
        insertCell(table, df.format(slip.getNetPay()), Element.ALIGN_CENTER, 1, bf12);
        
        paragraph.setAlignment(Element.ALIGN_CENTER);
        PaySlipStatement.add(paragraph);
        PaySlipStatement.add(new Paragraph("\n"));
        PaySlipStatement.add(table);
        

    }
    catch (DocumentException dex)
    {
     dex.printStackTrace();
    }
    catch (Exception ex)
    {
     ex.printStackTrace();
    }
    finally
    {
     if (PaySlipStatement != null){
      //close the document
      PaySlipStatement.close();
     }
     if (docWriter != null){
      //close the writer
      docWriter.close();
     }
    }
    return path;

}
}