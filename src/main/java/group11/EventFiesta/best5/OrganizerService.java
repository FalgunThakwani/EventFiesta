package group11.EventFiesta.best5;

import group11.EventFiesta.model.EventServices;
import group11.EventFiesta.model.Reviews;

public class OrganizerService extends GroupComponent {

    /// 1 particular organizer will have 1 service and 1 score

    protected String serviceName;// catering, decoration, auditorium
    protected Integer orgranizerId;
    protected Integer serviceId;
    private Double score;
    protected Reviews reviews;
    protected EventServices eventServices;
    protected Integer serviceRatings;
    private HelperForDB helper;

    public OrganizerService() {
        helper = new HelperForDB();
    }

    public OrganizerService(String serviceName, Double score) {
        helper = new HelperForDB();
        this.serviceName = serviceName;
        this.score = score;
    }

    public Double calculateScore() {
        Double scoreByRatings = 1.0 * helper.getRatingsForService(serviceId); //todo fix the return value
        System.out.println(scoreByRatings);
        return scoreByRatings;
    }

    @Override
    public String toString() {
        return "OrganizerService{" +
                "serviceName='" + serviceName + '\'' +
                ", score=" + score +
                "}\n";
    }

    @Override
    public void add(GroupComponent child) {
        // DO NOTHING I AM A LEAF
    }

    @Override
    public void remove(GroupComponent child) {
        // DO NOTHING I AM A LEAF
    }

}
