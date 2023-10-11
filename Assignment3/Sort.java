public class Sort{

    public static void selectionSort(int[] array){
        // array[2,4,7,3,55,1,6,14,90]
        //       c=2,j=0

        for (int i = 0; i < array.length - 1; i++){
            int min = i; // väljer första elementet som det minsta
            for (int j = i; j < array.length ; j++){
                if(array[j] < array[min]){ // har vi hittat mindre?
                    min = j;  // sätt den till minsta
                }
            }
            // swap
            int temp = array[i];  // temp = 1
            array[i] = array[min]; // array[5] = 2
            array[min] = temp;
            
        }
    }

    public static void insertionSort(int[] array){
        int min;
        int j;
        for (int i = 1; i < array.length; i++) {
            min = array[i];
            j = i - 1;
            // Compare key with each element on the left of it until an element smaller than
            // it is found.
            while (j >= 0 && min < array[j]) {
                array[j + 1] = array[j]; // move elements to the right in the array
                j--;
            }
            array[j + 1] = min;
        }
    }

    public static void sort(int[] org) {
        if (org.length == 0)
           return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length -1);
    }

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = (lo + hi)/2;
            sort(org,aux,lo,mid);
            sort(org,aux,mid+1,hi);
            merge(org, aux, lo, mid, hi);
        }
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        for(int element = lo; element <= hi; element++){
            aux[element] = org[element];
        }
        int i = lo; 
        int j = mid+1; 
        for ( int k = lo; k <= hi; k++){
            if(i > mid){
                org[k] = aux[j];
                j++;
            }
            else if(j > hi){
                org[k] = aux[i];
                i++;
            }
            else if(aux[i] < aux[j]){
                org[k] = aux[i];
                i++;
            }
            else{
                org[k] = aux[j];
                j++;
            }
        } 
    }
    

    public static void main(String[] args){
        int[] myArr = {2,4,7,3,55,1,6,134,589,-1,0,14,90};

        System.out.println("Unsorted array: \n");
        for(int i = 0; i < myArr.length; i++){
            System.out.printf( myArr[i] + "\t");
        }
        System.out.println("\nSorted array: \n");
        sort(myArr);
        for(int i = 0; i < myArr.length; i++){
            System.out.printf(myArr[i] + "\t");
        }

    }
}