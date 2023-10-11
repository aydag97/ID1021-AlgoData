import java.util.Random;

public class QuickSortLL{
    Node head;
    Node tail;

    public class Node{
        int value;
        Node next;
        private Node(int value){
            this.value = value;
            next =null;
        }
    }
    public QuickSortLL(){
        head = null;
        tail = null;
    }

    // add a node at the end of the list
    public void add(int element){
        Node addedNode = new Node(element);
        if(head == null){
            head = addedNode;
            tail = head;
        }
        else{
            tail.next = addedNode;
            tail = tail.next;
        }
    }
    // insert a node at the end of the list
    public void insert(Node node){
        // if list is empty
        if(head == null){
            head = node;
            tail = head;
        }
        else{
            tail.next = node;
            tail = node;
        }
        return;
    }
    
    // append a list at the end of another
    public void append(QuickSortLL b) {
        if (b.head != null) {
            if (head == null) {
                head = b.head;
                tail = b.tail;
            } else {
                tail.next = b.head;
                tail = b.tail;
            }
            b.head = null;
            b.tail = null;
        }
    }

    public static void sortLL(QuickSortLL list){
        if(list.head == list.tail || list.head == null)
            return;
        
        QuickSortLL small = new QuickSortLL();
        QuickSortLL great = new QuickSortLL();

        Node mid = partition(list.head, list.tail, small, great);

        QuickSortLL.sortLL(small);
        QuickSortLL.sortLL(great);

        list.head = null;
        list.tail = null;
        list.append(small);
        list.insert(mid);
        list.append(great);

    }

    public static Node partition(Node first, Node last, QuickSortLL smaller, QuickSortLL greater) {

        Node pivot = first;
        Node current = first.next;
        // Ensure the pivot is disconnected from the rest of the list
        pivot.next = null;
        while (current != null) {
            Node nextNode = current.next;
            current.next = null;  // Disconnect the current node
            if (current.value <= pivot.value) {
                smaller.insert(current);
            } else {
                greater.insert(current);
            }
            current = nextNode;
        }
        return pivot;
    }

    private static QuickSortLL copyList(QuickSortLL list){
        QuickSortLL copy = new QuickSortLL();
        QuickSortLL.Node curr = list.head;
        while(curr != null){
            copy.add(curr.value);
            curr = curr.next;
        }
        return copy;
    }

    public static void main(String[] args){
        Random rnd = new Random();
        int n  = 100;
        int size = 10;
        QuickSortLL l = new QuickSortLL();
        for(int i = 0; i < size; i++){
            l.add(rnd.nextInt(n));

        }
        Node curr = l.head;
        System.out.println("the original unsorted list:");

        for(int i = 0; i < size; i++){
            System.out.printf("%d\t", curr.value);
            curr = curr.next;
        }
        System.out.println();

         QuickSortLL copy = copyList(l);

         Node c1 = copy.head;
         System.out.println("the copy of the unsorted list:");

         for(int i = 0; i < size; i++){
            System.out.printf("%d\t", c1.value);
            c1 = c1.next;
        }

        System.out.println();
        System.out.println("Sorted copy list:");
        System.out.println();
        sortLL(copy);
        Node c = copy.head;
        for(int i = 0; i < size; i++){
            System.out.printf("%d\t", c.value);
            c = c.next;
        }
    }
}