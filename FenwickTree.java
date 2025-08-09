import java.util.Scanner;

public class FenwickTree {
    public static void update(int ind,int val,int BIT[]){
        while(ind <= BIT.length){
            BIT[ind] +=val;
            ind +=(ind & -ind);   // exponetially increasing interval
        }
    }

    public static int query(int ind,int BIT[]){
        int ans=0;
        while(ind > 0){
            ans +=BIT[ind];
            ind -=(ind & -ind);   // remove the last set bit
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        int arr[]=new int[n+1];   // Fenwick Tree always start with 1 based indexing
        int BIT[]=new int[n+1];

        for(int i=1;i<=n;i++){
            arr[i]=sc.nextInt();
            update(i,arr[i],BIT);  // Build the Fenwick Tree
        }

        int q=sc.nextInt();
        while(q-->0){
            int type=sc.nextInt();

            if(type==1){           // Range query [l,r]
                int l=sc.nextInt();
                int r=sc.nextInt();

                int ans=query(r,BIT) - query(l-1,BIT);
                System.out.println(ans);
            }
            else{                   // point update
                int i=sc.nextInt();
                int val=sc.nextInt();
                update(i,-arr[i],BIT);   // First remove the value of arr[i] where it is contributes on BIT array
                arr[i]=val;
                update(i,val,BIT);      // Then update the val on BIT arrray
            }
        }
    }
}