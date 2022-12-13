package group11.EventFiesta.model;

public class Service {
    private String name;

    public Service(){}
    public Service(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    private float cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


}
