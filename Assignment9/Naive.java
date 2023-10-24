
/*
* first task: finding the shortest path between two cities
* given a max distance (not the best solution)
*/

public class Naive {

    public static Integer shortest(City start, City destination, Integer max){
        if(max < 0)
            return null;
        if(start == destination)
            return 0;
        
        Integer minPath = null;
        for(Connection conn : start.neighbours){
            Integer dist = shortest(conn.city, destination, max-conn.distance);
            if(dist != null){
                if(minPath == null || minPath > dist + conn.distance)
                    minPath = dist + conn.distance;
            }
        }
        return minPath;
    }

    public static void main(String[] args){
        
        Map map = new Map("trains.csv");
        String[] city = {"Göteborg", "Umeå","705"};

        City from = map.find(city[0]);
        City to = map.find(city[1]);
        Integer max = Integer.valueOf(city[2]);

        System.out.println("\nfrom " + city[0] + " to " + city[1] + "\n");

        long  t0 = System.nanoTime();
        Integer dist = shortest(from, to, max);
        long time = (System.nanoTime() - t0)/1000000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)\n");
    }   
}
