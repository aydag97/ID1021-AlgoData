public class ListPQReversed{
    Node head;
    int size;

    private class Node{
        int data;
        Node next;
        
        public Node(int data){
            this.data = data;
            next = null;
        }
    }

    public ListPQReversed(){
        head = null;
        size = 0;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    // adding a new item in its right place. smaller -> high priority O(n)
    public void add(int item){
        Node addedItem = new Node(item);
        // if list is empty
        if(head == null){
            head = addedItem;
            size++;
            return;
        }
        if(item < head.data){
            addedItem.next = head;
            head = addedItem;
        }
        else{
            Node curr = head.next;
            Node prev = head;
            while(curr != null && curr.data < item){
                prev = curr;
                curr = curr.next;
            }
            addedItem.next = curr;
            prev.next = addedItem;
        }
        size++;
    }
    // remove the first element since the list is sorted (first is smallest)
    public int remove(){
        // if list is empty
        if(head == null){
            System.out.println("The list is empty");
            return -1;
        }
        if(size == 1){
            int retData = head.data;
            head = null;
            size--;
            return retData; 
        }
        else{
            int retValue = head.data;
            head = head.next;
            size--;
            return retValue;
        }
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
        ListPQReversed q = new ListPQReversed();
        q.add(2);
        q.add(5);
        q.add(3);
        q.add(6);
        q.add(4);
        q.add(1);
        q.print();
        q.remove();
        q.print();
        q.remove();
        q.print();
        q.remove();
        q.print();
        q.remove();
        q.print();
        q.remove();
        q.print();
        q.add(4);
        q.add(20);
        q.add(18);
        q.add(1);
        q.print();

    }
}
