*/  
   Author : Aditya Chatterjee

   License : CC0 1.0 Universal
   
*/

import java.util.*;
public class subset_sum
{ public static void main()
    { Scanner sc=new Scanner(System.in);
      System.out.println("Enter nummber of data : ");
      int n=sc.nextInt();
      int a[]=new int[n],M=0;
      System.out.println("Enter "+n+" data consequetively");
      for(int i=0;i<n;i++)
       {a[i]=sc.nextInt();M+=a[i];}
       int m[]=new int[M+10];
for(int i=0; i<M+10; i++) m[i]=0;
m[0]=1;
for(int i=0; i<n; i++)
for(int j=M; j>=a[i]; j--)
m[j] |= m[j-a[i]];
System.out.println("The possible sum subsets are : ");
for(int i=0;i<=M;i++)
System.out.println(i+"=>"+m[i]+", ");
}
}
