import java.util.Random;

public class Bench {
    // creates an array of random values
    public static int[] randomArr(int size, int n){
        Random rand = new Random();
        int[] myArr = new int[size];
        for(int i = 0; i < size ;i++){
            myArr[i] = rand.nextInt(n);
        }
		return myArr;
    }

    public static double dllBench(DoublyNode[] array, DoublyLinkedList list, int[] randArr, int loop, int keys, int size){

        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < size; i++){
                DoublyNode node = new DoublyNode(i);
                list.insert(node);
                array[i] = node;
            }
        for(int i = 0; i < loop; i++){
            long start = System.nanoTime();
            for(int j = 0; j < keys; j++){
                list.unlink(array[randArr[j]]);
                list.insert(array[randArr[j]]);
            }
            long end = System.nanoTime();
            double total = end-start;

            if(total < min)
                min = total;
            }
        return min;
    }

    public static double sllBench(Node[] array, LinkedList list, int[] randArr, int loop, int keys, int size){

        double min = Double.POSITIVE_INFINITY;

        for(int i = 0; i < size; i++){
                Node node = new Node(i);
                list.insert(node);
                array[i] = node;
            }
        for(int i = 0; i < loop; i++){
            long start = System.nanoTime();
            for(int j = 0; j < keys; j++){
                list.unlink(array[randArr[j]]);
                list.insert(array[randArr[j]]);
            }
            long end = System.nanoTime();
            double total = end-start;

            if(total < min)
                min = total;
            }
        return min;
    }

    public static void main(String[] args){
        int[] sizes = {25,50,100,200,400,800,1600,3200,6400,12800,25600,51200};
        
        int iter = 100;
        int k = 1000;

        System.out.printf("# Benchmark of Unlink and insert: doubly vs single linked list in microsec\n");
        System.out.printf("#%7s\t%8s\t%8s\n", "n", "Doubly","Single");

        for(int n : sizes){
            System.out.printf("%8d\t", n);

            // array of cells which keeps the references (cell array)
            DoublyNode[] dReference = new DoublyNode[n];
            // doubly list has nodes 0,1,2,3,...,n-1
            DoublyLinkedList dList = new DoublyLinkedList();

            Node[] sReference = new Node[n];
            LinkedList sList = new LinkedList();
            // random array to select a node and unlink/insert
            int[] random = randomArr(k,n);
            
            double dTime = dllBench(dReference, dList, random, iter, k, n);
            double sTime = sllBench(sReference, sList, random, iter, k, n);

            System.out.printf("%8.0f\t%8.0f\n",dTime/1000, sTime/1000);
        }
    }
}
