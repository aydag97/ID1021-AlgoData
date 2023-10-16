import java.util.Random;

public class ListBench{

    public static int[] randomArr(int size, int n){
        Random rand = new Random();
        int[] myArr = new int[size];
        for(int i = 0; i < size ;i++){
            myArr[i] = rand.nextInt(n);
        }
		return myArr;
    }

    public static double addList(int n, int loop, int[] randomArr){

        double min = Double.POSITIVE_INFINITY;
        for(int j = 0; j < loop; j++){
            ListPQ list = new ListPQ();
            long start = System.nanoTime();
            for(int i = 0; i < n ; i++){
                list.add(randomArr[i]);
            }
            long end = System.nanoTime();
            double total = end-start;
            if(total < min)
                min = total;
        }  
        return min/n;
    }

    public static double addReverse(int n, int loop, int[] randomArr){

        double min = Double.POSITIVE_INFINITY;
        for(int j = 0; j < loop; j++){
            ListPQReversed list = new ListPQReversed();
            long start = System.nanoTime();
            for(int i = 0; i < n ; i++){
                list.add(randomArr[i]);
            }
            long end = System.nanoTime();
            double total = end-start;
            if(total < min)
                min = total;
        }  
        return min/n;
    }

    public static double removeList(int n, int loop, int[] randomArr, ListPQ list){

        double min = Double.POSITIVE_INFINITY;
        for(int j = 0; j < loop; j++){

            for(int i = 0; i < n ; i++){
                list.add(randomArr[i]);
            }
            long start = System.nanoTime();
            for(int i = 0; i < n ; i++){
                list.remove();
            }
            long end = System.nanoTime();
            double total = end-start;
            if(total < min)
                min = total;
        }
        return min/n;
    }

    public static double removeReversed(int n, int loop, int[] randomArr, ListPQReversed list){

        double min = Double.POSITIVE_INFINITY;
        for(int j = 0; j < loop; j++){
            for(int i = 0; i < n ; i++){
                list.add(randomArr[i]);
            }
            long start = System.nanoTime();
            for(int i = 0; i < n ; i++){
                list.remove();
            }
            long end = System.nanoTime();
            double total = end-start;
            if(total < min)
                min = total;
        }
        return min/n;
    }

    public static void main(String[] args){
        //int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000,256000};
        int[] sizes = {100,200,400,800,1600,3200,6400,12800,25600};
        int loop = 20;

        System.out.printf("# Benchmark of add and remove: list vs reversed in micros\n");
        System.out.printf("#%7s\t%8s\t%8s\t%8s\t%8s\t%8s\t%8s\n", "n", "add O(1)",
        "remove O(n)","remove/n" ,"add O(n)" ,"add/n", "remove O(1)");

        for(int s: sizes){
            System.out.printf("%8d\t", s);
            ListPQ list = new ListPQ();
            int[] random = randomArr(s,1000);
            ListPQReversed reversedList = new ListPQReversed();

            double time1 = addList(s, loop, random);
            double time2 = removeList(s, loop, random, list);

            double time3 = addReverse(s, loop, random);
            double time4 = removeReversed(s, loop, random, reversedList);

            System.out.printf("%8.3f\t%8.3f\t%8.3f\t%8.3f\t%8.3f\t%8.3f\n",time1, 
            time2, time2/s, time3, time3/s, time4);

        }
    }
}
