package group11.EventFiesta.best5;
import java.util.Random;

public class OrganizerService extends GroupComponent {

    private String serviceName;//catering, decoration, auditorium
    private Double score = 0.0; //min score because the criteria matched

    static int count = 1;

    public int organizerId;

    public OrganizerService(String serviceName, Double score) {
        this.serviceName = serviceName;
        this.score = score;
        organizerId = count++;
    }

    public String getServiceName() {
        return serviceName;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    @Override
    public Double calculateScore() {
        return score;
    }

    @Override
    public String toString() {
        return "OrganizerService{" +
                "serviceName='" + serviceName + '\'' +
                ", score=" + score +
                ", organizerId=" + organizerId +
                "}\n";
    }
}

