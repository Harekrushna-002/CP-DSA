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
        
        int n1=sc.nextInt();
        int arr1[]=new int[n1];
        for(int i=0;i<n1;i++){
            arr1[i]=sc.nextInt();
        }

        int n2=sc.nextInt();
        int arr2[]=new int[n2];
        for(int i=0;i<n2;i++){
            arr2[i]=sc.nextInt();
        }

        SegmentTree sg1=new SegmentTree(n1);
        SegmentTree sg2=new SegmentTree(n2);

        sg1.build(0,0,n1-1,arr1); //build segment Tree
        sg2.build(0,0,n2-1,arr2);

        int q=sc.nextInt();
        while(q-->0){
            int type=sc.nextInt();
            if(type==1){
                int l1=sc.nextInt();  // 0-based indexing
                int r1=sc.nextInt();
                int l2=sc.nextInt();
                int r2=sc.nextInt();

                int x=sg1.query(0,0,n1-1,l1,r1);  //range query
                int y=sg2.query(0,0,n2-1,l2,r2);

                System.out.println(Math.min(x,y));
            }
            else{
                int array=sc.nextInt();
                if(array==1){
                    int i=sc.nextInt();
                    int val=sc.nextInt();
                    sg1.update(0,0,n1-1,i,val);  //point update
                    arr1[i]=val;
                }
                else{
                    int i=sc.nextInt();
                    int val=sc.nextInt();
                    sg2.update(0,0,n2-1,i,val);  //point update
                    arr2[i]=val;
                }
            }
        }
    }
}