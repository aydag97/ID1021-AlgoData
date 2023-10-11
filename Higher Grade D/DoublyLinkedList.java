

public class DoublyLinkedList {
    private DoublyNode head;
    private int size;

    public DoublyLinkedList(){
        this.head = null;
        this.size = 0;
    }
    
    // adding a new item as the first node in the list
    public void add(int item){
        DoublyNode node = new DoublyNode(item);

        if(this.head == null){
            this.head = node;
        }
        else{
            node.next = this.head;
            this.head.previous = node;
            this.head = node;

        }
        this.size++;
    }

    public int length(){
        return this.size;
    }

    public boolean find(int item){
        DoublyNode node = this.head;
        while(node != null){
            if(node.data == item){
                return true;
            }
            else{
                node = node.next;
            }
        }
        return false;
    }

    /*public void remove(int item){
        DoublyNode curr = this.head;
        DoublyNode prev = null;

        while(curr.next != null){
            if(curr.data == item){
                if(prev != null){
                    prev.next = curr.next;
                    curr.next.previous = prev;
                }
                else{
                    this.head = curr.next;
                }
                this.size--;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }*/

    public void remove(int item){
        while(this.head != null && this.head.data == item){
            this.head = this.head.next;
        }
        DoublyNode currNode = this.head;

        while(currNode != null && currNode.next != null){
            if(currNode.data == item){
                currNode.previous.next = currNode.next;
                currNode.next.previous = currNode.previous;
                this.size--;
            }
            else{
                currNode = currNode.next;
            }
        }
    }

    public void unlink(DoublyNode node){
        // if the list is empty
        if(this.head == null){
            return;
        }
        // if the node is the head
        if(node == this.head){
            this.head = node.next;
            node.next = null;
            node.previous = null;
        }
        // if the node is not the head
        if(node.previous != null){
            node.previous.next = node.next;
            node.previous = null;
        }
        // if the node is not the last node
        if(node.next != null){
            node.next.previous = node.previous;
            node.next = null;
        }
        this.size--;
        return;
    }

    public void insert(DoublyNode node){
        // if list is empty
        if(this.head == null){
            this.head = node;
        }
        else{
            node.next = this.head;
            this.head.previous = node;
            this.head = node;
        }
        this.size++;
        return;
    }



    public static DoublyLinkedList generateList(int n){
        DoublyLinkedList myList = new DoublyLinkedList();
        for(int i = 0; i < n; i++){
            myList.add(i);
        }
        return myList;
    }

    public static void printList(DoublyLinkedList list){
        DoublyNode curr = list.head;
        while(curr != null){
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
    public static void main(String[] args){
        DoublyLinkedList fixed = generateList(10);
        printList(fixed);
        System.out.printf("\n%d\n", fixed.size);
        fixed.remove(4);
        fixed.remove(6);
        printList(fixed);
        System.out.printf("\n%d\n", fixed.size);
        boolean found = fixed.find(4);
        boolean f1 = fixed.find(3);
        System.out.println(found);
        System.out.println(f1);
        fixed.add(11);
        printList(fixed);
        System.out.println();

        DoublyNode node1 = new DoublyNode(50);
        fixed.insert(node1);
        printList(fixed);
        System.out.println();

        DoublyNode node2 = new DoublyNode(39);
        fixed.insert(node2);
        printList(fixed);
        System.out.println();
        fixed.unlink(node1);
        printList(fixed);
        System.out.println();

        fixed.unlink(node2);
        printList(fixed);
        System.out.println();



        //DoublyLinkedList fixed1 = generateList(15);
        //fixed.append(fixed1);
        //System.out.printf("%d\n", fixed.listSize);
    }
}
