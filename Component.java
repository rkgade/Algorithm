*/  Author : Aditya Chatterjee
   Number of connected components
   Using DFS
   License : CC0 1.0 Universal
   */
   
import java.util.*;
class Edge 
{
    int a, b;
    Edge(int a, int b)
    {
       this.a = a; this.b =b;
    }       
}
public class Component 
{
    static ArrayList<ArrayList<Edge>> adj;static int visited[];
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), i = sc.nextInt();
        visited=new int[n];
        adj = new ArrayList<ArrayList<Edge>>(n+1);
        for(int j=0; j<n; j++)
            adj.add(new ArrayList<Edge>());
        for(int j=0;j<i;j++)
        {
            int a = sc.nextInt(), b = sc.nextInt();
            Edge x = new Edge(a, b);
            adj.get(a).add(new Edge(a, b));
            adj.get(b).add(new Edge(b, a));
        }
        int count = 0;long sum = 0;
        Iterator<Edge> it = adj.get(0).iterator();
        for(int j=0;j<n;j++)
        {
            if(visited[j]==0)
            {
                dfs(j);
                ++count;
            }
        }
        System.out.println(count);
    }    
    static void dfs(int j)
    {
        visited[j]=1;
        Iterator<Edge> it = adj.get(j).iterator();
        while(it.hasNext())
        {
            Edge edge=it.next();
            if(visited[edge.b]==0)
                dfs(edge.b);
        }
    }  
}
