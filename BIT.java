import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
class BIT
{
    static int B[],N;
    public static void main(String args[])throws IOException
    {
    BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
    }
    public int get(int i)
    {
        int res=0;
        while(i!=0)
        {
            res+=B[i];
            i-=(i&-i);
        }
        return res;
    }
    public void set(int i,int val)
    {
        while(i<=N)
        {
            B[i]+=val;
            i+=(i&-i);
        }
    }
}
