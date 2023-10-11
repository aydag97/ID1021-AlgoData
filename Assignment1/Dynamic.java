public class Dynamic extends Stack {

    private static int[] myArr;
    final int SIZE;
    private int sp = 0;         // stack pointer
    private int size;

    public Dynamic(){
        SIZE = 4;
        myArr = new int[SIZE];
        sp = 0;
        size = SIZE;
    }

    public void push(int element){
        
        if(sp == size){
           int[] newArr = new int[size*2];
           for(int i = 0; i < size ;i++){
            newArr[i] = myArr[i];
           }
           size = size * 2;
           myArr = newArr;  // myArr now points to the larger stack
        }
        myArr[sp] = element;
        sp++;
    }

    public int pop(){

         if(sp==0){
            System.out.println("Stack is already empty!");
            System.exit(0);
        }
        sp--;
        if(sp < size/4 && size > SIZE){
            int [] newArr = new int [size/2];
            size = size/2;
            for(int i= 0; i < sp; i++){
                newArr[i] = myArr[i];
            }
            myArr = newArr;
            // myArr now points to the larger stack
        }  
        return myArr[sp];
    }
}