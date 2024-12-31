package AlgorithmPrograms;

import java.util.Scanner;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomizeMessage {
    public static void main(String[] args) {
        String message = "Hello <<name>>, We have your full name as <<full name>> in our system. "
                + "Your contact number is 91-xxxxxxxxxx. Please, let us know in case of any clarification. "
                + "Thank you, BridgeLabz 01/01/2016.";
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter your full name: ");
        String fullName = sc.nextLine();
        System.out.print("Enter your contact number: ");
        String contactNumber = sc.nextLine();
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String namePattern = "<<name>>";
        String fullNamePattern = "<<full name>>";
        String mobilePattern = "91-xxxxxxxxxx";
        String datePattern = "\\d{2}/\\d{2}/\\d{4}";
        String modifiedMessage = message;
        modifiedMessage = modifiedMessage.replaceAll(namePattern, firstName);
        modifiedMessage = modifiedMessage.replaceAll(fullNamePattern, fullName);
        modifiedMessage = modifiedMessage.replaceAll(mobilePattern, contactNumber);
        modifiedMessage = modifiedMessage.replaceAll(datePattern, currentDate);
        System.out.println("\nModified Message:");
        System.out.println(modifiedMessage);
        sc.close();
    }
}

