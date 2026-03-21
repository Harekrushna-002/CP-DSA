public class MergeSort {
    public static void merge(int st,int mid,int end,int arr[]){
        int left=st,right=mid+1;
        int temp[]=new int[end-st+1];
        int k=0;

        while(left <= mid && right <= end){
            if(arr[left] <= arr[right]){
                temp[k++]= arr[left++];
            }
            else{
                temp[k++]= arr[right++];
            }
        }

        while(left <= mid){
            temp[k++]= arr[left++];
        }

        while(right <= end){
            temp[k++]= arr[right++];
        }

        // copy the temp array to original array
        for(int i=0;i<temp.length;i++){
            arr[st+i]= temp[i];
        }
    }

    public static void mergeSort(int st,int end,int arr[]){
        if(st == end) return;

        int mid= st + (end - st)/2;

        mergeSort(st, mid, arr);      // left part
        mergeSort(mid+1, end, arr);  // right part
        merge(st, mid, end, arr);    // merge left & right part to sort the array
    }

    public static void main(String[] args) {
        int arr[]={5,7,2,9,1,6};
        int n=arr.length;

        mergeSort(0,n-1,arr);

        System.out.println("The sorted array is :");
        for(int val : arr){
            System.out.print(val +" ");
        }
    }
}