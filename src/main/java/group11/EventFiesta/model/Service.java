package group11.EventFiesta.model;

public class Service {
    private int ServiceId;
    private String name;
    private String serviceType;
    private String status;
    private float cost;

    public Service(){}
    public Service(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }
    public int getServiceId() {
        return ServiceId;
    }
    public void setServiceId(int serviceId) {
        ServiceId = serviceId;
    }
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
    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
