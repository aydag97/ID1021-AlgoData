
public class QueueLL<T>{
    Node head;
    Node last;
    int size;

    private class Node{
        T item;
        Node next;

        private Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    public QueueLL(){
        head = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty(){
        return(head == null);
    }

    // add new items at the last position
    public void enqueue(T element){
        Node addedItem = new Node(element, null);
        // if the queue is empty
        if(head == null)
            // set head and last to the first element
            head = addedItem;
        else
            // if queue is not empty then next pointer of the last item 
            // should point to the new element
            last.next = addedItem;
        // set the new added item as the last element
        last = addedItem;
        size++;
    }


    // remove element from the start of the queue
    public T dequeue(){
        // if queue is empty
        if(head == null){
            System.out.println("The queue is empty");
            return null;
        }
        T removedItem = head.item;
        // if queue has 1 element
        if(size == 1){
            // set the head and last to null
            head = null;
            last = null;
            size--;
        }
        // if queue has more than 2 elements
        else if(size >= 2){
            // set the head to the next element
            head = head.next;
            size--;
        }
        return removedItem;
    }
    
    public void print() {
        if (this.head == null) {
            System.out.println("print(): queue is empty");
            return;
        }
        Node current = this.head;
        System.out.println("start of queue");
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        QueueLL<Integer> queue = new QueueLL<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(4);
        queue.enqueue(6);
        System.out.println(queue.dequeue());
        queue.print();
    }
}