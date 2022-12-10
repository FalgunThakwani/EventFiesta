package group11.EventFiesta.best5;

import group11.EventFiesta.model.EventServices;
import group11.EventFiesta.model.Reviews;

public class OrganizerService implements GroupComponent {

    /// 1 particular organizer will have 1 service and 1 score

    protected String serviceName;// catering, decoration, auditorium
    protected Integer orgranizerId;
    protected Integer serviceId;
    private Integer score;
    protected Reviews reviews;
    protected EventServices eventServices;
    protected Integer serviceRatings;
    private HelperForDB helper;

    public OrganizerService() {
        helper = new HelperForDB();
    }

    @Override
    public Integer calculateScore() {
        Integer scoreByRatings = helper.getRatingsForService(serviceId);
        System.out.println(scoreByRatings);
        return score;
    }

    @Override
    public void Add(GroupComponent child) {
        // DO NOTHING I AM A LEAF
    }

    @Override
    public void Remove(GroupComponent child) {
        // DO NOTHING I AM A LEAF
    }

}
