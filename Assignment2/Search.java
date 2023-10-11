
public class Search{

    public static boolean search_unsorted(int[] array, int key) {

        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
        }
        return false;
    }

    public static boolean search_sorted(int[] array, int key) {

        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
            if(array[index+1] > key){
                return false;
            }
        }
        return false;
    }

    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length-1;

        while (true) {
            int index = (first+last)/2;
            if (array[index] == key) {
                return true;
            }
            if (array[index] < key && index < last) {
                first =  index + 1;
                continue;
            }
            if (array[index] > key && index > first) {
                last = index - 1 ;
                continue;
            }
            break;
        }
        return false;
    }

    public static int duplicate_linear(int[] array1, int [] array2){
        int firstLen = array1.length;
        int secLen = array2.length;
        int counter = 0;
        for(int i = 0; i < firstLen; i++){
            for(int j = 0; j < secLen; j++){
                if(array1[i] == array2[j]){
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int duplicate_binary(int[] array1, int[] array2){
        int firstLen = array1.length;
        int count = 0;

        for(int i = 0; i < firstLen; i++){
            if(binary_search(array2, array1[i])){
                count++;
            }
        }
        return count;
    }

    public static int two_pointers(int[] array1, int[] array2){
        int countDup = 0;
        int firstLen = array1.length;
        int secLen = array2.length;
        int ptr1 = 0;   // counter for first array
        int ptr2 = 0;   // counter for the second array
        while(ptr1 < firstLen && ptr2 < secLen){
            if(array2[ptr2] < array1[ptr1]){
                ptr2++;
                continue;
            }
            else if(array2[ptr2] == array1[ptr1]){
                countDup++;
                ptr1++;
                ptr2++;
                continue;
            }
            else if(array2[ptr2] > array1[ptr1]){
                ptr1++;
            }
        }
        return countDup;
    }
}