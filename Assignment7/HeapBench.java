
import java.util.Random;

public class HeapBench {

    public static void main(String[] args){
        Random rand = new Random();
        // benchmarking of how deep push operations go in the tree
        Heap pushHeap = new Heap();
        Heap addHeap = new Heap();
        for(int i = 0; i < 1024; i++){
            int item = rand.nextInt(10000);
            pushHeap.enqueue(item);
            addHeap.enqueue(item);
        }
        System.out.println("Pushing elements in heap");
        System.out.printf("%8s\t%8s\t%8s\n","#incr" , "depth of push", "depth of add");

        for(int k = 0; k < 15;k++){
            int incr = rand.nextInt(10, 50);
            int depth = pushHeap.push(incr);
            int removed = addHeap.dequeue();
            int d = addHeap.enqueue(removed+incr);
            System.out.printf("%8d\t%8d\t%8d\n",incr,depth, d);
        }
    }
}
