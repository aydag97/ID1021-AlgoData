import java.util.Random;

public class QuickSortArr{

    public static void sort(int[] array, int low, int high){
        // when array has one element, do nothing. otherwise we sort
        if(low >= high)
            return;
        int mid = partition(array, low, high);
        
        if(mid != low)   
            sort(array,low,mid-1);

        if(mid != high)
            sort(array,mid,high);
    }

    public static int partition(int[] array, int low, int high){
        // first element as pivot
        int pivot = array[low];
        int leftp = low;
        int rightp = high;
        // while the two pointers don't point to the same element
        while(leftp != rightp){
            // while leftp points to smthg smaller than pivot and it hasnt hit the rightp yet
            while(array[leftp] <= pivot && leftp < rightp){
                leftp++;
            }
            // while rightp points to smthg larger than pivot and it hasnt hit the leftp yet
            while(array[rightp] > pivot && rightp > leftp){
                rightp--;
            }
            // swap the elements that are in the wrong place
            swap(array,leftp,rightp);
        }
        // swap the element in rightp and the pivot. now pivot is in its right place
        if(array[rightp] < array[low])
            swap(array,low,rightp);
        
        return rightp;
    }

    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static int[] unsorted(int n){
		Random rand = new Random();
        int[] array = new int[10];

        for(int i = 0; i < 10;i++){
            array[i] = rand.nextInt(n);
        }
		return array;
	}
    public static void main(String[] args){
        int n = 1000;
        //int[] arr ={4,6,1,3,8,2,9,7,5,10};
        int[] arr = unsorted(n);
        System.out.println("the unsorted arr is:");

        for(int i = 0; i < 10; i++){
            System.out.printf("%d\t", arr[i]);
        }
        System.out.println();
        sort(arr,0,arr.length-1);
        System.out.println("the sorted arr is:");

        for(int i = 0; i < 10; i++){
            System.out.printf("%d\t", arr[i]);
        }
    }
}