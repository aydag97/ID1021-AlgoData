
/*
* second task: finding the shortest path between two cities
* without a predefined max distance. instead detect loops
* by adding the visited cities to a stack
*/

public class Paths{
    public City[] path;
    public int sp;

    public Paths(){
        path = new City[54];
        sp = 0;
    }

    private Integer shortest(City start, City destination){
        
        // if we go to a city that we already visited, then return null
        for (int i = 0; i < sp; i++) {
          if (path[i] == start)
            return null;
        }
        // add the city we are in to the stack
        path[sp++] = start;

        if(start == destination)
            return 0;
        
        Integer minPath = null;
        for(Connection conn : start.neighbours){ //checking all neighbours of start city
            Integer dist = shortest(conn.city, destination);
            if(dist != null){
                if(minPath == null || minPath > dist + conn.distance)
                    minPath = dist + conn.distance;
            }
        }
        path[sp--] = null;
        return minPath;
    }

    public static void main(String[] args){
        Paths path = new Paths();
        
        Map map = new Map("trains.csv");
        String[] city = {"Malmö", "Kiruna"};

        City from = map.find(city[0]);
        City to = map.find(city[1]);

        System.out.println("\nfrom " + city[0] + " to " + city[1] + "\n");

        long  t0 = System.nanoTime();
        Integer dist = path.shortest(from, to);
        long time = (System.nanoTime() - t0)/1000;

        System.out.println("shortest: " + dist + " min (" + time + " us)\n");
    }    
}
