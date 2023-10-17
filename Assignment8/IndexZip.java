import java.io.BufferedReader;
import java.io.FileReader;

public class IndexZip{
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

    public IndexZip(String file){
        data = new Node[100000];
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[code] = new Node(code ,row[1], Integer.valueOf(row[2]));
            }
        }catch(Exception e){
            System.out.println("File " + file + " not found!");
        }
    }

    public String lookup(Integer zip){
        if(data[zip] == null)
            return null;
        return data[zip].name;
    }

    public boolean linear(Integer zip){
        for(int i = 0; i < max; i++){
            if(data[i].code == zip){
                return true;
            }
        }
        return false;
    }

    public static double lookBench(int tries, int loop, IndexZip file, Integer s){
        double min = Double.POSITIVE_INFINITY;
        for(int k = 0; k < tries; k++){

            long start = System.nanoTime();
            for(int i = 0; i < loop; i++)
                file.lookup(s);
            
            long end = System.nanoTime();
            double total = end - start;
            if(total  < min)
                min = total;
        }
        return min/loop;
    }


    public static void main(String[] args){
        IndexZip f = new IndexZip("postnummer.csv");
        Integer s1 = 11115;
        Integer s2 = 98399;
        int tries = 100;
        int loop = 100;
        // warm up
        lookBench(tries, loop, f, s1);
        lookBench(tries, loop, f, s2);
       // binBench(tries, loop, f, s1);
       // binBench(tries, loop, f, s2);
        
        double lin1 = lookBench(tries, loop, f, s1);
        double lin2 = lookBench(tries, loop, f, s2);
        //double bin1 = binBench(tries, loop, f, s1);
       // double bin2 = binBench(tries, loop, f, s2);
        System.out.println();
        System.out.println("3.Testing lookup in ns");
        System.out.printf("%8s\t%8s\n", "code", "lookup");
        System.out.printf("%8s\t%8.0f\n", s1, lin1);
        System.out.printf("%8s\t%8.0f\n", s2, lin2);
        System.out.println();
    }
}
