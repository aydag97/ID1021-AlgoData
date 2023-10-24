import java.util.ArrayList;

//each city has a name and an array containing its neightbours
public class City{
    public String name;
    public ArrayList<Connection> neighbours;

    public City(String name){
        this.name = name;
        neighbours = new ArrayList<Connection>();
    }
    // this method is used when reading the file and adding a new neighbour to a city
    public void connect(City next, int dst){
        neighbours.add(new Connection(next, dst));
    }
}