public class rcycles
{ public static void main()
   { int holder[]=new int[999];
     int count=0,count1=0;
     for(int i=2;i<=1000;i++)
     { count1=0;int j=0;holder[0]=1;
         inner:
         while(holder[j]!=0)
         { holder[j+1]=(holder[j]*(int)(Math.pow(10,c(i))))%i;
           j++;
           for(int k=0;k<=j;k++)
             if(holder[k]==holder[j])
              { count1=j-k;
                break inner;
              }
            }
            if(count<count1)
             count=count1;
            }
            System.out.println(count);
        }
        public static int c(int x)
        {int y=0;
            while(x>0)
            {y++;x=x/10;
            }
        return y;
    }
    }
            
            
         
