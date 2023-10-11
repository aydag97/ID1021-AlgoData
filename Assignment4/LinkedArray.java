
public class LinkedArray{
    // appends the second array to the end of the first one
    public static int[] append(int[] array1, int[] array2){
        int firstSize = array1.length;
        int secondSize = array2.length;
        int[] finalArr = new int[firstSize + secondSize];

        for(int i = 0; i < firstSize; i++){
            finalArr[i] = array1[i];
        }
        for(int j = 0; j < secondSize; j++){
            finalArr[firstSize + j] = array2[j];
        }
        return finalArr;
    }


    public static int[] generateArray(int n){
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = i;
        }
        return array;
    }

    public static void main(String[] args){

        int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800,25600,51200,102400};
        System.out.printf("# Benchmark: appending an array at the end of another one\n");
        System.out.printf("#%7s\t%8s\t%8s\t%8s\t%8s\n", "n", "G to F","Total GF", "F to G", "Total FG");

        for(int n : sizes){
            System.out.printf("%8d\t", n);
            int k = 1000;
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long start = System.nanoTime();

                int[] fixed = generateArray(1000);
                int[] growing= generateArray(n);
                long startTime = System.nanoTime();
                int[] test = append(fixed,growing);
                long endTime = System.nanoTime();
                double total1 = (endTime - start);
                double total2 = (endTime - startTime);
                if (total1 < min1)
                    min1 = total1;

                if (total2 < min2)
                    min2 = total2;
            }
            System.out.printf("%8.2f\t%8.2f\t", (min2/1000), min1/1000);


            min1 = Double.POSITIVE_INFINITY;
            min2 = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                long start = System.nanoTime();
                int[] fixed = generateArray(1000);
                int[] growing= generateArray(n);
                long startTime = System.nanoTime();
                int[] test = append(growing,fixed);
                long endTime = System.nanoTime();
                double total1 = (endTime - start);
                double total2 = (endTime - startTime);
                if (total1 < min1)
                    min1 = total1;

                if (total2 < min2)
                    min2 = total2;
            }
            System.out.printf("%8.2f\t%8.2f\n", min2/1000, min1/1000);
        }
    }
}
