
public class LinkedList{
    private Node first;
    private int listSize;
    
    private class Node{
        private int data;
        private Node next;

        Node(int value, Node nxt){
            this.data = value;
            this.next = nxt;
        }
    }

    // constructor
    public LinkedList(){
        this.first = null;
        this.listSize = 0;
    }

    public void add(int item){
        // creating a new node of item
        Node newItem = new Node(item, first);
        // setting the first reference to the new item
        this.first = newItem;
        //updating size of the list
        this.listSize++;
    }

    public int length(){
        return this.listSize;
    }

    public boolean find(int item){
        // create a node that can traverse the list
        Node nxt = this.first;
        // while list is not empty
        while(nxt != null){
            if(nxt.data == item){
                return true;
            }
            else{
                //move to the next node
                nxt = nxt.next;
            }
        }
        return false;
    }

    public void remove(int item){
        // if the item is the first node
        while(this.first != null && this.first.data == item){
            this.first = this.first.next;
        }
        Node currNode = this.first;
        // while we have not reached the end
        while(currNode != null && currNode.next != null){
            // if the next node is the target
            if(currNode.next.data == item){
                // prev points to the next node
                currNode.next = currNode.next.next;
                this.listSize--;
            }
            else{
                //keep looking
                currNode = currNode.next;
            }
        }
    }

    // appends a linked list to the end of another one
    public void append(LinkedList b){
        Node currNode = this.first;
        // loop until currNode holds the last node
        while(currNode.next != null){
            currNode = currNode.next;
        }
        // currNode points to the second list
        currNode = b.first;
        b.first = null;
        this.listSize += b.listSize;
    }

    // generate a linked list of size n
    public static LinkedList generateList(int n){
        LinkedList myList = new LinkedList();
        for(int i = 0; i < n; i++){
            myList.add(i);
        }
        return myList;
    }
    // benchmark
    public static void main(String[] args){

        int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800,25600,51200,102400};
        System.out.printf("# Benchmark: appending a linked list at the end of another one\n");
        System.out.printf("#%7s\t%8s\t%8s\t%8s\t%8s\n", "n", "G to F","total GF", "F to G", "total FG");

        for(int n : sizes){
            System.out.printf("%8d\t", n);
            int k = 1000;
            double min = Double.POSITIVE_INFINITY;

            double min1 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long start = System.nanoTime();
                
                LinkedList fixed = generateList(100);
                LinkedList growing= generateList(n);
                long startTime = System.nanoTime();
                fixed.append(growing);
                long endTime = System.nanoTime();
                double total1 = (endTime - start);
                double total2 = (endTime - startTime);
                if (total2 < min)
                    min = total2;

                if (total1 < min1)
                    min1 = total1;
            }
            System.out.printf("%8.2f\t%8.2f\t", min/1000, min1/1000);


            min = Double.POSITIVE_INFINITY;
            min1 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long start = System.nanoTime();
                
                LinkedList fixed = generateList(1000);
                LinkedList growing= generateList(n);
                long startTime = System.nanoTime();
                growing.append(fixed);
                long endTime = System.nanoTime();
                double total1 = (endTime - start);
                double total2 = (endTime - startTime);
                if (total2 < min)
                    min = total2;
                
                if (total1 < min1)
                    min1 = total1;
            }
            System.out.printf("%8.2f\t%8.2f\n", min/1000, min1/1000);
        }

    }
}