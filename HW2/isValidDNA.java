package HW2;
import java.util.Scanner;
public class isValidDNA
{
    public static boolean checkDNA(String s) 
    {
        for (int i = 0; i < s.length(); i++) 
        {
            char c = s.charAt(i);
            if (c != 'A' && c != 'T' && c != 'C' && c != 'G')
                return false;
        }
        return true;
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String x= sc.nextLine();  
        if(checkDNA(x))
            {System.out.println("True");}
        else
            {System.out.println("False");}

    }
}