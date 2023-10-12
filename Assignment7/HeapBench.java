import java.util.Random;

public class HeapBench {

    public static void main(String[] args){
        Random rand = new Random();
        // benchmarking of how deep push operations go in the tree
        Heap heap = new Heap();
        for(int i = 0; i < 1024; i++){
            heap.enqueue(rand.nextInt(10000));
        }
        System.out.println("Pushing elements in heap");
        System.out.printf("%7s\t%8s\n","#n of push" , "depth");

        for(int k = 0; k < 15;k++){
            int r = rand.nextInt(10, 100);
            System.out.printf("%8d\t",r);
            int d = heap.push(r);
            System.out.printf("%8d\n",d);
        }

    }
    
}
