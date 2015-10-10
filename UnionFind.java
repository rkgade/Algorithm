   
    /*
   Author : Aditya Chatterjee
   License : CC0 1.0 Universal
   Union Find using union by rank + path compression
   Complexity : m*(Inverse Ackermann of n) over m union and find operations where n is the number of nodes
   */
   
import java.util.Scanner;
public class UnionFind
{
    static int parent[],rank[],count;
    public UnionFind(int n)
    {
        count=n;
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i]=i;
            rank[i]=0;
        }
    }
    public static int find(int a)
    {
        while(parent[a]!=a)
        {
            parent[a]=parent[parent[a]];
            a=parent[a];
        }
        return a;
    }
    public static boolean connected(int a,int b)
    {
        return find(a)==find(b);
    }
    public static void union(int a,int b)
    {
        int rootA=find(a),rootB=find(b);
        if(rank[rootA]<rank[rootB]) parent[rootA]=rootB;
        else if(rank[rootA]>rank[rootB]) parent[rootB]=rootA;
        else
        {
            parent[rootB]=rootA;
            ++rank[rootA];
        }
        --count;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(); // number of nodes
        UnionFind uni=new UnionFind(n);
        int m=sc.nextInt(); // number of edges
        for(int i=0;i<m;i++)
        {
            int a=sc.nextInt(); // first node
            int b=sc.nextInt(); // second node
            if(uni.connected(a,b)) continue;
            uni.union(a,b);
        }
        System.out.println(count+" components");
    }
}
        
