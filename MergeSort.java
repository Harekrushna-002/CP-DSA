import java.util.Scanner;
public class Sort {
    public static void mergefxn(int arr[],int st,int mid,int end){
        int i=st,j=mid+1;
        int temp[]=new int[end-st+1];
        int k=0;
        while(i <=mid && j<=end){
            if(arr[i] <= arr[j]){
                temp[k++]=arr[i];
                i++;
            }
            else{
                temp[k++]=arr[j];
                j++;
            }
        }
        while(i<=mid){
            temp[k++]=arr[i];
            i++;
        }
        while(j<=end){
            temp[k++]=arr[j];
            j++;
        }
        for(int idx=0;idx<temp.length;idx++){
            arr[idx+st]=temp[idx];
        }
    }
    public static void mergesort(int arr[],int st,int end){
            if(st < end){
                int mid=st+(end-st)/2;
                mergesort(arr,st,mid);
                mergesort(arr,mid+1,end);
                mergefxn(arr,st,mid,end);
            }
        }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        int st=0,end=n-1;
        mergesort(arr,st,end);
        System.out.println("The sorted array is :");
        for(int i=0;i<n;i++){
            System.out.print(arr[i] +" ");
        }
    }
}
