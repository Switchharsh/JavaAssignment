import java.util.Scanner;
public class Main
{
    public static void main(String[] args) {
        // asking for an input
        System.out.println("Enter a number and you will be shown that day of week");
        // Scanning for an input
        Scanner number = new Scanner(System.in);
        // saving the input in a variable
        int a = number.nextInt();
        // starting switch case
        switch(a){
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
            default:
                // sending error message if the number is more than 7
                System.out.println("Enter a valid number");
                break;
                
        }
        
    }
}
