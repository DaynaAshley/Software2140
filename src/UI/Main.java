package UI;

import java.util.Scanner;

public class Main {
    public static void main(String [] args)  throws InterruptedException{
        String choice="";
    
        Scanner scan;

    
        scan= new Scanner(System.in);
     
        while(!choice.equals("3"))
        {
            
            System.out.println("\n======-======-=====-====-===-----------KJ TRUCKING------------===-====-=====-=====-====\n");    
            System.out.println("1. TextUI");
            System.out.println("2. GUI"); 
            System.out.println("3. Exit");
            

            System.out.println("Enter choice:\n");
            choice=scan.next();

            switch(choice)
            {
                case "1":
                    TextUI ui = new TextUI();
                    ui.go();
                    break;
                
                case "2":
                    GUI.main(null);
                    break;

                
                case "3":
                
                    System.out.println("Exiting..");
                    System.exit(0);
            }
            

        }
        scan.close();        
        
    }
    
}
    
