public class QuickSort {
    public static void swap(int x,int y,int arr[]){
        int temp=arr[x];
        arr[x]=arr[y];
        arr[y]=temp;
    }

    public static int partition(int st,int end,int arr[]){
        int pivot= arr[end];  // pivot element
        int idx = st-1;

        for(int i=st;i<end;i++){
            if(arr[i] <= pivot){
                idx++;
                swap(idx,i,arr);
            }
        }

        idx++;
        swap(idx,end,arr);

        return idx;
    }

    public static void quickSort(int st,int end,int arr[]){
        if(st >= end) return;

        int piIdx= partition(st, end, arr);  // find the exact pos of pivot element (last element treated as pivot element)
        quickSort(st, piIdx-1, arr);        // left part
        quickSort(piIdx+1, end, arr);      // right part
    }

    public static void main(String[] args) {
        int arr[]={2,5,0,7,1,9,4};
        int n=arr.length;

        quickSort(0,n-1,arr);

        System.out.println("The sorted array is :");
        for(int val : arr){
            System.out.print(val +" ");
        }
    }
}