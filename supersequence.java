/*
    Author : Aditya Chatterjee
   Longest common supersequence
   License : CC0 1.0 Universal
*/
import java.util.*;
public class supersequence
{ public static void main()
  { Scanner sc=new Scanner(System.in);
    System.out.println("Enter first string ");
    String s1=sc.nextLine();String sd=s1+" ";
    System.out.println("Enter second string ");
    String s2=sc.nextLine();s1=" "+s1;s2=" "+s2;
    int l1=s1.length(),l2=s2.length();
    int c[][]=new int[l1][l2];String s[][]=new String[l1][l2];
    for(int i=0;i<l1;i++)
     {c[i][0]=0;s[i][0]="";}
    for(int i=0;i<l2;i++)
     {c[0][i]=0;s[0][i]="";}
    for(int i=1;i<l1;i++)
     for(int j=1;j<l2;j++)
      if(s1.charAt(i)==s2.charAt(j))
       { c[i][j]=c[i-1][j-1]+1;s[i][j]=s[i-1][j-1]+sd.charAt(i);}
      else if(c[i-1][j]>=c[i][j-1])
        {c[i][j]=c[i-1][j];s[i][j]=s[i-1][j];}
      else
        {c[i][j]=c[i][j-1];s[i][j]=s[i][j-1];}
     int ans=l1+l2-2-c[l1-1][l2-1];
     System.out.println("The length of the longest common supersequence is : "+ans+" - "+s[l1-1][l2-1]);
    
    }
}
