import java.util.NoSuchElementException;

public class Heap {
    Node root;
    
    private class Node{
        int value;
        Node right;
        Node left;
        int size;

        public Node(int value){
            this.value = value;
            right = null;
            left = null;
            size = 1;
        }

        public void add(int item){
            size ++;
            if(item < this.value){
                int temp = this.value;
                this.value = item;
                item = temp;
            }
    
            if(left == null){
                left = new Node(item);
                return;
            }
            else if(right == null){
                right = new Node(item);
            }
            else if(left.size <= right.size){
                left.add(item);
                return;
            }
            else if(right.size < left.size){
                right.add(item);
                return;
            }
        }

        public Node remove(){
            if(left == null){
                return right;
            }
            else if(right == null)
                return left;

            else if(right.value < left.value){
                value = right.value;
                right = right.remove();
            }
            else{
                value = left.value;
                left = left.remove();
            }
            size--;
            return this;
        }


        public void print(){
        if (left != null)
            left.print();
        System.out.println(" value: " + value);
        if (right != null)
            right.print();
        }
    }


    public Heap(){
        root = null;
    }

    public void enqueue(int item){
        if(root == null){
            root = new Node(item);
        }
        else{
            root.add(item);
        }
    }

    public int dequeue(){
        if(root == null)
            throw new NoSuchElementException();
        int ret = root.value;

        if(root.size == 1)
            root = null;
        else{
            root = root.remove();
        }
        return ret;
    }

    public int pushRecursive(int pushedNode, Node current, int depth){
        // if there are no left branches then we should push the item to the right
        if(current.left == null){
            // if there are no right either or the new pushed value is still smaller than
            // the right value then we haven't pushed so return depth
            if(current.right == null || pushedNode < current.right.value){
                return depth;
            }
            // but if the pushed is greater than right value, swap those two, increment depth
            else if(pushedNode > current.right.value){
                int temp = current.value;
                current.value = current.right.value;
                current.right.value = temp;
                depth++;
            }
            // if right branch has more children then check if we should push further
            if(current.right.size != 1){
                depth = pushRecursive(pushedNode, current.right, depth);
            }
        }
        // if the right branch is empty we go to left and do the same things as before
        else if(current.right == null){
            if(current.left == null || pushedNode < current.left.value){
                return depth;
            }
            else if(pushedNode > current.left.value){
                int temp = current.value;
                current.value = current.left.value;
                current.left.value = temp;
                depth++;
            }
            
            if(current.left.size != 1){
                depth = pushRecursive(pushedNode, current.left, depth);
            }
        }
        // if none of branches is empty, we go to a branch with smaller size
        else if(current.right.size > current.left.size){
            // all swaping and comparisons are the same as before 
            if(pushedNode > current.left.value){
                int temp = current.value;
                current.value = current.right.value;
                current.right.value = temp;
                depth++;
            }
            else if(pushedNode < current.left.value){
                return depth;
            }

            if(current.left.size != 1){
                depth = pushRecursive(pushedNode, current.left, depth);
            }
        }
        else{
            if(pushedNode > current.right.value){
                int temp = current.value;
                current.value = current.right.value;
                current.right.value = temp;
                depth++;
            }
            else if(pushedNode < current.right.value){
                return depth;
            }

            if(current.right.size != 1){
                depth = pushRecursive(pushedNode, current.right, depth);
            }
        }
        return depth;
    }


    public int push(int incr){
        root.value += incr;
        Node curr = root;
        return pushRecursive(root.value, curr, 0);
    }

    public void print() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        root.print();
    }

    public static void main(String[] args){
        Heap h = new Heap();
        h.enqueue(5);
        h.enqueue(8);
        h.enqueue(7);
        h.enqueue(6);
        h.print();
        System.out.println();

        //h.dequeue();
        h.print();

        System.out.println();
        int d = h.push(10);
        System.out.println(d);
        h.print();
    } 
}
