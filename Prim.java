*/  Author : Aditya Chatterjee
   Minimum Spanning Tree - Prim's Algorithm
   Complexity : O(VlogV+ElogV+V)=O(ElogV)
   Using a priority queue
   License : CC0 1.0 Universal
*/
import java.util.*;
class edge implements Comparable<edge>
{
    int a,b,c;
    edge(int a,int b,int c) 
   {
        this.a=a;this.b=b;this.c=c;
   }
    public int compareTo(edge e)
    {
        return Integer.compare(this.c,e.c);
    }
}
public class MinimumSpanningTree
{
    static int parent[];
    public static void main(String args[])
   {
       Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        parent=new int[n+1];
        ArrayList<ArrayList<edge>> arr=new ArrayList<ArrayList<edge>>(n+1);
        for(int i=0;i<n+1;i++)
            arr.add(new ArrayList<edge>());
        Arrays.fill(parent,-1);
        while(m-->0)
        {
            int a=sc.nextInt(),b=sc.nextInt(),c=sc.nextInt();
            arr.get(a).add(new edge(a,b,c));
            arr.get(b).add(new edge(b,a,c));
        }
        int source=sc.nextInt();System.out.println("source:"+source);
        int count=0,sum=0;
        PriorityQueue<edge> col=new PriorityQueue<edge>();
        Iterator<edge> ii=arr.get(source).iterator();
        while(ii.hasNext())
        {
            col.add(ii.next());
        }
        while(count != n-1)
        {
            edge current=col.remove();
            int a=current.a,b=current.b,c=current.c;
            if(getP(a)!= getP(b))
            {
                ++count;sum+=c;union(a,b);System.out.println("sum:"+sum);
                Iterator<edge> ii1=arr.get(b).iterator();
                while(ii1.hasNext())
                    col.add(ii1.next());                
            }
        }
        System.out.println(sum);
    }
    public static int getP(int a)
    {
        while(parent[a]!= -1)
            a=parent[a];
        return a;
    }
    public static void union(int a,int b)
    {
        parent[getP(b)]=getP(a);
    }
}
