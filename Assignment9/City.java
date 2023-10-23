import java.util.ArrayList;

public class City{
    public String name;
    public ArrayList<Connection> neighbours;

    public City(String name){
        this.name = name;
        neighbours = new ArrayList<Connection>();
    }

    public void connect(City next, int dst){
        neighbours.add(new Connection(next, dst));
    }
}