
public class LinkedList{
    private Node first;
    private int listSize;
    
    

    // constructor
    public LinkedList(){
        this.first = null;
        this.listSize = 0;
    }

    public void add(int item){
        // creating a new node of item
        Node newItem = new Node(item);
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
        while(this.first != null && this.first.data == item){
            this.first = this.first.next;
        }
        Node currNode = this.first;

        while(currNode != null && currNode.next != null){
            if(currNode.next.data == item){
                currNode.next = currNode.next.next;
                this.listSize--;
            }
            else{
                currNode = currNode.next;
            }
        }
    }

    // appends a linked list to the end of another one
    public void append(LinkedList b){
        Node currNode = this.first;
        
        while(currNode.next != null){
            currNode = currNode.next;
        }
        currNode.next = b.first;
        b.first = null;
        this.listSize += b.listSize;
    }

    public void unlink(Node node){
        Node curr = this.first;
        Node prev = null;     
        
        while(curr.next != null ){
            if(curr == node){
                // if the node is not the head
                if(prev != null){
                    prev.next = curr.next;
                }
                else{
                    this.first = curr.next;
                }
                node.next = null;
                this.listSize--;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void insert(Node node){
        // if list is empty
        if(this.first == null){
            this.first = node;
        }
        else{
            node.next = this.first;
            this.first = node;
        }
        this.listSize++;
        return;
    }

    public static LinkedList createSList(int n){
        LinkedList myList = new LinkedList();
        for(int i = n; i >= 0; i--){
            myList.add(i);
        }
        return myList;
    }
    public static void printList(LinkedList list){
        Node curr = list.first;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args){
        LinkedList list = createSList(10);
        printList(list);
        list.remove(4);
        list.remove(6);
        printList(list);
        boolean found = list.find(4);
        boolean f1 = list.find(3);
        System.out.println(found);
        System.out.println(f1);
        list.add(11);
        printList(list);

        Node node1 = new Node(50);
        list.insert(node1);
        printList(list);
        

        Node node2 = new Node(39);
        list.insert(node2);
        printList(list);
        
        list.unlink(node1);
        printList(list);
       

        list.unlink(node2);
        printList(list);
        

    }
    
}