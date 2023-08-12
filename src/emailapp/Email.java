package emailapp;
import java.util.*;
public class Email{
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity=500;
    private int defaultPasswordLength=10;
    private String companySuffix="vassarlabscompany.com";

    //Constructor to receive the firstname and lastname
    public Email(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
        

        //Call a method asking for department - return the department
        this.department=setDepartment();
 

        //Call a method that return a random password
        this.password = randomPassword(defaultPasswordLength);
        System.out.println("Your Password is: "+this.password);

        //Combine elements to generate email
        email=firstName.toLowerCase()+"."+lastName.toLowerCase()+"@"+department+"."+companySuffix;
        System.out.println("Your email is: "+email);
    }
    //Ask for the department
    private String setDepartment(){
        System.out.println("New worker: "+ firstName+"\nDepartment codes\n1 for Sales\n2 for Development\n3 for Accounting\n0 for None");
        System.out.print("Enter the Department code:");
        Scanner in=new Scanner(System.in);
        int depChoice = in.nextInt();
        if(depChoice==1){
            return "sales";
        }
        else if(depChoice==2){
            return "development";
        }
        else if(depChoice==3){
            return "accounting";
        }
        else{
            return "";
        }
    }
    //Generate the random password
    private String randomPassword(int length){
        String passwordSet="ABCDEFGHIJKALMNOPQRSTUVWXYZ0123456789!@#$%";
        char[] password=new char[length];
        for(int i=0;i<length;i++){
            int rand=(int) (Math.random() * passwordSet.length());
            password[i]=passwordSet.charAt(rand);
        }
        return new String(password);
    }
    //Set the capacity
    public void setMailboxCapacity(int capacity){
        this.mailboxCapacity=capacity;
    }


    public int getMailboxCapacity(){
        return mailboxCapacity;
    }

    public String showInfo(){
        return "DISPLAY NAME: "+firstName+" "+lastName+
                "\nCOMPANY EMAIL: "+email+
                "\nMAILBOX CAPACITY: "+mailboxCapacity+"mb";
    }

    
}