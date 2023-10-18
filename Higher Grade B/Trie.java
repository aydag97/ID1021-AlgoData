import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Trie{
    Node root;

    private class Node{
        public Node[] next;
        public boolean valid;

        public Node(){
            next = new Node[27];
            valid = false;
        }
        // finds words that are valid and adds them to a list
        public void collect(String keySeq, String word, ArrayList<String> list, int index){
            // base case: if the word is valid then add it to the list,
            // otherwise return(no word found)
            if(index == keySeq.length()){
                if(valid){
                    list.add(word);
                    return;
                }
                else{
                    return;
                }
            }
            // keySeq = "33224" -> '3' -> key = int 3
            int key = Character.getNumericValue(keySeq.charAt(index));
            // get the index of key in the tree: if key is 3 then index is 2
            int indx = getIndexofKey(key);
            // iterate over all three branches for the given key
            for (int i = 0; i < 3; i++){
                String possiblePath = "";
                if(next[(indx*3)+i] != null){
                    //indx*3+i allows to iterate over all branches of a key
                    possiblePath = word + getChar((indx*3) + i);
                    next[(indx*3)+i].collect(keySeq, possiblePath,list, index+1);
                }
            }
        }
    }
    // constructor for the Trie class; reads a file and populates the tree
    public Trie(String file){
        root = new Node();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                add(line);
            }
            br.close();
        }catch(Exception e){
            System.out.println("File  not found!");
        }
    }
    // add words to the tree
    public void add(String word){
        if(root == null){
            root = new Node();
        }
        int i = 0;
        Node curr = root;
        //go through each char in the word
        while(i != word.length()){
            int index = getCode(word.charAt(i));
            // create a new branch if it's empty
            if(curr.next[index] == null){
                curr.next[index] = new Node();
            }
            curr = curr.next[index];
            i++;
        }
        // set the flag valid to true meaning a valid word
        curr.valid = true;
    }

    //takes a key sequence and finds the word in the tree using collect method
    public ArrayList<String> decode(String keySeq) {
        ArrayList<String> list = new ArrayList<>();
        String word = "";
        root.collect(keySeq, word, list, 0);
        return list;
    }
    // returns the int value of each char
    private static int getCode(char c){
        int ret = 0;
        if(c >= 'a' && c <= 'p') // a to p
            ret = (int) c - 'a';
        else if(c >= 'r' && c <= 'v') // r to v
            ret = (int) c - 'a' - 1;
        else if(c >= 'x' && c <= 'z') // x to z
            ret = (int) c - 'a' - 2;
        else if(c == 'å')
            ret = 24;
        else if(c == 'ä')
            ret = 25;
        else if(c == 'ö')
            ret = 26;
        
        return ret;
    }
    // returns the char of a value
    private static char getChar(int c){
        char ret = ' ';
        if(c >= 0 && c <= 15) // a to p
            ret = (char) (97 + c);
        else if(c >= 16 && c <= 20) // r to v
            ret = (char) (97 + c + 1);
        else if(c >= 21 && c <= 23) // x to z
            ret = (char) (97 + c + 2);
        else if(c == 24)
            ret = 'å';
        else if(c== 25)
            ret = 'ä';
        else if(c == 26)
            ret = 'ö';

        return ret;
    }
    // ex: key 3 on phone has the index 2 in the tree
    private static int getIndexofKey(int key){
        return key-1;
    }
    // finds the key associated to each char. each key is associated with 3 chars
    private static int getKeyofChar(char c){
        return (getCode(c)/3) + 1;
        // we want to divide chars into sets of 3. +1 ensures that we stay in range 1 to 9 (keys)
        // 1        2       3       4       5      6       7       8       9
        // abc      def     ghi     jkl     mno     prs     tuv     xyz     åäö
    }


    public static void main(String[] args) {
        Trie tree = new Trie("kelly.txt");
        System.out.print(Trie.getKeyofChar('s'));
        System.out.print(Trie.getKeyofChar('p'));
        System.out.print(Trie.getKeyofChar('ä'));
        System.out.print(Trie.getKeyofChar('n'));
        System.out.print(Trie.getKeyofChar('n'));
        System.out.print(Trie.getKeyofChar('a'));
        System.out.print(Trie.getKeyofChar('n'));
        System.out.print(Trie.getKeyofChar('d'));
        System.out.print(Trie.getKeyofChar('e'));
        System.out.println();
        
        String key = "669551522"; // spännande

        ArrayList<String> allWords = tree.decode(key);

        for (String x: allWords){
            System.out.print(x + "\t");
        }
    }
}