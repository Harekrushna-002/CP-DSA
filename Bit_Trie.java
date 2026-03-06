class Node{
    Node child[];

    public Node(){
        child=new Node[2];  //each position is either 0 or 1

        child[0]=null;
        child[1]=null;
    }
}

public class Bit_Trie {
    static Node root=new Node();

    public static void insert(int n){
        Node curr=root;

        for(int i=31;i>=0;i--){
            int bit= (n & (1 << i)) == 0 ? 0 : 1;

            if(curr.child[bit] == null){
                curr.child[bit]= new Node();
                
            }

            curr= curr.child[bit];
        }
    }

    public static int maxXor(int x){
        Node curr=root;

        int ans=0;
        for(int i=31;i>=0;i--){
            int bit= (x & (1 << i)) == 0 ? 0 : 1;
            
            if(curr.child[1^bit] != null){
                ans |= (1 << i);
                curr= curr.child[1^bit];
            }
            else{
                curr= curr.child[bit];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Max - xor of arr with x
        int arr[]={9,8,7,5,4};
        int x=8;
        
        for(int val : arr){
            insert(val);   // insert into Trie
        }

        int ans= maxXor(x);  // find max - xor

        System.out.println("Max - Xor is : " + ans);
    }
}