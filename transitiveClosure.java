import java.util.*;
/*
 * Aditya Chatterjee
 *  License : CC0 1.0 Universal
 * Transitive closure is a matrix of 1 and 0 where 1 denotes that there is a path between vertices i and j
 * Comlexity : O(n^3)
 * Faster than floyd warshall as bit operations are used but complexity remains same
 */
public class Warshall_TansitiveClosure {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long[][] ar = new long[n+1][n+1];
        boolean res[][]=new boolean[n][n];
        for(int i=0; i<n+1; i++){
            for(int j=0; j<n+1; j++){
                if(i!=j)
                    ar[i][j] = Integer.MAX_VALUE;
            }
        }
        
        while(m-- > 0){
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            ar[a][b] = c;if(ar[a][b]!=0) res[a][b]=true;
        }
        
        for(int k=1; k<n+1; k++){
            for(int i = 1; i<n+1; i++){
                for(int j = 1; j< n+1; j++){
                    res[i][j] = res[i][j] || ( res[i][k] && res[k][j] );
                }
            }
        }
        for(int i=0;i<n;i++){
           for(int j=0;j<n;j++)
              System.out.print(res[i][j]+" ");
              System.out.println();}
        }
    }
