import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

// using the same BinaryTree as the previous assignment but using a queue for a breadth-first traversal
public class BinaryTree implements Iterable<Integer>{

    public Node root;

    public class Node{
        public Integer key;
        public Integer value;
        public Node left, right;

        // Node constructor
        public Node(Integer key, Integer value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
        // finding the height of tree
        public int maxH(){
            int ml = 0;
            int mr = 0;
            if(left != null)
                ml = left.maxH();
            if(right != null)
                mr = right.maxH();
            return(Math.max(ml,mr)+1);
        }
        // utilises recursion
        private void add(Integer key, Integer value){
        // if the key is already present, update the value
            if(this.key.equals(key)){
                this.value = value;
                return;
            }
            // if the new key is less than root?!
            if(this.key > key){
                // if the left branch of root is not empty
                if(this.left != null){
                    // repeat until left node is null
                    this.left.add(key, value);
                }
                    //if left is null then add the new node to it
                else{
                    this.left = new Node(key,value);
                }
            }
            else{
                if(this.right != null){
                    this.right.add(key,value);
                }
                else{
                    this.right = new Node(key,value);
                }
            }
        }
        // utilises recursion
        private Integer lookup(Integer key){
            // if the item is the root
            if(this.key == key){
                return value;
            }
            // if the item is less than the root
            else if(this.key > key){
                if(left != null){
                    // search for the key in the left side of the tree
                    return left.lookup(key);
                }
                // didn't find there
                else{
                    return null;
                }
            }
            // if the key is larger than root
            else{
                if(right != null){
                    return right.lookup(key);
                }
                else{
                    return null;
                }
            }
        }
        // printing the tree
        public void print() {
            if (left != null)
                left.print();
            System.out.println(" key: " + key + "\tvalue: " + value);
            if (right != null)
                right.print();
        }
    }
    // BinaryTree constructor
    public BinaryTree(){
        root = null;
    }
    // generate a tree with random values
    public BinaryTree(int n){
        Random rnd = new Random();

        root = new Node(n/2,n/2);
        for(int i = 0; i < n; i++){
            int kv = rnd.nextInt(n);
            root.add(kv,kv);
        }
    }
    // calls the private add()
    public void add(Integer key, Integer value){
         // if the tree is empty
        if(root == null){
            root = new Node(key,value);
        }
        else{
            root.add(key,value);
        }
    }
    // calls the private lookup()
    public Integer lookup(Integer key){
        if(root != null){
            return root.lookup(key);
        }
        else{
            return null;
        }
    }
    // helper method for printing a tree
    public void print() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        root.print();
    }
    // finding the height of tree
    public int max(){
        if(root!=null)
            return root.maxH();
        else
            return 0;
    }

    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }

    public class TreeIterator implements Iterator<Integer>{

        private Node next;
        private QueueLL<BinaryTree.Node> queue;
    
        public TreeIterator(){ 
            queue = new QueueLL<BinaryTree.Node>();
            next = root;
            if(next == null){
                return;
            }
        }


        @Override
        public boolean hasNext(){
            return (next != null);
        }
    

        @Override
        public Integer next(){
            if(!hasNext())
                throw new NoSuchElementException();

            Node ret = next;
            if(next.left != null){
                queue.enqueue(next.left);
            }
            if(next.right != null)
                queue.enqueue(next.right);
            
            next = queue.dequeue();
            return ret.key; 
        }
    

        @Override
        public void remove() {
          throw new UnsupportedOperationException();
        } 
    }

    // helper function for benchmark
    public static double benchLookUp(int treeSize, int iter, int keys){
        double min = Double.POSITIVE_INFINITY;
        // generate a tree with random values
        BinaryTree tree = new BinaryTree(treeSize);
        // finding the height the tree
        int maxl = tree.max();
        System.out.printf("%d", maxl);
        Random rnd = new Random();
        
        for(int i = 0; i < iter; i++){
            long start = System.nanoTime();
            for(int j = 0; j < keys; j++){
                tree.lookup(rnd.nextInt(treeSize));
            }
            long end = System.nanoTime();
            double total = end-start;

            if(total < min)
                min = total;
        }
        return min;
    }

    public static void main(String[] args){
        // [6,3,7,9,2,1,5,4,8]
        /*BinaryTree n = new BinaryTree();
        n.add(6,6);
        n.add(3,3);
        n.add(7,7);
        n.add(9,9);
        n.add(2,2);
        n.add(1,1);
        n.add(5,5);
        n.add(4,4);
    
        n.print();*/
        /*int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000,256000,512000,1024000};
        
        int iter = 100;
        int k = 100;

        System.out.printf("# Benchmark of lookup a key in tree in micros\n");
        System.out.printf("#%7s\t%3s\t%10s\t%8s\n", "n", "height","lookup", "log(n)" );

        for(int n : sizes){
            System.out.printf("%8d\t", n);
            double time = benchLookUp(n,iter,k);
            //int maxl = tree.max();

            System.out.printf("\t%8.0f\t%8.0f\n",time/1000, Math.log(n));
        }*/
        BinaryTree tree = new BinaryTree();
            tree.add(5,105);
            tree.add(2,102);
            tree.add(7,107);
            tree.add(1,101);
            tree.add(8,108);
            tree.add(6,106);
            tree.add(3,103);
            for (int i : tree)
                System.out.println("next value " + i);
    }
}