public class Bench{

    public static void main(String[] args){

        Map m = new Map("europe.csv");
        Dijkstra djk = null;

        Integer dist = 0;
        String start = "Stockholm";

        String[] cities = {"Linköping", "Malmö", "Amsterdam", "Bryssel", "Paris", "Wien", 
                            "Birmingham","Budapest", "Milano", "Glasgow", "Madrid", "Brindisi"};

        City from = m.find(start);
        System.out.println("\nfrom " + start);
            System.out.printf("%8s\t%8s\t%8s\t%8s\t%8s\n" ,"to:" , "shortest",
             "time(us)", "entries", "time/n*log(n)");

        for(String city : cities){

            double min = Double.POSITIVE_INFINITY;
            City to = m.find(city);

            for(int i = 0; i < 1000; i++){
                djk = new Dijkstra(m);
                long  t0 = System.nanoTime();
                djk.search(from, to);
                long t1 = System.nanoTime();

                double time = (t1 - t0);

                if(time < min)
                    min = time;
            }
            
            dist = djk.getDistance(to);
            int n = djk.getDoneLen();
            System.out.printf("%8s\t%8d\t%8.2f\t%8d\t%8.2f\n", city, dist, min/1000, n,
            min/(n*Math.log(n)));
            System.out.println();
        }
    }
}
