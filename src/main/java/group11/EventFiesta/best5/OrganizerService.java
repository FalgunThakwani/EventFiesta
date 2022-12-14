package group11.EventFiesta.best5;

public class OrganizerService extends GroupComponent {

    /// 1 particular organizer will have 1 service and 1 score

    protected String serviceName;// catering, decoration, auditorium
    protected Integer orgranizerId;

    protected Integer id;
    protected Double budget;
    protected Double headCount;
    protected String event;
    protected String dateTime;
    protected Double score = 0.0;
    protected Double rating;
    protected Double price;

    private ICalculateScore calculator;

    public OrganizerService() {
        this.calculator = new CalculateScoreForService(this);
    }

    public OrganizerService(String serviceName, Double score) {
        this.serviceName = serviceName;
        this.score = score;
        this.calculator = new CalculateScoreForService(this);
    }

    public Double calculateScore() {
        System.out.println("Inside OrganizerService.calculateScore()");
        if (score == 0.0) {
            score = 1.0 * calculator.calculateScoreForService();
        }
        System.out.println(score);
        return score;
    }

    @Override
    public String toString() {
        return "{" +
                " serviceName='" + serviceName + "'" +
                ", orgranizerId='" + orgranizerId + "'" +
                ", serviceId='" + id + "'" +
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

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

}
