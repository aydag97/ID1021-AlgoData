
import java.util.Random;

public class HeapBench {
    // bench for removing an item and add it with incr to the tree
    public static double addBench(Heap h, int loop, int tries, int n, int[] random){
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
    // bench for removing an item and add it with incr to the array heap
    public static double arrAddB(HeapArr a, int loop, int tries, int n, int[] random){
        Random rnd = new Random();
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < loop; i++){
            a.clear();
            for(int j = 0; j < n; j++){
                a.add(rnd.nextInt(tries));
            }

            long start = System.nanoTime();
            for(int k = 0; k < random.length; k++){
                int removed = a.remove();
                a.add(removed + random[k]);
            }
            long end = System.nanoTime();
            double total = end - start;

            if(total < min)
                min = total;
        }
        return min;
    }

    //bench for pushing to the tree
    public static double pushBench(Heap h, int loop, int tries, int n, int[] random){
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
    //bench for pushing to the array heap
    public static double arrPushB(HeapArr a, int loop, int tries, int n, int[] random){
        Random rnd = new Random();
        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < loop; i++){
            a.clear();
            for(int j = 0; j < n; j++){
                a.add(rnd.nextInt(tries));
            }

            long start = System.nanoTime();
            for(int k = 0; k < random.length; k++){
                a.push(random[k]);
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
        //int[] sizes = {100,200,400,800,1600,3200,6400,12800,25600,51200};
        int[] sizes = {100,200,400,800,1000,2000,4000,8000,16000,32000,64000,128000,256000};
        Heap pushHeap = new Heap();
        Heap addHeap = new Heap();

        Heap pushD = new Heap();
        Heap removeD = new Heap();

        int tries = 1000;
        int loop = 100;
        int[] randomArr = new int[tries];

        for(int j = 0; j < tries; j++){
            randomArr[j] = rand.nextInt(10,1000);
        }

        for(int i = 0; i < 1024; i++){
            int item = rand.nextInt(10000);
            pushD.enqueue(item);
            removeD.enqueue(item);
        }

        System.out.println("Pushing elements vs remove/add elements in heap tree vs array in micros");
        System.out.printf("%8s\t%8s\t%8s\t%8s\t%8s\n","#n" , "removeTree","pushTree" ,
         "removeArr", "pushArr");

        for(int n : sizes){
            System.out.printf("%8d\t", n);
            HeapArr arr = new HeapArr(n);
            double timeAdd = addBench(addHeap, loop, tries, n, randomArr);
            double timePush = pushBench(pushHeap, loop, tries, n, randomArr);

            double timeAddArr = arrAddB(arr, loop, tries, n, randomArr);
            double timePushArr = arrPushB(arr, loop, tries, n, randomArr);

            //int incr = rand.nextInt(10, 50);
            // how deep it goes when pushing/adding
            /*int depth = pushD.push(incr);
            int removed = removeD.dequeue();
            int d = removeD.enqueue(removed+incr);*/

            System.out.printf("%8.0f\t%8.0f\t%8.0f\t%8.0f\n", timeAdd/1000, timePush/1000,
            timeAddArr/1000, timePushArr/1000);
        }
    }
}
