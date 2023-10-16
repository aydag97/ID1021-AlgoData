import java.io.BufferedReader;
import java.io.FileReader;

public class IntegerZip{
    Node[] data;
    int max;

    public class Node{
        Integer code;
        String name;
        Integer pop;
        
        public Node(Integer code, String name, Integer pop){
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    public IntegerZip(String file){
        data = new Node[10000];
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[i++] = new Node(code ,row[1], Integer.valueOf(row[2]));
            }
            max = i-1;
        }catch(Exception e){
            System.out.println("File " + file + " not found!");
        }
    }

    public boolean linear(Integer zip){
        for(int i = 0; i < max; i++){
            if(data[i].code == zip){
                return true;
            }
        }
        return false;
    }

    public boolean binary(Integer zip){
        int first = 0;
        int last = max;

        while (true) {
            int mid = (first+last)/2;
            if (data[mid].code.equals(zip)) {
                return true;
            }
            if (data[mid].code < zip && mid < last) {
                first =  mid + 1;
                continue;
            }
            if (data[mid].code > zip && mid > first) {
                last = mid - 1 ;
                continue;
            }
            break;
        }
        return false;
    }

    public static double linearBench(int tries, int loop, IntegerZip file, Integer s){
        double min = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){

            long start = System.nanoTime();
            for(int i = 0; i < loop; i++)
                file.linear(s);
            
            long end = System.nanoTime();
            double total = end - start;
            if(total  < min)
                min = total;
        }
        return min/loop;
    }

    public static double binBench(int tries, int loop, IntegerZip file, Integer s){
        double min = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){

            long start = System.nanoTime();
            for(int i = 0; i < loop; i++)
                file.binary(s);
            
            long end = System.nanoTime();
            double total = end - start;
            if(total  < min)
                min = total;
        }
        return min/loop;
    }


    public static void main(String[] args){
        IntegerZip f = new IntegerZip("postnummer.csv");
        Integer s1 = 11115;
        Integer s2 = 98399;
        int tries = 100;
        int loop = 100;
        // warm up
        linearBench(tries, loop, f, s1);
        linearBench(tries, loop, f, s2);
        binBench(tries, loop, f, s1);
        binBench(tries, loop, f, s2);
        
        double lin1 = linearBench(tries, loop, f, s1);
        double lin2 = linearBench(tries, loop, f, s2);
        double bin1 = binBench(tries, loop, f, s1);
        double bin2 = binBench(tries, loop, f, s2);
        System.out.println();
        System.out.println("2.Searching for two codes(integers) linearly vs binary in ns");
        System.out.printf("%8s\t%8s\t%8s\n", "code", "linear", "binary");
        System.out.printf("%8s\t%8.0f\t%8.0f\n", s1, lin1, bin1);
        System.out.printf("%8s\t%8.0f\t%8.0f\n", s2, lin2, bin2);
        System.out.println();
    }
}
