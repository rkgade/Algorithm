import java.util.*;
public class knapsack
{ 
  public static int w[],b[],W;
  public static void main()
      { 
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of weigths ");
        int n=sc.nextInt()+1;
        w=new int[n];b=new int[n];
        System.out.println("Enter the weigths ");
        for(int i=1;i<n;i++)
         w[i]=sc.nextInt();
        System.out.println("Enter the benifits ");
        for(int i=1;i<n;i++)
         b[i]=sc.nextInt();
        System.out.println("Enter maximum weigth ");
        W=sc.nextInt();
        int x=B(n-1,W);
        System.out.println("The maximum benifit : "+x);
  }
  public static int B(int i,int q)
  { 
      if(w[i]>q)
        return B(i-1,q);
      else if(i==0)
        return 0;
      else if(q==0)
        return 0;
      return (int)Math.max(B(i-1,q),b[i]+B(i-1,q-w[i]));
  }
}
