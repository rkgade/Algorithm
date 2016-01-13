// Sum subset by Catalan numbers
// Aditya Chatterjee
// GeeksforGeeks 12-01-2016
// IIEST, Shibpur.
import java.util.Scanner;
public class Sum_Subset
{
    public static void main()
    {
        Scanner sc=new Scanner(System.in);
        // Entering the number of elements in the set
        System.out.println("Enter value of 2p : ");
        long p2=sc.nextLong();
        long sum=0; // Computes the total number of subsets to be checked
        long value[][]=new long[2][(int)p2/2+1]; // 2 dimensional array to store 2 information
        // value[0][i] = C(2i,i)  
        // value[0][i] = (value[0][i-1]*(2*i-1)*(2*i)/(i))/i  -> follows from binomial theorem
        // value[1][i] = C(2p,2i)
        // value[1][i]=(value[1][i-1]*(p2-2*i+2)*(p2-2*i+1)/(2*i-1))/(2*i) -> follows from binomial theorem
        value[0][0]=1;   // Base case to start with
        value[1][0]=1;   // Base case
        // Looping through potential values of i
        for(int i=1;2*i<=(int)p2;i++)  // O(n) time complexity
        {
            // Computes value[][] using previous values
            value[0][i]=(value[0][i-1]*(2*i-1)*(2*i)/(i))/i;
            value[1][i]=(value[1][i-1]*(p2-2*i+2)*(p2-2*i+1)/(2*i-1))/(2*i);
            sum+=(value[0][i]/2 - value[0][i]/(i+1))*value[1][i];
        }// End of loop
        // Output the result stored in sum
        System.out.println("The minimum number of comparisons required is : "+sum);
    }
}
/*
 *  Example 1 :
 *  Input: 8
 *  Output: 231
 *  
 *  Example 2 :
 *  Input: 5
 *  Output: 5
 *  
 *  Example 3:
 *  Input: 10
 *  Output: 2289
 *  
 *  Example 4:
 *  Input: 11
 *  Output: 7029
 *  
 */
        
            
