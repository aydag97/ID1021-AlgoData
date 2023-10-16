import java.io.BufferedReader;
import java.io.FileReader;

public class Hash{
    Node[] data;
    int[] keys;
    int max;
    int mod;

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

    public Hash(String file , int mod){
        this.mod = mod;
        data = new Node[mod];
        keys = new int[9676];
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                keys[i] = code;
                data[code%mod] = new Node(code ,row[1], Integer.valueOf(row[2]));
                i++;
            }
            max = i-1;
        }catch(Exception e){
            System.out.println("File " + file + " not found!");
        }
    }

    public String lookup(Integer zip){
        return data[zip%mod].name;
    }

    public void collisions(int mod){
        int[] data = new int[mod];
        int[] cols = new int[10];
        for (int i = 0; i < max; i++){
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }
        System.out.print(mod + " |");
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] mods = {10000,20000,30000,40000,12345,13513,13600,14000};
        System.out.println("\n4. size matters");
        System.out.printf("\n%4s\t%8s\n" , "mod", "collisions");

        for(int mod: mods){
            Hash h = new Hash("postnummer.csv", mod);
            System.out.println();
            h.collisions(mod);
        }
        System.out.println();
        Hash h = new Hash("postnummer.csv", 10000);
        String s = h.lookup(11115);
        System.out.println(s);
    }
}
