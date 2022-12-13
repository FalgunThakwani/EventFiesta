package group11.EventFiesta.model;

public class UserService
{
    private int id;
    private String type;
    private Organizer organizer;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int cost;


    public UserService()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", cost='" + cost + '\'' +
                ", organizer=" + organizer +
                '}';
    }
}
