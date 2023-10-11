
import java.util.Random;


class Bench {

    private static void linearUnsorted(int[] array, int[] indx) {
		for (int i = 0; i < indx.length ; i++) {
			Search.search_unsorted(array, indx[i]);
		}
    }
    
	private static void linearSorted(int[] array, int[] indx) {
		for (int i = 0; i < indx.length ; i++) {
			Search.search_sorted(array, indx[i]);
		}
    }

    private static void binary(int[] array, int[] indx) {
		for (int i = 0; i < indx.length ; i++) {
			Search.binary_search(array, indx[i]);
		}
    }

	private static void dup_binary(int[] array1, int[] array2,int n) {
		for (int i = 0; i < n ; i++) {
			Search.duplicate_binary(array1, array2);
		}
    }
	private static void dup_linear(int[] array1, int[] array2,int n) {
		for (int i = 0; i < n ; i++) {
			Search.duplicate_linear(array1, array2);
		}
    }

	private static void better_dup(int[] array1, int[] array2,int n) {
		for (int i = 0; i < n ; i++) {
			Search.two_pointers(array1, array2);
		}
    }

       
    private static int[] sorted(int n) {
		Random rnd = new Random();	
		int[] array = new int[n];
		int nxt = rnd.nextInt(10);

		for (int i = 0; i < n ; i++) {
			array[i] = nxt;
			nxt += rnd.nextInt(10) + 1 ;
		}	
		return array;
    }


	private static int[] unsorted(int n){
		Random rand = new Random();
        int[] array = new int[n];

        for(int i = 0; i < n;i++){
            array[i] = rand.nextInt(n);
        }
		return array;
	}


    private static int[] keys(int loop, int n) {
		Random rnd = new Random();	
		int[] indx = new int[loop];
		for (int i = 0; i < loop ; i++) {
			indx[i] = rnd.nextInt(n*5);
		}	
		return indx;
    }

    
    public static void main(String[] arg) {

		int[] sizes = {100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500,1600,2000,2500,3000,3500,4000,4500,5000,5500,6000};
		//int[] sizes = {64000000};

		System.out.printf("# searching through an array of length n, time in ns\n");
		//System.out.printf("#%7s\t%8s\t%8s\t%8s\n", "n", "unsorted","sorted", "binary");
		System.out.printf("#%7s\t%8s\t%8s\t%8s\n", "n", "Linear","Binary", "2Pointers");
		for ( int n : sizes) {

			int loop = 100;
			int[] sortedArr = sorted(n);
			int[] unsortedArr = unsorted(n);
			int[] indx = keys(loop, n);

			System.out.printf("%8d\t", n);

			int k = 100;
			double min = Double.POSITIVE_INFINITY;

			// unsorted search
			// **************************************

			for (int i = 0; i < k; i++) {
				long t0 = System.nanoTime();
				linearUnsorted(unsortedArr, indx);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				if (t < min)
					min = t;
			}

			System.out.printf("%8.0f\t", (min/loop));
			// *************************************


			// sorted search
			// **************************************
			min = Double.POSITIVE_INFINITY;

			for (int i = 0; i < k; i++) {
				long t0 = System.nanoTime();
				linearSorted(sortedArr, indx);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				if (t < min)
					min = t;
			}

			System.out.printf("%8.0f\t", (min/loop)); 
			// ************************************* 


			// binary search
			// **************************************

			min = Double.POSITIVE_INFINITY;
			
			for (int i = 0; i < k; i++) {
				long t0 = System.nanoTime();
				binary(sortedArr, indx);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				if (t < min)
					min = t;
				}

			System.out.printf("%8.0f\n" , (min/loop));
			
			// **************************************

			/*min = Double.POSITIVE_INFINITY;
			int[] unsortedArr1 = unsorted(n);
			int[] unsortedArr2 = unsorted(n);
			int[] sortedArr1 = sorted(n);
			int[] sortedArr2 = sorted(n);
			
			for (int i = 0; i < k; i++) {
				long t0 = System.nanoTime();
				dup_linear(unsortedArr1, unsortedArr2,loop);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				if (t < min)
					min = t;
				}

			System.out.printf("%8.0f\t" , (min/loop));


			min = Double.POSITIVE_INFINITY;
			
			for (int i = 0; i < k; i++) {
				long t0 = System.nanoTime();
				dup_binary(sortedArr1, sortedArr2,loop);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				if (t < min)
					min = t;
				}

			System.out.printf("%8.0f\t" , (min/loop));

			min = Double.POSITIVE_INFINITY;
			
			for (int i = 0; i < k; i++) {
				long t0 = System.nanoTime();
				better_dup(sortedArr1, sortedArr2,loop);
				long t1 = System.nanoTime();
				double t = (t1 - t0);
				if (t < min)
					min = t;
				}

			System.out.printf("%8.0f\n" , (min/loop));
		}*/
    }
}
}
