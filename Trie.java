public class Trie {
    public static class Node{
        Node children[];
        boolean eow;

        public Node(){
            children=new Node[26];
            
            for(int i=0;i<26;i++){
                children[i]=null;
                eow=false;
            }
        }
    }

    static Node root=new Node();    // object of the class Node

    public static void insert(String word){
        Node curr=root;

        for(int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            int idx=ch - 'a';
            if(curr.children[idx] == null){
                curr.children[idx]=new Node();
            }
            if(i == word.length()-1){
                curr.children[idx].eow=true;
            }
            curr=curr.children[idx];
        }
    }

    public static boolean search(String key){
        Node curr=root;

        for(int i=0;i<key.length();i++){
            char ch=key.charAt(i);
            int idx=ch - 'a';
            if(curr.children[idx] == null){
                return false;
            }
            if(i == key.length()-1 && curr.children[idx].eow == false){
                return false;
            }
            curr=curr.children[idx];
        }
        return true;
    }

    public static void main(String[] args) {
        String words[]={"there","their","apple","any","eat"};

        for(String word : words){
            insert(word);         // Insert the words to the Trie
        }

        String key="apple";
        System.out.println(search(key));   // search word in the Trie
    }
}