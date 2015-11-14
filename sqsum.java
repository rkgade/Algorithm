import java.util.Scanner;
public class sqsum {
	private static final int[] SQ_DIG = {0, 1, 4, 9, 16, 25, 36, 49, 64, 81};
	private static long[] ways = {1};
	private static long[] sums = {0};
    public static int upperlimit;
    public static long answer[];
	public static void getAnswer() {
		solve(0);
		long sum = 0;
		for (int i = 1; i * i < sums.length; i++)
			sum += sums[i * i];
        answer[upperlimit]=sum % 1000000000;
		
	}
	private static void solve(int digits) {
		if (digits == upperlimit) return;
		long[] nextWays = (digits > 8) ? null : new long[ways.length + 81];
		long[] nextSums = new long[sums.length + 81];
		for (int i = 0; i < sums.length; i++)
			if (digits > 8)
				for (int j = 0; j < 10; j++)
					nextSums[i + SQ_DIG[j]] += sums[i];
			else if (ways[i] != 0)
				for (int j = 0; j < 10; j++) {
					nextWays[i + SQ_DIG[j]] += ways[i];
					nextSums[i + SQ_DIG[j]] += sums[i] + ways[i] * j * (long)Math.pow(10, digits);
				}
		for (int i = 0; i < nextSums.length; i++)
			nextSums[i] %= 1000000000;
        long sum = 0;
		for (int i = 1; i * i < sums.length; i++)
			sum += sums[i * i];
        answer[digits]=sum % 1000000000;
		ways = nextWays;
		sums = nextSums;
		solve(digits + 1);
	}
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt(),max=0;
        int data[]=new int[t]; 
        for(int i=0;i<t;i++){
            data[i]=sc.nextInt();
        if(max<data[i]) max=data[i];}
        upperlimit=max;answer=new long[max+1];
        getAnswer();
        for(int i=0;i<t;i++)
            System.out.println(answer[data[i]]);
    }
}
