
public class HP35{
    public static void main(String[] args) {
        // 10 + 2 * 5
        // 10 2 5 * + in reversed Polish notation
        // 2*2 +  3*6 = 22
        /*Item[] expr = {
        Item.Value(10),
        Item.Value(2),
        Item.Value(5),
        Item.Mul(),
        Item.Add()
        };
        
        Calculator calc = new Calculator(expr);
        int res = calc.run();
        System.out.println(" Calculator: res = " + res);*/
        
        System.out.printf("Number \t %s\t%s\t%s\n", "Static(Ms)","Dynamic(Ms)","Ratio");
        benchMark(10);
        benchMark(20);
        benchMark(50);
        benchMark(100);
        benchMark(200);
        benchMark(400);
        benchMark(800);
        benchMark(1600);
        benchMark(3200);
        
       
        
    }
    public static void StackTime(int iter,int stackLoop, Stack myStack){
        for(int j = 0; j < iter; j++){
            for(int i = 0; i < stackLoop; i++){
            myStack.push(i);
        }
        for(int i = 0; i < stackLoop; i++){
            myStack.pop();
        }

        }
    }

    public static double minTime(int iter, int loop,int stackLoop, Stack myStack){
        double min = Double.POSITIVE_INFINITY;
        double startTime = 0;
        double endTime = 0;
        double totalTime = 0;

        for(int i = 0; i < loop; i++){
            startTime = System.nanoTime();
            StackTime(iter,stackLoop, myStack);
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
            if(totalTime < min){
                min = totalTime;
            }
        }
        return min;
    }
    public static void benchMark(int stackLoop){
        int loop= 10;
        int iter = 10000;
        Stack test = new Static(stackLoop);
        Stack test2 = new Dynamic();
        //minTime(iter*10, loop, stackLoop, test);
        double staticTime = minTime(iter, loop, stackLoop, test);
        double dynamicTime = minTime(iter, loop, stackLoop, test2);
        double ratio = (dynamicTime/(iter*1000))/(staticTime/(iter*1000));
        System.out.printf("%d \t %.2f\t\t%.2f\t\t%.2f\n", stackLoop, (staticTime/(iter*1000)),(dynamicTime/(iter*1000)),ratio);
    }
}
