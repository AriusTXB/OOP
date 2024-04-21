package HW2;

public class Stringcode 
{
    public static String blowup(String str) 
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) 
        {
            char c = str.charAt(i);
            if (Character.isDigit(c)) 
            {
                int count = Character.getNumericValue(c);
                if (i + 1 < str.length()) 
                {
                    char nextChar = str.charAt(i + 1);
                    for (int j = 0; j < count; j++) 
                    {
                        result.append(nextChar);
                    }
                }
            } 
            else 
            {
                result.append(c);
            }
        }
        return result.toString();
    }
    public static int maxRun(String str)
    {
        int count = 1;
        int temp = 1;
        for (int i = 0; i < str.length(); i++) 
        {
            if (str.charAt(i)==str.charAt(i+1))
                {
                    temp++;
                }
            else
                {
                    temp =1;
                }
            if(temp >= count)
                {
                    count = temp;
                }
        }
        return count;

    }
    public static void main(String[] args) 
    {
        System.out.println(blowup("a3tx2z"));
        System.out.println(maxRun("xxyyyz"));   
    }
}
