
/*
* third task: finding the shortest path between two cities (Paths improved)
* still using stacks, but starting with a null max distance,
* after finding the first path, keep its distance in max, 
* the other acceptable paths must be shorter than max.
*/

public class ImprovedPaths{
    public City[] path;
    public int sp;

    public ImprovedPaths(){
        path = new City[54];
        sp = 0;
    }

    private Integer shortest(City start, City destination, Integer max){
        if(max != null && max < 0)
            return null;

        if(start == destination)
            return 0;
        
        for (int i = 0; i < sp; i++) {
          if (path[i] == start)
            return null;
        }
    
        path[sp++] = start;
        Integer minPath = null;

        for(Connection conn : start.neighbours){
            Integer dist = shortest(conn.city, destination,(max != null) ? max-conn.distance: null);
            if(dist != null){
                if(minPath == null || minPath > dist + conn.distance){
                    minPath = dist + conn.distance;
                if(((minPath != null) && max == null)|| minPath < max)
                    max = minPath;
                }
            }
        }
        path[sp--] = null;
        return minPath;
    }

    
    public static void main(String[] args){
        ImprovedPaths path = new ImprovedPaths();
        
        Map map = new Map("trains.csv");
        String[] city = {"Malmö", "Kiruna"};

        City from = map.find(city[0]);
        City to = map.find(city[1]);

        System.out.println("\nfrom " + city[0] + " to " + city[1] + "\n");

        long  t0 = System.nanoTime();
        Integer dist = path.shortest(from, to, null);
        long time = (System.nanoTime() - t0)/1000000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)\n");
    }    
}