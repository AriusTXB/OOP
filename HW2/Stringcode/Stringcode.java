package HW2.Stringcode;
import java.util.HashSet;
import java.util.Set;
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
        for (int i = 0; i < str.length()-1; i++) 
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
    public static boolean stringIntersect(String a, String b, int len) 
    {
        Set<String> substringsA = getSubstrings(a, len);
        for (String substring : getSubstrings(b, len)) {
            if (substringsA.contains(substring)) {
                return true;
            }
        }
        return false;
    }
    private static Set<String> getSubstrings(String s, int len) 
    {
        Set<String> substrings = new HashSet<>();
        for (int i = 0; i <= s.length() - len; i++) {
            substrings.add(s.substring(i, i + len));
        }
        return substrings;
    }

    public static void main(String[] args) 
    {
        System.out.println(blowup("a3tx2z"));
        System.out.println(maxRun("xxyyyz"));   
    }
}
