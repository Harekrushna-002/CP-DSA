import java.util.Scanner;

class SegmentTree{
    int seg[];
    public SegmentTree(int n){
        seg=new int[4*n];
    }
    public void build(int ind,int low,int high,int arr[]){
        if(low==high){
            seg[ind]=arr[low];
            return;
        }

        int mid=(low + high)/2;
        build(2*ind + 1,low,mid,arr);  //left call
        build(2*ind + 2,mid+1,high,arr); //right call

        seg[ind]=Math.min(seg[2*ind+1], seg[2*ind+2]);
    }

    public int query(int ind,int low,int high,int l,int r){
        //No overlap ->[low,high] (l,r) or (l,r) [low,high]
        if(high < l || r < low){
            return Integer.MAX_VALUE;
        }

        //Complete overlap -> [l low high r]
        if(low >=l && high<=r){
            return seg[ind];
        }

        //Partial overlap
        int mid=(low + high)/2;
        int left=query(2*ind + 1,low, mid,l,r);
        int right=query(2*ind + 2,mid+1,high,l,r);

        return Math.min(left, right);
    }

    public void update(int ind,int low,int high,int i,int val){
        if(low==high){
            seg[ind]=val;
            return;
        }

        int mid=(low + high)/2;
        if(i <=mid) update(2*ind + 1,low,mid,i,val);
        else update(2*ind + 2,mid+1,high,i,val);

        seg[ind]=Math.min(seg[2*ind+1], seg[2*ind+2]);
    }
}

public class SgTree {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        SegmentTree sg=new SegmentTree(n);

        sg.build(0,0,n-1,arr); //build segment Tree

        int q=sc.nextInt();
        while(q-->0){
            int type=sc.nextInt();
            if(type==1){
                int l=sc.nextInt();  // 0-based indexing
                int r=sc.nextInt();
                System.out.println(sg.query(0,0,n-1,l,r));  //range query
            }
            else{
                int i=sc.nextInt();
                int val=sc.nextInt();
                sg.update(0,0,n-1,i,val);  //point update
                arr[i]=val;
            }
        }
    }
}