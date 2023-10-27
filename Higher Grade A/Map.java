import java.io.BufferedReader;
import java.io.FileReader;

// read the file and create a graph of it
public class Map{

    private City[] cities;
    private final int mod = 541; // prime number (used in hash function)
    private Integer id;
    public int size;

    public Map(String file){
        cities = new City[mod];
        id = 0;
        size = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                City one = lookupAdd(row[0]);
                City two = lookupAdd(row[1]);
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
    public City lookupAdd(String name){
        Integer hashKey = hash(name);
        while(true){
            if(cities[hashKey] == null){
                City city = new City(name, id++);
                size++;
                cities[hashKey] = city;
                return city;
            }
            if(cities[hashKey].name.equals(name)){
                return cities[hashKey];
            }
            hashKey = (hashKey + 1) % mod;
        }
    }

    public static void main(String[] args){
        Map map = new Map("europe.csv");

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
        System.out.println(map.size);
    } 
}
