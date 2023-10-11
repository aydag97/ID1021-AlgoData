import java.util.Random;

public class Bench {

    // generates an array of random values of size n
    private static int[] unsorted(int n){
		Random rand = new Random();
        int[] array = new int[n];

        for(int i = 0; i < n;i++){
            array[i] = rand.nextInt(1000);
        }
		return array;
	}

    // copy the list
    private static QuickSortLL copyList(QuickSortLL list){
        QuickSortLL copy = new QuickSortLL();
        QuickSortLL.Node curr = list.head;
        while(curr != null){
            copy.add(curr.value);
            curr = curr.next;
        }
        return copy;
    }

    private static double arrayBench(int[] array, int tries, int loop){
        double min = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){
            double startTime = System.nanoTime();
            for(int s = 0; s < loop; s++){
                int[] copy = array.clone();
                //int[] copy = copyArr(array);
                QuickSortArr.sort(copy, 0, copy.length-1);
            }
            double endTime = System.nanoTime();
            double total = endTime - startTime;
            if(total < min)
                min = total;
        }
        return min/(loop*1000);
    }

    private static double llBench(QuickSortLL list, int tries, int loop){
        double min = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){
            double startTime = System.nanoTime();
            for(int s = 0; s < loop; s++){
                QuickSortLL copy = copyList(list);
                QuickSortLL.sortLL(copy);
            }
            double endTime = System.nanoTime();
            double total = endTime - startTime;
            if(total < min)
                min = total;
        }
        return min/(loop*1000);
    }

    public static void main(String[] args){

        System.out.printf("#%s\t%8s\t%8s\t%8s\t%8s\t%8s\n", "n", "Array","arr/n*log(n)",
        "Linked list","LL/n*log(n)", "LL/Arr");

        int[] sizes = {100,200,400,800,1000,2000,4000,8000,16000,32000,64000,128000,256000,512000};
        int tries = 100;
        int loop = 20;

        for(int n : sizes){
            int[] array = unsorted(n);
            QuickSortLL list = new QuickSortLL();
            // we want the same random values in the list
            for(int i = 0; i < n; i++){
                list.add(array[i]);
            }
            double arrTime = arrayBench(array, tries, loop);
            double listTime = llBench(list, tries, loop);
            double ratio = listTime/arrTime;
            
            System.out.printf("%d\t%8.0f\t%8.4f\t%8.0f\t%8.4f\t%8.2f\n", n, arrTime, 
            arrTime/(n*Math.log(n)),listTime,listTime/(n*Math.log(n)), ratio);
        }
    } 
}