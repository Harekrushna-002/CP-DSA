import java.util.Scanner;

class LazySegTree{
    int seg[];
    int lazy[];
    public LazySegTree(int n){
        seg=new int[4*n];
        lazy=new int[4*n];
    }

    public void build(int ind,int low,int high,int arr[]){
        if(low==high){
            seg[ind]=arr[low];
            return;
        }
        int mid=(low + high)/2;
        build(2*ind+1,low,mid,arr);
        build(2*ind+2,mid+1,high,arr);

        seg[ind]=seg[2*ind+1] + seg[2*ind+2];
    }

    public void update(int ind,int low,int high,int l,int r,int val){
        //update previous remaining updates & propagate down
        if(lazy[ind] !=0){
            seg[ind] +=(high-low+1) * lazy[ind];

            if(low !=high){
                lazy[2*ind+1] +=lazy[ind];
                lazy[2*ind+2] +=lazy[ind];
            }
            lazy[ind]=0;
        }

        //No overlap
        if(r < low || high < l){
            return;
        }

        //completely overlap
        if(low >=l && high <=r){
            seg[ind] +=(high-low+1) * val;  //update the node

            //Lazy propagate downwards and return
            if(low !=high){
                lazy[2*ind+1] +=val;
                lazy[2*ind+2] +=val;
            }
            return;
        }

        //Partial overlap
        int mid=(low + high)/2;
        update(2*ind+1,low,mid,l,r,val);
        update(2*ind+2,mid+1,high,l,r,val);

        seg[ind]= seg[2*ind+1] + seg[2*ind+2];
    }

    public int query(int ind,int low,int high,int l,int r){
        if(lazy[ind] !=0){
            seg[ind] +=(high-low+1) * lazy[ind];

            if(low !=high){
                lazy[2*ind+1] +=lazy[ind];
                lazy[2*ind+2] +=lazy[ind];
            }
            lazy[ind]=0;
        }

        if(r < low || high < l){
            return 0;
        }

        if(low >=l && high<=r){
            return seg[ind];
        }

        int mid=(low + high)/2;
        int left=query(2*ind+1,low,mid,l,r);
        int right=query(2*ind+2,mid+1,high,l,r);

        return left + right;
    }
}

public class LazyPropagation {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        LazySegTree sg=new LazySegTree(n);

        sg.build(0,0,n-1,arr);

        int q=sc.nextInt();
        while(q-->0){
            int l=sc.nextInt();
            int r=sc.nextInt();
            int val=sc.nextInt();

            sg.update(0,0,n-1,l,r,val);

            System.out.println(sg.query(0,0,n-1,l,r));
        }
    }
}