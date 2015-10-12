/*
  Aditya Chatterjee
  Bellman Ford Algorithm
  */
import java.util.*;

class Edge implements Comparable<Edge>{
    int a, b, cost;
    Edge(int a, int b, int cost){
        this.cost = cost; this.a = a; this.b =b;
    }
    
    public int compareTo(Edge x){
        return Integer.compare(this.cost, x.cost);
    }
}

public class bellman_ford {
    static int[] dist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
       
        ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
        for(int i=0; i<n+1; i++)
            adj.add(new ArrayList<Edge>());
        dist=new int[n];
        while(m-- > 0){
            int a = sc.nextInt(), b = sc.nextInt(), cost = sc.nextInt();
            Edge x = new Edge(a, b, cost);
            adj.get(a).add(new Edge(a, b, cost));
            //adj.get(b).add(new Edge(b, a, cost));
        }
        int s = sc.nextInt();
        int count = 0, sum = 0;
        
        //Iterator<Edge> it = adj.get(s).iterator();
        for(int i=0;i<n;i++)
          dist[i]=99999;
        dist[s]=0;
        for(int j=0;j<n-1;j++)
        for(int i=0;i<n;i++){
            Iterator<Edge> it=adj.get(i).iterator();
            while(it.hasNext()){
                Edge e=it.next();
                if(dist[e.a]!=99999 && dist[e.a] + e.cost < dist[e.b])
                    dist[e.b]=dist[e.a]+e.cost;
                }
            }
       for(int i=0;i<n;i++)
         System.out.println(dist[i]);
    }
    
 }
