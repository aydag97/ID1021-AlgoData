
public class HeapArr {
    int[] prioArr;
    int arrSize;
    int itemNo; // number of elements in the array

    public HeapArr(int size){
        arrSize = size;
        prioArr = new int[arrSize];
        itemNo = 0;
    }

    public void swap(int[] array, int first, int second){
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public void bubble(int pos){
        if(pos == 0)
            return;
        int parentPos = (pos-1)/2;
        if(prioArr[pos] < prioArr[parentPos])
            swap(prioArr,pos,parentPos);
        bubble(parentPos);
    }

    public int sink(int pos, int depth){
        int childL = (2*pos) + 1;
        int childR = (2*pos) + 2;
        if(childR < itemNo){
            if(prioArr[childL] < prioArr[childR]){
                if(prioArr[pos] > prioArr[childL]){
                    swap(prioArr, pos, childL);
                    depth++;
                    return sink(childL, depth);
                }
            }
            else{
                if(prioArr[pos] > prioArr[childR]){
                    swap(prioArr,pos,childR);
                    depth++;
                     return sink(childR, depth);
                }
            }
        }
        return depth;
    }

    public void add(int item){
        if(itemNo == arrSize){
            // if arrays is full, allocate more memory
            int[] newArr = new int[2*arrSize];
            for(int i = 0; i < arrSize; i++){
                newArr[i] = prioArr[i];
            }
            prioArr = newArr;
            arrSize = 2*arrSize;
        }
        prioArr[itemNo] = item;
        bubble(itemNo);
        itemNo++;
    }

    public int remove(){
        if(itemNo == 0){
            System.out.println("The queue is empty!");
            return -1;
        }
        int ret = prioArr[0];
        itemNo--;
        prioArr[0] = prioArr[itemNo];
        prioArr[itemNo] = 0;
        sink(0, 0);
        return ret;
    }

    public int push(int incr){
        prioArr[0]+= incr;
        // returns the depth of the pushed item
        return sink(0 , 0);
    }

    public void print(){
        if(itemNo == 0){
            System.out.println("Th list is empty");
        }
        for(int i = 0; i < itemNo; i++){
            System.out.printf("%d\t", prioArr[i]);
        }
        System.out.println();
    }

    public void clear(){
        prioArr = new int[arrSize];
    }

    public static void main(String[] args){
        HeapArr q = new HeapArr(10);
        q.add(5);
        q.add(4);
        q.add(6);
        q.add(8);
        q.add(7);
        q.print();
        System.out.println();

        q.remove();
        q.print();
        System.out.println();
        q.add(2);
        q.print();
        System.out.println();

        int d = q.push(10);
        q.print();
        System.out.println();
        System.out.println(d);
        System.out.println();
        int d2 = q.push(20);
        q.print();
        System.out.println();
        System.out.println(d2);
    } 
}
