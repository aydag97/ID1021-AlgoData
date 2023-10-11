import java.util.Random;

public class Bench {

    private static double selectionBench(int[] array, int tries, int loop){
        double minSelec = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){
            double startTime = System.nanoTime();
            for(int s = 0; s < loop; s++){
                int[] copy = array.clone();
                Sort.selectionSort(copy);
            }
            double endTime = System.nanoTime();
            double total = endTime - startTime;
            if(total < minSelec)
                minSelec = total;
        }
            return minSelec/(loop*1000);
    }

    private static double insertionBench(int[] array, int tries, int loop){
        double minInser = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){
            double startTime = System.nanoTime();
            for(int s = 0; s < loop; s++){
                int[] copy = array.clone();
                Sort.insertionSort(copy);
            }
            double endTime = System.nanoTime();
            double total = endTime - startTime;
            if(total < minInser)
                minInser = total;
        }
            return minInser/(loop*1000);
    }

    private static double mergeBench(int[] array, int tries, int loop){
        double minMerge = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){
            double startTime = System.nanoTime();
            for(int s = 0; s < loop; s++){
                int[] copy = array.clone();
                Sort.sort(copy);
            }
            double endTime = System.nanoTime();
            double total = endTime - startTime;
            if(total < minMerge)
                minMerge = total;
        }
            return minMerge/(loop*1000);
    }

    private static int[] unsorted(int n){
		Random rand = new Random();
        int[] array = new int[n];

        for(int i = 0; i < n;i++){
            array[i] = rand.nextInt(n);
        }
		return array;
	}

    public static void main(String[] args){
        System.out.printf("#%s\t%8s\t%8s\t%8s\t%8s\t%8s\n", "n", "Selection","Insertion", "Ratio", "Merge", "Ratio2");
        int tries = 10;
        int loop = 10;

        //n = array size
        for(int n = 100; n <= 51200; n*=2){
            int[] array = unsorted(n);
            double selec = selectionBench(array, tries, loop);
            double inser = insertionBench(array, tries, loop);
            double ratio = selec/inser;
            double merge = mergeBench(array, tries, loop);
            double ratio2 = inser/merge;
            System.out.printf("%d\t%8.0f\t%8.0f\t%8.2f\t%8.0f\t%8.2f\n", n, selec, inser, ratio, merge, ratio2);
        }
    }
}
