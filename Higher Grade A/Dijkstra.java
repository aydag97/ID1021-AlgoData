import java.util.PriorityQueue;

public class Dijkstra{
    private Path[] done; // to keep shortest paths
    private PriorityQueue<Path> queue;
    
    private class Path implements Comparable<Path>{
        private City city;
        private City previous;
        private Integer distance;

        private Path(City city){
            this.city = city;
            previous = null;
            distance = 0;
        }
        private Path(City city, City previous, Integer distance){
            this.city = city;
            this.previous = previous;
            this.distance = distance;
        }

        @Override
        public int compareTo(Path p){
            if(this.distance == p.distance){
                return 0;
            }else if(this.distance > p.distance){
                return 1;
            }
            else{
                return -1;
            }
        }
    }

    public Dijkstra(Map map){
        int len = map.size;
        done = new Path[len];
        queue = new PriorityQueue<Path>();
    }
    // returns the distance of a city in the done array
    public Integer getDistance(City cty){
        if(cty != null && done[cty.id] != null)
            return done[cty.id].distance;
        else
            return null;
    }

    public boolean getCity(City cty){
        return (done[cty.id] == null);  
    }
    // return the previous city
    public City getPreCity(City cty){
        return done[cty.id].previous;
    }
    // get the number of elements in done array
    public int getDoneLen(){
        int ret = 0;
        for(int i = 0; i < done.length; i++){
            if(done[i] != null){
                ret++;
            }
        }
        return ret;
    }
    // takes start and destination, searches for the shortest path by calling shortest()
    public void search(City start, City destination){
        Path shortPath = new Path(start);
        queue.add(shortPath); // add the start city to the PQ
        shortest(destination);

    }

    public void shortest(City destination){
        while(!(queue.isEmpty())){

            Path p = queue.remove(); // take the city with high priority
            City start = p.city;
            // if we don't find the city in done list, then add it to done
            if(done[start.id] == null){
                done[start.id] = p;
                
                if(destination == start)
                    break;

                Integer soFar = p.distance;
                for(Connection conn : start.neighbours){
                    City to = conn.city; // examining neighbours
                    if(done[to.id] == null){ // keep searching only if they are not in done
                        Path newPath = new Path(to, start, conn.distance + soFar);
                        queue.add(newPath);
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        Map m = new Map("europe.csv");
        Dijkstra djk = new Dijkstra(m);
        Integer dist = 0;

        String[] city = {"Stockholm", "Birmingham"};

        City from = m.find(city[0]);
        City to = m.find(city[1]);
        
        System.out.println("\nfrom " + city[0] + " to " + city[1] + "\n");

        long  t0 = System.nanoTime();
        djk.search(from, to);
        long time = (System.nanoTime() - t0)/1000;
        
        dist = djk.getDistance(to);
        System.out.println("shortest: " + dist + " min (" + time + " us)\n");

        for ( City prv = to; prv != null; prv = djk.getPreCity(prv)) {
            System.out.println("--" + prv. name);
        }
    }
}