// Complexity : O(E+VlogV)
import java.util.*;
// Node class
class Node implements Comparable<Node>
{
    int node, weight;
    Node(int node, int weight)
    {
        this.node = node; this.weight = weight;
    }
    
    public int compareTo(Node x)
    {
        return Integer.compare(this.weight, x.weight);
    }
}
public class djikstra 
{
    public static void main(String[] args)
    {
    Scanner sc = new Scanner(System.in);
        // n : number of nodes
        // m : number of edges
        int n = sc.nextInt(),m = sc.nextInt();
        ArrayList<ArrayList<Node>> adj = new ArrayList<ArrayList<Node>>(n+1);
        for(int i=0; i<n+1; i++)
            adj.add(new ArrayList<Node>(n+1));    
        while(m-- > 0)
        {
            // x : first node
            // y : second node
            // weight : weight of the edge joining x and y
            int x = sc.nextInt(), y = sc.nextInt(),weight = sc.nextInt();
            adj.get(x).add(new Node(y, weight));
            adj.get(y).add(new Node(x, weight));
        }
        int source = sc.nextInt(); // source : the starting vertex
        djikstra(source, adj, n);
    }    
    static void djikstra(int s, ArrayList<ArrayList<Node>> adj, int n)
    {
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(s, 0));
        while(pq.size() > 0)
        {
            Node current = pq.peek(); pq.remove();
            int currentNode = current.node;
            Iterator<Node> it = adj.get(currentNode).iterator();
            while(it.hasNext()){
                Node temp = it.next();
                if(distance[temp.node] > distance[currentNode] + temp.weight)
                {
                    pq.add(new Node(temp.node, distance[currentNode]+temp.weight));
                    distance[temp.node] = distance[currentNode] + temp.weight;
                }
            }
        }        
        for(int i=1; i<distance.length; i++)
        {
            if(i!=s)
            {
                System.out.print(((distance[i] == Integer.MAX_VALUE)?-1:distance[i]) + " ");
            }
        }
        System.out.println();
    } // end of djikstra's algorithm 
}
