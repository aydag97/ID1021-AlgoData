import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/* this version handles collisions. 
* updated by adding an attribute next pointer into the Node class
* in the collision spots, all nodes with same hash are linked to 
* each other thanks to the next pointer*/

public class BetterHash{
    Node[] data;
    static int[] keys;
    int max;
    int mod;

    public class Node{
        Integer code;
        String name;
        Integer pop;
        Node next; // new
        
        public Node(Integer code, String name, Integer pop){
            this.code = code;
            this.name = name;
            this.pop = pop;
            next = null;
        }
    }

    public BetterHash(String file , int mod){
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
            System.out.println("File " + file + " not found!");
        }
    }
    /* adds a node in its hashed spot. 
    collision? next pointer of the previous one points to the new one*/
    public void add(Integer code, Node n){
        Integer hashKey = code%mod;
        if(data[hashKey] == null){
            data[hashKey] = n;
        }
        else{
            Node curr = data[hashKey];
            while(curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
        }
    }
    // collision? we check all next nodes till we find it
    public String lookup(Integer zip){

        Integer hashKey = zip%mod;
        int number = 0;

        if(data[hashKey].code.equals(zip)){
            System.out.println("the number of elements to check before: "+ number);
            return data[hashKey].name;
        }else{
            Node curr = data[hashKey];
            while(curr != null){
                if(curr.code.equals(zip)){
                    System.out.println("the number of elements to check before: "+ number);
                    return curr.name;
                }
                number++;
                curr = curr.next;
            }
            return null;
        }
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
        int[] mods = {10000,12345,13513,13600,14000, 20000,30000,40000};
       System.out.println("\n5. slightly better");
        for(int m: mods){
            System.out.println();
            System.out.println("Array size: " + m);
            BetterHash h = new BetterHash("postnummer.csv", m);
            Random rnd = new Random();
            Integer k = keys[rnd.nextInt(9676)];
            System.out.println("The Post Code: " + k);
            String s = h.lookup(k);
            System.out.println("City: " + s);
        }
    }
}

