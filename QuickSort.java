import java.util.Scanner;

public class Sort {
    public static int partition(int arr[],int st,int end){
        int pivot=arr[end];
        int i=st-1;
        for(int j=st;j<end;j++){
            if(arr[j] <= pivot){
                i++;
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        i++;
        int temp=arr[i];
        arr[i]=pivot;
        arr[end]=temp;

        return i;
    }
  
    public static void quicksort(int arr[],int st,int end){
        if(st < end){
            int pvtIdx=partition(arr,st,end);
            quicksort(arr,st,pvtIdx-1);
            quicksort(arr,pvtIdx+1,end);
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
        quicksort(arr,st,end);
        System.out.println("The sorted Array is :");
        for(int i=0;i<n;i++){
            System.out.print(arr[i] +" ");
        }
    }
}
