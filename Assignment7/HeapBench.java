
import java.util.Random;

public class HeapBench {

    public static double addBench(Heap h, int loop, int tries, int n, int[] random){
        // h ska vara tom, vi fyller i här men innan nästa loop så tömmer vi det mha clear()
        Random rnd = new Random();
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < loop; i++){
            h.clear();
            for(int j = 0; j < n; j++){
                h.enqueue(rnd.nextInt(tries));
            }

            long start = System.nanoTime();
            for(int k = 0; k < random.length; k++){
                int removed = h.dequeue();
                h.enqueue(removed + random[k]);
            }
            long end = System.nanoTime();
            double total = end - start;

            if(total < min)
                min = total;
        }
        return min;
    }

    public static double pushBench(Heap h, int loop, int tries, int n, int[] random){
        // h ska vara tom, vi fyller i här men innan nästa loop så tömmer vi det mha clear()
        Random rnd = new Random();
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < loop; i++){
            h.clear();
            for(int j = 0; j < n; j++){
                h.enqueue(rnd.nextInt(tries));
            }

            long start = System.nanoTime();
            for(int k = 0; k < random.length; k++){
                h.push(random[k]);
            }
            long end = System.nanoTime();
            double total = end - start;

            if(total < min)
                min = total;
        }
        return min;
    }

    public static void main(String[] args){
        Random rand = new Random();
        // benchmarking of how deep push operations go in the tree
        // storlek på heap upp till 1600 typ
        int[] sizes = {100,200,400,800,1600,3200,6400,12800};
        Heap pushHeap = new Heap();
        Heap addHeap = new Heap();
        int tries = 1000;
        int loop = 100;
        int[] randomArr = new int[tries];

        for(int j = 0; j < tries; j++){
            randomArr[j] = rand.nextInt(10,1000);
        }

        /*for(int i = 0; i < 1024; i++){
            int item = rand.nextInt(10000);
            pushHeap.enqueue(item);
            addHeap.enqueue(item);
        }*/

        
        System.out.println("Pushing elements vs remove/add elements in heap tree in micros");
        System.out.printf("%8s\t%8s\t%8s\n","#n" , "remove", "push");

        for(int n : sizes){
            System.out.printf("%8d\t", n);
            double timeAdd = addBench(addHeap, loop, tries, n, randomArr);
            double timePush = pushBench(pushHeap, loop, tries, n, randomArr);
            System.out.printf("%8.0f\t%8.0f\n", timeAdd/1000, timePush/1000);


        }

        /*for(int k = 0; k < 15;k++){
            int incr = rand.nextInt(10, 15);
            // mät tiden på denhär
            int depth = pushHeap.push(incr);
            // mät tiden på denhär och jämföra med andra (antal tries 100 och k = 1000)
            // ha den som vanligt i en method
            int removed = addHeap.dequeue();
            int d = addHeap.enqueue(removed+incr);
            System.out.printf("%8d\t%8d\t%8d\n",incr,depth, d);
        }*/

    }
}
