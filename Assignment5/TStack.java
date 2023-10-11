
// a generic stack for any type of structures
public class TStack<T>{
    Node first;
    int size;
    private class Node{
        T value;
        Node next;

        private Node(T value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    public TStack(){
        first = null;
        size = 0;
    }

    public void push(T node){
        Node item = new Node(node, first);
        first = item;
        size++;
    }

    public T pop(){
        
        if(first == null)
            return null;
        T popped = first.value;
        first = first.next;
        size--;

        return popped;
    }
}