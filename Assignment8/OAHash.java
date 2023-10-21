import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/* this version handles collisions using open addressing. 
* if there are collisions, then it adds the item at the
* next empty spot.*/

public class OAHash{
    Node[] data;
    static int[] keys;
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

    public OAHash(String file , int mod){
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
                add(code, new Node(code ,row[1], Integer.valueOf(row[2])));
                i++;
            }
            max = i-1;
        }catch(Exception e){
            System.out.println("File " + file + " not found! " + e);
        }
    }
    /* collisions? find the next empty spot!*/
    public void add(Integer code, Node n){
        Integer hashKey = code % mod;
        while(data[hashKey] != null){
            // wrap around
            hashKey = (hashKey + 1) % mod; 
        }
        data[hashKey] = n;
    }
   
    public String lookup(Integer zip){

        Integer hashKey = zip%mod;
        int number = 0;

        while(data[hashKey] != null && !(data[hashKey].code.equals(zip))){
            // wrap around
            hashKey = (hashKey + 1) % mod;
            number++;
        }
        System.out.println("The number of cities to look before: " + number);
        return data[hashKey].name;
    }

    public static void main(String[] args){
        int[] mods = {10000,13513,20000,28447,31327};
        Random rnd = new Random();
        int r = rnd.nextInt(9676);
       System.out.println("\n6. slightly better");
        for(int m: mods){
            System.out.println();
            System.out.println("Array size/mod: " + m);
            OAHash h = new OAHash("postnummer.csv", m);
            Integer k = keys[r];
            System.out.println("The Post Code: " + k );
            String s = h.lookup(k);
            System.out.println("City: " + s);
        }
        System.out.println();
    }
}
