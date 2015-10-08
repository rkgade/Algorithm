
*/  Author : Aditya Chatterjee
   Verifies the 8 queen problem state in 8*8 board
   Using DFS
   License : CC0 1.0 Universal
   */
   
import java.io.*;
import java.util.*;

public class queen {

    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     int a[]=new int[8];int b[][]=new int[8][3];int t=0,g=0;
     for(int j=0;j<8;j++){
     String s=sc.nextLine();String ss="";g=0;
     for(int i=0;i<8;i++)
         {
         if(s.charAt(i)=='*') {
             g++;
             ss='1'+ss;}
         else ss='0'+ss;
     }
         if(g!=1) t=1;
     a[j]=Integer.parseInt(ss,2);
     int c=1;
     for(int y=j+1;y<8;y++,c++)
     {b[y][0]=b[y][0] | (a[j]>>c);b[y][1]=b[y][1] | (a[j]<<c);b[y][2]=b[y][2] | a[j];}
     if((a[j] & b[j][0]) != 0 || (a[j] & b[j][1]) != 0 || (a[j] & b[j][2]) != 0) 
     {t=1;}
    }
    if(t==1)
        System.out.println("invalid");
    else
        System.out.println("valid");
    }
}
