
public class Static extends Stack{
    private static int[] myArr ;
    private int sp = 0;

    public Static(int size){
        sp = 0;
        myArr = new int[size];
    }

    public void push(int element){
        if(sp == myArr.length){
            System.out.println("Stack Overflow!");
            System.exit(0);
        }
        myArr[sp] = element;
        sp++;       // stack pointer points to the top of stack
    }

    public int pop(){
        
        if(sp==0){
            System.out.println("Stack is already empty!");
            System.exit(0);
        }
        sp--;
        return myArr[sp]; 
    }
}