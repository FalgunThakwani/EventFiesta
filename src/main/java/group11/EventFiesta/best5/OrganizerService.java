package group11.EventFiesta.best5;

public class OrganizerService extends GroupComponent {

    /// 1 particular organizer will have 1 service and 1 score

    protected String serviceName;// catering, decoration, auditorium
    protected Integer orgranizerId;
    protected Integer serviceId;
    protected Double budget;
    protected Double headCount;
    protected String event;
    protected String dateTime;
    private Double score;
    private ICalculateScore calculator;

    public OrganizerService() {
        this.calculator = new CalculateScoreForService(this);
    }

    public OrganizerService(String serviceName, Double score) {
        this.serviceName = serviceName;
        this.score = score;
    }

    public Double calculateScore() {
        Double scoreByRatings = 1.0 * calculator.calculateRatingsForService();
        System.out.println(scoreByRatings);
        return scoreByRatings;
    }

    @Override
    public String toString() {
        return "{" +
                " serviceName='" + serviceName + "'" +
                ", orgranizerId='" + orgranizerId + "'" +
                ", serviceId='" + serviceId + "'" +
                ", budget='" + budget + "'" +
                ", headCount='" + headCount + "'" +
                ", event='" + event + "'" +
                ", dateTime='" + dateTime + "'" +
                ", score='" + score + "'" +
                "}";
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
