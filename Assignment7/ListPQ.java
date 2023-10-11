
public class ListPQ{
    Node head;
    Node tail;
    int size;
    
    private class Node{
        int data;
        Node next;
        
        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    public ListPQ(){
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    // adding a new item at the end. constant time O(1)
    public void add(int item){
        Node addedItem = new Node(item);
        // if list is empty
        if(head == null){
            head = addedItem;
            tail = addedItem;
        }
        else{
            tail.next = addedItem;
            tail = addedItem;
        }
        size++;
    }
    // removing an item with a time O(n). returning the smallest item
    public int remove(){
        // if list is empty
        if(head == null){
            System.out.println("The list is empty");
            return -1;
        }

        Node curr = head;
        Node prev = null;
        Node prevMin = null;
        Node afterMin = null;
        int retValue = curr.data;

        while(curr != null){
            if(curr.data < retValue){
                retValue = curr.data;
                prevMin = prev;
                afterMin = curr.next;
            }
            else{
                prev = curr;
                curr = curr.next;
            } 
        }
        size--;
        if(prevMin != null)
            prevMin.next = afterMin;
        else{
            // this is bc if we try to remove the head, i.e. prevMin == null
            head = head.next;
        }
        return retValue;
    }

    public void print(){
        Node curr = head;
        System.out.println("The list is:");
        while(curr != null){
            System.out.printf("%d\t", curr.data);
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        ListPQ q = new ListPQ();
        q.add(2);
        q.add(5);
        q.add(3);
        q.add(6);
        q.add(4);
        q.add(1);
        q.print();
        // remove 1
        q.remove();
        q.print();
        // remove 2
        q.remove();
        q.print();
        // remove 3
        q.remove();
        q.print();
        // remove 4
        q.remove();
        q.print();
        // remove 5
        q.remove();
        q.print();
        // remove 6
        q.remove();
        q.print();
    }
}