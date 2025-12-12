import java.util.*;

public class FactorialOfLargeNumber  {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter a number : ");
        int n=sc.nextInt(); // 1 <= n <=1000

        List<Integer> list=new ArrayList<>();
        list.add(1);

        for(int i=2;i<=n;i++){
            int carry=0;
            for(int j=0;j<list.size();j++){
                int val=i * list.get(j) + carry;
                list.set(j,val % 10);
                carry = val / 10;
            }

            while(carry > 0){
                list.add(carry % 10);
                carry /=10;
            }
        }

        // Reverse list
        for(int i=0,j=list.size()-1;i<j;i++,j--){
            int temp=list.get(i);
            list.set(i,list.get(j));
            list.set(j,temp);
        }

        System.out.println(list);
    }
}