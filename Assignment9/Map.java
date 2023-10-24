import java.io.BufferedReader;
import java.io.FileReader;

// read the file and create a graph of it
public class Map{

    private City[] cities;
    private final int mod = 541; // prime number (used in hash function)
    private int size;

    public Map(String file){
        cities = new City[mod];
        size = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                City one = lookup(row[0]);
                City two = lookup(row[1]);
                Integer dist = Integer.valueOf(row[2]);
                one.connect(two, dist);
                two.connect(one, dist);
            }
        }catch(Exception e){
            System.out.println("Error found: " + e);
        }
    }
    
    private Integer hash(String name){
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash*31 % mod) + name.charAt(i);
        }
        return Math.floorMod(hash, mod);
    }

    // returns the city we look for
    public City find(String name){
        Integer hashKey = hash(name);
        while(true){
            if(cities[hashKey] == null)
                return null;
            if(cities[hashKey].name.equals(name)){
                return cities[hashKey];
            }
            hashKey++;
            if(hashKey >= cities.length)
                hashKey = 0;
        }
    }

    // searches for a city, if it can't find it then it adds the city to graph
    public City lookup(String name){
        Integer hashKey = hash(name);
       // int colisions = 0; // inga collisions

        while(true){
            if(cities[hashKey] == null){
                City city = new City(name);
                cities[hashKey] = city;
                size++;
                return city;
            }
            if(cities[hashKey].name.equals(name)){
                //colisions++;
               // System.out.println("number of collisions: " + colisions);
                return cities[hashKey];
            }
            hashKey = (hashKey + 1) % mod;
        }
    }

    public static void main(String[] args){
        Map map = new Map("trains.csv");

        for(int i = 0; i < map.mod; i++){
            City city = map.cities[i];
            if(city != null){
                System.out.printf("%8s%8s", "City name: " , city.name);
                System.out.printf("\t %8s", "neightbours: ");
                for(Connection conn : city.neighbours){
                    System.out.printf("\t %8s", conn.city.name);
                }
                System.out.println();
            }
        }
    } 
}
